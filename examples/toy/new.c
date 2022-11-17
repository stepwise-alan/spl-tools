// A: (x != y)
int lib(int x, int y) {
    int result = 0;
#ifdef A
//    result += x;
    result += y;
#else
    result += y;
#endif
#ifdef B
    result += x;
#else
    result += y;
#endif
#ifdef C
    result += x;
#else
    result += y;
#endif
    return result;
}

int client(int x, int y) {
    // A and !B: x + 1 != y
#ifdef A
    // A and !B: x + 1 != y
#ifdef B
    return lib(y, y);
#else
    // !A: x + 1 != y
    return lib(x + 1, y);
#endif
#else
    // !A: x != y
    return lib(x, y);
#endif
}
