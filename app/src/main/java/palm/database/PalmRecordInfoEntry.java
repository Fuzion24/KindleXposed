package palm.database;

import com.mobi.ArraySlice;

public class PalmRecordInfoEntry{
   private int dataOffset;
   private byte recordAttributes;
   private int uniqueID;

   private PalmRecordInfoEntry(){}
   public static PalmRecordInfoEntry parse(ArraySlice as) throws Exception {
       PalmRecordInfoEntry prie = new PalmRecordInfoEntry();
       prie.dataOffset = as.readInt();
       int temp = as.readInt();
       prie.uniqueID = temp & 0xFFFFFF;
       prie.recordAttributes = (byte) (temp >> 3);

       /*
        ms.flags =  as.readByte();
        ms.val   =  (as.readByte() << 16) | (as.readByte() << 8) | (as.readByte()) ;
       */
       return prie;
   }

    public int getDataOffset() {
        return dataOffset;
    }
}