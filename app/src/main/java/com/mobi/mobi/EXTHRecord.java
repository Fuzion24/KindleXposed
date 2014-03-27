package com.mobi.mobi;


import com.mobi.ArraySlice;

public class EXTHRecord {
    private EXTHRecordType mRecordType;
    private final int mRecordLength;
    private final byte mRecordData[];

    private EXTHRecord(int recordLength, byte[] recordData){
        mRecordLength = recordLength;
        mRecordData = recordData;
    }

    public static EXTHRecord parse(ArraySlice as) throws Exception {

        int rLen = as.readInt();
        byte[] recData = new byte[rLen];
        as.readFully(recData);
        return new EXTHRecord(rLen, recData);
    }
}
