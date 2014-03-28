package com.mobi.mobi;

import com.mobi.ArraySlice;

public class EXTHHeader {

    private int identifer;
    private int headerLength;

    private int recordCount;

    private EXTHHeader(){ }

    public static EXTHHeader parse(ArraySlice as) throws Exception {
       EXTHHeader h = new EXTHHeader();

        h.identifer = as.readInt();
        h.headerLength = as.readInt();
        h.recordCount = as.readInt();

        return h;
    }

    public int getRecordCount() {
        return recordCount;
    }

}
