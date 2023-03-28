#if REVE
#define RPCBIND_MAXUADDRLEN  	(42)
#else
#define RPCBIND_MAXUADDRPLEN	sizeof(".255.255")
#define INET6_ADDRSTRLEN	    (48)
#define RPCBIND_MAXUADDR6LEN	\
		(INET6_ADDRSTRLEN + RPCBIND_MAXUADDRPLEN)
#define RPCBIND_MAXUADDRLEN	RPCBIND_MAXUADDR6LEN
#endif

#define NULL 			(0)
#define unlikely(c)		(c)

typedef enum {AF_INET, AF_INET6} family;

typedef int size_t;

struct net {
};

struct sockaddr {
	family sa_family;
};

#define __SOCK_SIZE__	16		/* sizeof(struct sockaddr)	*/
struct sockaddr_in {
  sa_family_t		sin_family;	/* Address family		*/
  __be16		sin_port;	/* Port number			*/
  struct in_addr	sin_addr;	/* Internet address		*/
};

struct sockaddr_in6 {
	int sin6_port;
};

extern int memcpy(char*, const char*, int);
extern char * strrchr(char*, int);
extern int strlen(char*);
extern int strict_strtoul(char*, int, unsigned long*);
extern int htons(int);

static size_t rpc_pton4(const char *buf, const size_t buflen,
			struct sockaddr *sap, const size_t salen)
{
	struct sockaddr_in *sin = (struct sockaddr_in *)sap;
	u8 *addr = (u8 *)&sin->sin_addr.s_addr;

	if (buflen > INET_ADDRSTRLEN || salen < sizeof(struct sockaddr_in))
		return 0;

	memset(sap, 0, sizeof(struct sockaddr_in));

	if (in4_pton(buf, buflen, addr, '\0', NULL) == 0)
		return 0;

	sin->sin_family = AF_INET;
	return sizeof(struct sockaddr_in);;
}

static inline int ipv6_addr_type(const struct in6_addr *addr)
{
	return __ipv6_addr_type(addr) & 0xffff;
}

#if defined(CONFIG_IPV6) || defined(CONFIG_IPV6_MODULE)
static int rpc_parse_scope_id(const char *buf, const size_t buflen,
			      const char *delim, struct sockaddr_in6 *sin6)
{
	char *p;
	size_t len;

	if ((buf + buflen) == delim)
		return 1;

	if (*delim != IPV6_SCOPE_DELIMITER)
		return 0;

	if (!(ipv6_addr_type(&sin6->sin6_addr) & IPV6_ADDR_LINKLOCAL) &&
	    !(ipv6_addr_type(&sin6->sin6_addr) & IPV6_ADDR_SITELOCAL))
		return 0;

	len = (buf + buflen) - delim - 1;
	p = kstrndup(delim + 1, len, GFP_KERNEL);
	if (p) {
		unsigned long scope_id = 0;
		struct net_device *dev;

		dev = dev_get_by_name(&init_net, p);
		if (dev != NULL) {
			scope_id = dev->ifindex;
			dev_put(dev);
		} else {
			if (strict_strtoul(p, 10, &scope_id) == 0) {
				kfree(p);
				return 0;
			}
		}

		kfree(p);

		sin6->sin6_scope_id = scope_id;
		return 1;
	}

	return 0;
}

static size_t rpc_pton6(const char *buf, const size_t buflen,
			struct sockaddr *sap, const size_t salen)
{
	struct sockaddr_in6 *sin6 = (struct sockaddr_in6 *)sap;
	u8 *addr = (u8 *)&sin6->sin6_addr.in6_u;
	const char *delim;

	if (buflen > (INET6_ADDRSTRLEN + IPV6_SCOPE_ID_LEN) ||
	    salen < sizeof(struct sockaddr_in6))
		return 0;

	memset(sap, 0, sizeof(struct sockaddr_in6));

	if (in6_pton(buf, buflen, addr, IPV6_SCOPE_DELIMITER, &delim) == 0)
		return 0;

	if (!rpc_parse_scope_id(buf, buflen, delim, sin6))
		return 0;

	sin6->sin6_family = AF_INET6;
	return sizeof(struct sockaddr_in6);
}
#else
static size_t rpc_pton6(const char *buf, const size_t buflen,
			struct sockaddr *sap, const size_t salen)
{
	return 0;
}
#endif

size_t rpc_pton(const char *buf, const size_t buflen,
		struct sockaddr *sap, const size_t salen)
{
	unsigned int i;

	for (i = 0; i < buflen; i++)
		if (buf[i] == ':')
			return rpc_pton6(buf, buflen, sap, salen);
	return rpc_pton4(buf, buflen, sap, salen);
}

size_t rpc_uaddr2sockaddr(struct net *net, const char *uaddr,
		const size_t uaddr_len, struct sockaddr *sap,
		const size_t salen)
{
	char *c, buf[RPCBIND_MAXUADDRLEN];
	unsigned long portlo, porthi;
	unsigned short port = 0;
	int r = 0;

	if (uaddr_len > RPCBIND_MAXUADDRLEN - 2) {
		r = 1;
		return 0;
	}

	memcpy(buf, uaddr, uaddr_len);

	buf[uaddr_len] = '\0';
	c = strrchr(buf, '.');

	if (unlikely(c == NULL))
		return 0;
	if (unlikely(strict_strtoul(c + 1, 10, &portlo) != 0))
		return 0;
	if (unlikely(portlo > 255))
		return 0;

	*c = '\0';
	c = strrchr(buf, '.');
	if (unlikely(c == NULL))
		return 0;
	if (unlikely(strict_strtoul(c + 1, 10, &porthi) != 0))
		return 0;
	if (unlikely(porthi > 255))
		return 0;

	*c = '\0';
	if (rpc_pton(net, buf, strlen(buf), sap, salen) == 0)
		return 0;

	if (sap->sa_family == AF_INET) {
		((struct sockaddr_in *)sap)->sin_port = htons(port);
		return sizeof(struct sockaddr_in);
	} else if (sap->sa_family == AF_INET6) {
		((struct sockaddr_in6 *)sap)->sin6_port = htons(port);
		return sizeof(struct sockaddr_in6);
	}
	return 0;
}
