#define RPCBIND_MAXUADDRLEN (42)
#define NULL (0)
#define unlikely(c) (c)
#define INET6_ADDRSTRLEN (48)
#define IPV6_SCOPE_ID_LEN sizeof("%nnnnnnnnnn")
#define IPV6_SCOPE_DELIMITER '%'
#define INET_ADDRSTRLEN (16)
#define AF_INET (2)
#define AF_INET6 (10)

typedef unsigned short sa_family_t;

typedef int size_t;

typedef unsigned char u8;

typedef unsigned short sa_family_t;

typedef unsigned int __u32;

typedef unsigned char __u8;

typedef unsigned short __u16;

typedef __u16 __be16;

typedef __u32 __be32;

struct sockaddr {
    sa_family_t sa_family;
    char sa_data[14];
};

struct in_addr {
    __be32 s_addr;
};

struct sockaddr_in {
    sa_family_t sin_family;
    int sin_port;
    struct in_addr sin_addr;
};

struct in6_addr {
    union   {
        __u8 u6_addr8[16] ;
        __be16 u6_addr16[8] ;
        __be32 u6_addr32[4] ;
    }  in6_u ;
};

struct sockaddr_in6 {
    unsigned short int sin6_family;    /* AF_INET6 */
    int sin6_port;
    struct in6_addr sin6_addr;
};

extern int memcpy(char*, const char*, int);
extern char * strrchr(char*, int);
extern int strlen(char*);
extern int strict_strtoul(char*, int, unsigned long*);
extern int htons(int);

static size_t rpc_pton6(const char *buf, const size_t buflen,
            struct sockaddr *sap, const size_t salen)
{
#if defined(CONFIG_IPV6) || defined(CONFIG_IPV6_MODULE)
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
#else
    return 0;
#endif
}

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

size_t rpc_pton(const char *buf, const size_t buflen,
        struct sockaddr *sap, const size_t salen)
{
    unsigned int i;

    for (i = 0; i < buflen; i++)
        if (buf[i] == ':')
            return rpc_pton6(buf, buflen, sap, salen);
    return rpc_pton4(buf, buflen, sap, salen);
}

size_t rpc_uaddr2sockaddr(const char *uaddr,
        const size_t uaddr_len, struct sockaddr *sap,
        const size_t salen)
{
    _Bool returned = 0;
    size_t return_value;
    char *c, buf[RPCBIND_MAXUADDRLEN];
    unsigned long portlo, porthi;
    unsigned short port = 0;
    int r = 0;

    if (uaddr_len > RPCBIND_MAXUADDRLEN - 2) {
        r = 1;
//        return 0;
        if (!returned) {
            returned = 1;
            return_value = 0;
        }
    }

    memcpy(buf, uaddr, uaddr_len);

    buf[uaddr_len] = '\0';
    c = strrchr(buf, '.');

    if (unlikely(c == NULL))
//        return 0;
        if (!returned) {
            returned = 1;
            return_value = 0;
        }
    if (unlikely(strict_strtoul(c + 1, 10, &portlo) != 0))
//        return 0;
        if (!returned) {
            returned = 1;
            return_value = 0;
        }
    if (unlikely(portlo > 255))
//        return 0;
        if (!returned) {
            returned = 1;
            return_value = 0;
        }

    *c = '\0';
    c = strrchr(buf, '.');
    if (unlikely(c == NULL))
//        return 0;
        if (!returned) {
            returned = 1;
            return_value = 0;
        }
    if (unlikely(strict_strtoul(c + 1, 10, &porthi) != 0))
//        return 0;
        if (!returned) {
            returned = 1;
            return_value = 0;
        }
    if (unlikely(porthi > 255))
//        return 0;
        if (!returned) {
            returned = 1;
            return_value = 0;
        }

    *c = '\0';
    if (rpc_pton(buf, strlen(buf), sap, salen) == 0)
//        return 0;
        if (!returned) {
            returned = 1;
            return_value = 0;
        }

    if (sap->sa_family == AF_INET) {
        ((struct sockaddr_in *)sap)->sin_port = htons(port);
//        return sizeof(struct sockaddr_in);
        if (!returned) {
            returned = 1;
            return_value = sizeof(struct sockaddr_in);
        }
    } else if (sap->sa_family == AF_INET6) {
        ((struct sockaddr_in6 *)sap)->sin6_port = htons(port);
//        return sizeof(struct sockaddr_in6);
        if (!returned) {
            returned = 1;
            return_value = sizeof(struct sockaddr_in6);
        }
    }
    return return_value;
}
