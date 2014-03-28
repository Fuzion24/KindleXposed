package com.mobi.mobi;

import com.mobi.ArraySlice;

import java.io.DataInput;
import java.io.RandomAccessFile;

class MobiHeader{

    private short compression;
    private short unused;
    private int   textLength;
    private short recordCount;
    private short recordSize;
    private short encryptionType;
    private short garbage;
    private byte[] magic = new byte[4];
    private int headerLength;
    private int mobiType;
    private int textEnc;
    private int uniqueID;
    private int fileVersion;
    private int ortographicIndex;
    private int inflectionIndex;
    private int indexNames;
    private int indexKeys;
    private int extraIndex0;
    private int extraIndex1;
    private int extraIndex2;
    private int extraIndex3;
    private int extraIndex4;
    private int extraIndex5;
    private int firstNonbookIndex;
    private int fullNameOffset;
    private int fullNameLength;
    private int locale;
    private int inputLanguage;
    private int outputLanguage;
    private int minVersion;
    private int firstImageIndex;
    private int huffmanRecordOffset;
    private int huffmanRecordCount;
    private int huffmanTableOffset;
    private int huffmanTableLength;
    private int EXTHflags;
    private byte[] unknown = new byte[32];
    private int DRMoffset;
    private int DRMCount;
    private int DRMSize;
    private int DRMFlags;
    private long unknown2;
    private short firstContentRecordNum;
    private short lastContentRecordNum;
    private int unknown3;
    private int FCISRecordNumber;
    private int FCISRecordCount;
    private long unknown4;
    private int unknown5;
    private int firstCompilationDataSectionCount;
    private int numOfCompilationDataSects;
    private int unknown6;
    private int extraRecordDataFlags;
    private int indxRecordOffset;




    private MobiHeader(){}
    public static MobiHeader parse(ArraySlice as) throws Exception{
        MobiHeader mh = new MobiHeader();
        mh.compression = as.readShort();
        mh.unused = as.readShort();
        mh.textLength = as.readInt();
        mh.recordCount = as.readShort();
        mh.recordSize = as.readShort();
        mh.encryptionType = as.readShort();
        mh.garbage         = as.readShort();
        as.readFully(mh.magic);

        mh.headerLength   = as.readInt();
        mh.mobiType = as.readInt();
        mh.textEnc = as.readInt();
        mh.uniqueID = as.readInt();
        mh.fileVersion = as.readInt();
        mh.ortographicIndex = as.readInt();
        mh.inflectionIndex = as.readInt();
        mh.indexNames = as.readInt();
        mh.indexKeys = as.readInt();
        mh.extraIndex0 = as.readInt();
        mh.extraIndex1 = as.readInt();
        mh.extraIndex2 = as.readInt();
        mh.extraIndex3 = as.readInt();
        mh.extraIndex4 = as.readInt();
        mh.extraIndex5 = as.readInt();
        mh.firstNonbookIndex = as.readInt();
        mh.fullNameOffset = as.readInt();
        mh.fullNameLength = as.readInt();
        mh.locale = as.readInt();
        mh.inputLanguage = as.readInt();
        mh.outputLanguage = as.readInt();
        mh.minVersion = as.readInt();
        mh.firstImageIndex = as.readInt();
        mh.huffmanRecordOffset = as.readInt();
        mh.huffmanRecordCount = as.readInt();
        mh.huffmanTableOffset = as.readInt();
        mh.huffmanTableLength = as.readInt();
        mh.EXTHflags = as.readInt();
        as.readFully(mh.unknown = new byte[32]);
        mh.DRMoffset = as.readInt();
        mh.DRMCount = as.readInt();
        mh.DRMSize = as.readInt();
        mh.DRMFlags = as.readInt();
        mh.unknown2 = as.readLong();
        mh.firstContentRecordNum = as.readShort();
        mh.lastContentRecordNum = as.readShort();
        mh.unknown3 = as.readInt();
        mh.FCISRecordNumber = as.readInt();
        mh.FCISRecordCount = as.readInt();
        mh.unknown4 = as.readLong();
        mh.unknown5 = as.readInt();
        mh.firstCompilationDataSectionCount = as.readInt();
        mh.numOfCompilationDataSects = as.readInt();
        mh.unknown6 = as.readInt();
        mh.extraRecordDataFlags = as.readInt();
        mh.indxRecordOffset = as.readInt();
        return mh;
    }

    public int getFullNameOffset() {
        return fullNameOffset;
    }

    public boolean hasEXTHHeader(){
        return (EXTHflags & 0x40) > 0;
    }

    public int getFullNameLength() {
        return fullNameLength;
    }
}
