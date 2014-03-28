package com.mobi.mobi;

public enum EXTHRecordType{

    AUTHOR(100,"author"),
    PUBLISHER(101, "publisher"),
    IMPRINT(102, "imprint"),
    DESCRIPTION(103, "description"),
    ISBN(104, "ISBN"),
    SUBJECT(105, "subject"),
    PUBLISHING_DATE(106, "publishing date"),
    REVIEW(107, "review"),
    CONTRIBUTOR(108, "contributor"),
    RIGHTS(109, "rights"),
    SUBJECT_CODE(110, "subject code"),
    TYPE(111, "type"),
    SOURCE(112, "source"),
    ASIN(113, "ASIN"),
    VER_NUM(114, "version number"),
    RETAIL_PRICE(118, "retail price"),
    RETAIL_CUR(119, "retail price currency"),
    DICT_SHORT_NAME(200, "dictionary short name"),
    TTS_OFF(404, "TTS off"),
    CDE_TYPES(501, "CDE type"),
    UPDATED_TITLE(503, "updated title"),
    ASIN2(504, "ASIN"),
    UNKNOWN(-1, "UNKNOWN");


    EXTHRecordType(int n, String desc) { value = n; name = desc; }
    public final int value;
    public final String name;

    public static EXTHRecordType fromInt(int v) throws Exception {
       switch(v){
           case 100: return AUTHOR;
           case 101: return PUBLISHER;
           case 102: return AUTHOR;
           case 103: return DESCRIPTION;
           case 104: return ISBN;
           case 105: return SUBJECT;
           case 106: return PUBLISHING_DATE;
           case 107: return REVIEW;
           case 108: return CONTRIBUTOR;
           case 109: return RIGHTS;
           case 110: return SUBJECT_CODE;
           case 111: return TYPE;
           case 112: return SOURCE;
           case 113: return ASIN;
           case 114: return VER_NUM;
           case 118: return RETAIL_PRICE;
           case 119: return RETAIL_CUR;
           case 200: return DICT_SHORT_NAME;
           case 404: return TTS_OFF;
           case 501: return CDE_TYPES;
           case 503: return UPDATED_TITLE;
           case 504: return ASIN2;
           default: return UNKNOWN; //throw new Exception("Unknown EXTHRecord: " + v);
       }
    }

}