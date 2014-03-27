package palm.database;


import com.mobi.ArraySlice;

import java.util.LinkedList;
import java.util.List;

public class PalmDatabaseFormat {
    //http://wiki.mobileread.com/wiki/PDB#Palm_Database_Format

    private String name;
    private short attributes;
    private short version;
    private int created;
    private int modified;
    private int backup;
    private int modnum;
    private int appInfoId;
    private int sortInfoID;
    private int type;
    private int creator;
    private int uniqueIDseed;
    private int nextRecordListID;
    private int numOfRecords;

    public List<PalmRecordInfoEntry> getRecordInfoEntryList() {
        return recordInfoEntryList;
    }

    private List<PalmRecordInfoEntry> recordInfoEntryList;

    protected PalmDatabaseFormat(){}

/*
    Adobe Reader	.pdfADBE
    PalmDOC	TEXtREAd
    BDicty	BVokBDIC
    DB (Database program)	DB99DBOS
    eReader	PNRdPPrs
    eReader	DataPPrs
    FireViewer (ImageViewer)	 vIMGView
    HanDBase	PmDBPmDB
    InfoView	InfoINDB
    iSilo	ToGoToGo
    iSilo 3	SDocSilX
    JFile	JbDbJBas
    JFile Pro	JfDbJFil
    LIST	DATALSdb
    MobileDB	Mdb1Mdb1
    MobiPocket	BOOKMOBI
    Plucker	DataPlkr
    QuickSheet	DataSprd
    SuperMemo	SM01SMem
    TealDoc	TEXtTlDc
    TealInfo	InfoTlIf
    TealMeal	DataTlMl
    TealPaint	DataTlPt
    ThinkDB	dataTDBP
    Tides	TdatTide
    TomeRaider	ToRaTRPW
    Weasel	zTXTGPlm
    WordSmith	BDOCWrdS
*/

    public static PalmDatabaseFormat parse(ArraySlice as, PalmDatabaseFormat pdf) throws Exception {
        pdf.name = as.readString(32);
        pdf.attributes = as.readShort();
        pdf.version = as.readShort();
        pdf.created = as.readInt();
        pdf.modified = as.readInt();
        pdf.backup   = as.readInt();
        pdf.modnum   = as.readInt();
        pdf.appInfoId = as.readInt();
        pdf.sortInfoID = as.readInt();
        pdf.type       = as.readInt();
        pdf.creator    = as.readInt();
        pdf.uniqueIDseed = as.readInt();
        pdf.nextRecordListID = as.readInt();
        pdf.numOfRecords = as.readShort();

        List<PalmRecordInfoEntry> recs = new LinkedList<PalmRecordInfoEntry>();
        for(int i = 0; i < pdf.numOfRecords; i++){
            recs.add(PalmRecordInfoEntry.parse(as));
        }

        pdf.recordInfoEntryList = recs;

        return pdf;
    }

}
