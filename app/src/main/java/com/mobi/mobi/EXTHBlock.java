package com.mobi.mobi;

import com.mobi.ArraySlice;

import java.util.ArrayList;
import java.util.List;

public class EXTHBlock {
    private EXTHHeader mHeader;
    private List<EXTHRecord> records = new ArrayList<EXTHRecord>();

    private EXTHBlock(){}

    public static EXTHBlock parse(ArraySlice as) throws Exception {
        EXTHBlock blk = new EXTHBlock();
        blk.mHeader = EXTHHeader.parse(as);
        for(int i = 0; i < blk.mHeader.getRecordCount(); i++)
            EXTHRecord.parse(as);

        return blk;
    }

    public List<EXTHRecord> getRecords() {
        return records;
    }
}
