int f(int x, int y, int z) {
    int r = 0;
#if defined A && !defined B
    x = y + 1;
#endif
    while (z > 0) {
#if defined A || defined C
        r += y;
#else
        r += x;
#endif
        --z;
    }
    return r;
}