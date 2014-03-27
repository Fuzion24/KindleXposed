package com.mobi.mobi;

import com.mobi.ArraySlice;

import java.util.*;

import palm.database.PalmDatabaseFormat;
import palm.database.PalmRecordInfoEntry;

public class MobiBook extends PalmDatabaseFormat {

    private MobiHeader mHeader = null;


/*
  https://github.com/kroo/mobi-python/blob/master/mobi/__init__.py
  https://github.com/gluggy/Java-Mobi-Metadata-Editor/blob/master/src/mobimeta/MobiHeader.java
  http://wiki.mobileread.com/wiki/MOBI
*/

    public static MobiBook parse(String filename) throws Exception{
        return parse(new ArraySlice(filename, "r"));
    }
     public static MobiBook parse(ArraySlice as) throws Exception{
         MobiBook mb = new MobiBook();
         PalmDatabaseFormat.parse(as,mb);
         PalmRecordInfoEntry record0 = mb.getRecordInfoEntryList().get(0);

         as.seek(record0.getDataOffset());

         mb.mHeader = MobiHeader.parse(as);
         return mb;
     }


    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }



    private List<byte[]> validatePins(List<String> pids){
        List<byte[]> vPids = new ArrayList<byte[]>();
        for(String s : pids)
            vPids.add(validatePid(s));
        return vPids;
    }

    private byte[] validatePid(String pid){
        return pid.getBytes();
    }

}

