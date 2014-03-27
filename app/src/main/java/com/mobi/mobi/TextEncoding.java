package com.mobi.mobi;

public enum TextEncoding {
    CP1252(1252),
    UTF8(65001);

    TextEncoding(int n) { value = n; }
    public final int value;
}
