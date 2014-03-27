package com.mobi;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public class ArraySlice extends RandomAccessFile {
    public ArraySlice(String name, String mode) throws Exception{
        super(name,mode);
    }

    public String readString(long offset, long size) throws Exception {
        seek(offset);
        return readString(size);
    }

    public String readString(long size) throws Exception {
        byte[] stringBuf = new byte[(int)size];
        readFully(stringBuf);
        return new String(stringBuf);
    }

    public short readShort(long offset) throws Exception {
        seek(offset);
        return readShort();
    }
}
