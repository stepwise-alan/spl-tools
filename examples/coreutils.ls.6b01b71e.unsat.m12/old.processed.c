typedef signed char smallint;
typedef unsigned long int __dev_t;
typedef unsigned int __uid_t;
typedef unsigned int __gid_t;
typedef unsigned long int __ino_t;
typedef unsigned int __mode_t;
typedef unsigned long int __nlink_t;
typedef long int __off_t;
typedef long int __time_t;
typedef long int __blksize_t;
typedef long int __blkcnt_t;
typedef __off_t off_t;
typedef __time_t time_t;
struct  timespec {
  __time_t tv_sec ;
  long  int tv_nsec ;
}  ;
struct  stat {
  __dev_t st_dev ;
  __ino_t st_ino ;
  __nlink_t st_nlink ;
  __mode_t st_mode ;
  __uid_t st_uid ;
  __gid_t st_gid ;
  int __pad0 ;
  __dev_t st_rdev ;
  __off_t st_size ;
  __blksize_t st_blksize ;
  __blkcnt_t st_blocks ;
  struct  timespec   st_atim ;
  struct  timespec   st_mtim ;
  struct  timespec   st_ctim ;
  long  int __unused[3] ;
}  ;
extern int strcmp(const  char *__s1 , const  char *__s2 ) __attribute__((__nothrow__)) __attribute__((__pure__)) __attribute__((__nonnull__ (1, 2)));
extern int strcoll(const  char *__s1 , const  char *__s2 ) __attribute__((__nothrow__)) __attribute__((__pure__)) __attribute__((__nonnull__ (1, 2)));

#if (definedEx(CONFIG_FEATURE_FIND_CONTEXT) || definedEx(CONFIG_SELINUX))
typedef char *security_context_t;
#endif


#if definedEx(CONFIG_LFS)
typedef unsigned long long uoff_t;
#endif


#if !definedEx(CONFIG_LFS)
typedef unsigned long uoff_t;
#endif

enum  {
  COMMON_BUFSIZE = ((8192 >= (256 * sizeof(void *))) ? (8192 + 1) : (256 * sizeof(void *)))
} ;
extern char bb_common_bufsiz1[COMMON_BUFSIZE];
enum  {
  TERMINAL_WIDTH = 80,
  COLUMN_GAP = 2,
  STYLE_COLUMNS = (1 << 21),
  STYLE_LONG = (2 << 21),
  STYLE_SINGLE = (3 << 21),
  STYLE_MASK = STYLE_SINGLE,
  LIST_INO = (1 << 0),
  LIST_BLOCKS = (1 << 1),
  LIST_MODEBITS = (1 << 2),
  LIST_NLINKS = (1 << 3),
  LIST_ID_NAME = (1 << 4),
  LIST_ID_NUMERIC = (1 << 5),
  LIST_CONTEXT = (1 << 6),
  LIST_SIZE = (1 << 7),
  LIST_DATE_TIME = (1 << 9),
  LIST_FULLTIME = (1 << 10),
  LIST_FILENAME = (1 << 11),
  LIST_SYMLINK = (1 << 12),
  LIST_FILETYPE = (1 << 13),
  LIST_EXEC = (1 << 14),
  LIST_MASK = ((LIST_EXEC << 1) - 1),
  DISP_DIRNAME = (1 << 15),
  DISP_HIDDEN = (1 << 16),
  DISP_DOT = (1 << 17),
  DISP_NOLIST = (1 << 18),
  DISP_RECURSIVE = (1 << 19),
  DISP_ROWS = (1 << 20),
  DISP_MASK = (((DISP_ROWS << 1) - 1) & (~ (DISP_DIRNAME - 1))),
  SORT_FORWARD = 0,
  SORT_REVERSE = (1 << 27),
  SORT_NAME = 0,
  SORT_SIZE = (1 << 28),
  SORT_ATIME = (2 << 28),
  SORT_CTIME = (3 << 28),
  SORT_MTIME = (4 << 28),
  SORT_VERSION = (5 << 28),
  SORT_EXT = (6 << 28),
  SORT_DIR = (7 << 28),
  SORT_MASK = ((7 << 28) 
  #if definedEx(CONFIG_FEATURE_LS_SORTFILES)
  * 1
  #endif
   
  #if !definedEx(CONFIG_FEATURE_LS_SORTFILES)
  * 0
  #endif
  ),
  TIME_CHANGE = ((1 << 23) 
  #if (definedEx(CONFIG_FTPD) || definedEx(CONFIG_FEATURE_LS_TIMESTAMPS))
  * 1
  #endif
   
  #if (!definedEx(CONFIG_FEATURE_LS_TIMESTAMPS) && !definedEx(CONFIG_FTPD))
  * 0
  #endif
  ),
  TIME_ACCESS = ((1 << 24) 
  #if (definedEx(CONFIG_FTPD) || definedEx(CONFIG_FEATURE_LS_TIMESTAMPS))
  * 1
  #endif
   
  #if (!definedEx(CONFIG_FEATURE_LS_TIMESTAMPS) && !definedEx(CONFIG_FTPD))
  * 0
  #endif
  ),
  TIME_MASK = ((3 << 23) 
  #if (definedEx(CONFIG_FTPD) || definedEx(CONFIG_FEATURE_LS_TIMESTAMPS))
  * 1
  #endif
   
  #if (!definedEx(CONFIG_FEATURE_LS_TIMESTAMPS) && !definedEx(CONFIG_FTPD))
  * 0
  #endif
  ),
  FOLLOW_LINKS = ((1 << 25) 
  #if definedEx(CONFIG_FEATURE_LS_FOLLOWLINKS)
  * 1
  #endif
   
  #if !definedEx(CONFIG_FEATURE_LS_FOLLOWLINKS)
  * 0
  #endif
  ),
  LS_DISP_HR = ((1 << 26) 
  #if definedEx(CONFIG_FEATURE_HUMAN_READABLE)
  * 1
  #endif
   
  #if !definedEx(CONFIG_FEATURE_HUMAN_READABLE)
  * 0
  #endif
  ),
  LIST_SHORT = LIST_FILENAME,
  LIST_LONG = (LIST_MODEBITS | LIST_NLINKS | LIST_ID_NAME | LIST_SIZE | LIST_DATE_TIME | LIST_FILENAME | LIST_SYMLINK),
  SPLIT_DIR = 1,
  SPLIT_FILE = 0,
  SPLIT_SUBDIR = 2
} ;
struct  dnode {
  const  char *name ;
  const  char *fullname ;
  struct  dnode   *next ;
  smallint fname_allocated ;
  struct  stat   dstat ;
  
