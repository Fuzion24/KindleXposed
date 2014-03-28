package com.mobi.mobi;

import com.mobi.ArraySlice;

public class EXTHHeader {

    private byte[] identifer = new byte[4];
    private int headerLength;

    private int recordCount;

    private EXTHHeader(){ }

    public static EXTHHeader parse(ArraySlice as) throws Exception {
       EXTHHeader h = new EXTHHeader();

        as.readFully(h.identifer);

        if(! (h.identifer[0] == 'E' && h.identifer[1] == 'X' && h.identifer[2] == 'T' && h.identifer[3] == 'H')){
            throw new Exception("Magic for EXTH header is incorrect");
        }

        h.headerLength = as.readInt();
        h.recordCount = as.readInt();

        return h;
    }

    public int getRecordCount() {
        return recordCount;
    }

}
