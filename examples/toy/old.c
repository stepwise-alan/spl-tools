int f(int x, int y, int z) {
    int r = 0;
#if defined A && defined B
    x = y + 1;
#endif
    while (z > 0) {
#if defined A || !defined C
        r += x;
#else
        r += y;
#endif
        --z;
    }
    return r;
}