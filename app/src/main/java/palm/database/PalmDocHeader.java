package palm.database;

import com.mobi.ArraySlice;

public class PalmDocHeader {
    private short compression;
    private short unused;
    private int   textLength;
    private short recordCount;
    private short recordSize;
    private int   currentPosition;

    public static PalmDocHeader parse(ArraySlice as) throws Exception{
        PalmDocHeader pdh = new PalmDocHeader();
        pdh.compression = as.readShort();
        pdh.unused = as.readShort();
        pdh.textLength = as.readInt();
        pdh.recordCount = as.readShort();
        pdh.recordSize = as.readShort();
        pdh.currentPosition = as.readInt();

        return pdh;
    }
}