  #if (definedEx(CONFIG_FEATURE_FIND_CONTEXT) || definedEx(CONFIG_SELINUX))
  security_context_t sid ;
  #endif
  
}  ;
struct  globals {
  
  #if definedEx(CONFIG_FEATURE_LS_COLOR)
  smallint show_color ;
  #endif
  
  smallint exit_code ;
  unsigned all_fmt ;
  
  #if definedEx(CONFIG_FEATURE_AUTOWIDTH)
  unsigned tabstops ;
  #endif
  
  
  #if definedEx(CONFIG_FEATURE_AUTOWIDTH)
  unsigned terminal_width ;
  #endif
  
  
  #if (definedEx(CONFIG_FTPD) || definedEx(CONFIG_FEATURE_LS_TIMESTAMPS))
  time_t current_time_t ;
  #endif
  
}  ;

#if definedEx(CONFIG_FEATURE_LS_SORTFILES)
static  int sortcmp(const  void *a , const  void *b )  {
  struct  dnode   *d1 =  (*((struct  dnode   **) a));
  struct  dnode   *d2 =  (*((struct  dnode   **) b));
  unsigned sort_opts =  ((*((struct  globals   *) (&bb_common_bufsiz1))).all_fmt & SORT_MASK);
  off_t dif;
  (dif = 0);
  if ((sort_opts == SORT_SIZE)) {
    (dif = (d2->dstat.st_size - d1->dstat.st_size));
  } 
  else if ((sort_opts == SORT_ATIME)) {
    (dif = (d2->dstat.st_atim.tv_sec - d1->dstat.st_atim.tv_sec));
  }
  
  else if ((sort_opts == SORT_CTIME)) {
    (dif = (d2->dstat.st_ctim.tv_sec - d1->dstat.st_ctim.tv_sec));
  }
  
  else if ((sort_opts == SORT_MTIME)) {
    (dif = (d2->dstat.st_mtim.tv_sec - d1->dstat.st_mtim.tv_sec));
  }
  
  else if ((sort_opts == SORT_DIR)) {
    (dif = (((d2->dstat.st_mode & 0170000) == 0040000) - ((d1->dstat.st_mode & 0170000) == 0040000)));
  } 
  if ((dif == 0)) {
    if (
    #if (definedEx(CONFIG_FEATURE_LS_SORTFILES) && definedEx(CONFIG_LOCALE_SUPPORT))
    1
    #else
    0
    #endif
    ) (dif = strcoll(d1->name, d2->name));  
    else (dif = strcmp(d1->name, d2->name));
  }  
  if ((sizeof(dif) > sizeof(int ))) {
    if ((sizeof(dif) == (sizeof(int ) * 2))) {
      if ((dif != 0)) {
        (dif = (1 | ((int ) (((uoff_t ) dif) >> (sizeof(int ) * 8)))));
      }  
    }  
    else {
      while (((dif & (~ ((off_t ) 2147483647))) != 0)) {
        (dif >>= (sizeof(int ) * 8 / 2));
      }
    }
  }  
  return (((*((struct  globals   *) (&bb_common_bufsiz1))).all_fmt & SORT_REVERSE) ? (- ((int ) dif)) : ((int ) dif));
}
#endif
