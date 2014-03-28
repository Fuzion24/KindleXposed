package com.mobi.mobi;


import com.mobi.ArraySlice;

public class EXTHRecord {
    private int mrecordType;
    private int mRecordLength;
    private byte mRecordData[];

    private EXTHRecordType recordType;

    private EXTHRecord(){
    }

    public static EXTHRecord parse(ArraySlice as) throws Exception {
        EXTHRecord rec = new EXTHRecord();
        rec.mrecordType = as.readInt();
        rec.recordType = EXTHRecordType.fromInt(rec.mrecordType);
        rec.mRecordLength = as.readInt();
        byte[] recData = new byte[rec.mRecordLength];
        as.readFully(recData);
        rec.mRecordData = recData;
        return rec;
    }

    public EXTHRecordType getRecordType(){
        return recordType;
    }

    public String getUTF8String() {
      try{
          return new String(mRecordData, "UTF-8");
      }catch(Exception e){
          return null;
      }
    }
}
