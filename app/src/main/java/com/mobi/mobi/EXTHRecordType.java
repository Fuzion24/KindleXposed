package com.mobi.mobi;

public enum EXTHRecordType{

    AUTHOR(100,"author"),
    PUBLISHER(101, "publisher"),
    IMPRINT(102, "imprint"),
    DESCRIPTIOIN(103, "description"),
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
    ASIN2(504, "ASIN");


    EXTHRecordType(int n, String desc) { value = n; name = desc; }
    public final int value;
    public final String name;

}