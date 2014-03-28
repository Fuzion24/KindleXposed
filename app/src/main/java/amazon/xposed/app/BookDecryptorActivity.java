package amazon.xposed.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.mobi.mobi.MobiBook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookDecryptorActivity extends Activity {

    private String TAG = "BookDecryptor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File kindleDir = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.amazon.kindle/files/");
        File[] kindleFiles = kindleDir.listFiles();

        if(kindleFiles == null){
            // Permissions?
            return;
        }

        List<MobiBook> mobiFiles = new ArrayList<MobiBook>();

        // kindleFiles.filter(_.getName.endsWith(".prc")).flatMap(MobiBook(_)) .... just sayin' ...

        for(File f : kindleFiles)
          if(f.getName().endsWith(".prc"))
              try {
                  mobiFiles.add(MobiBook.parse(f));
              }catch(Exception e) {
                  Log.e(TAG, "Failed to parse: " + f.getName() + " \t " + e.getMessage());
                  e.printStackTrace();
              }


        StringBuilder sb = new StringBuilder();

        for(MobiBook mb : mobiFiles)
            sb.append(mb.getName() + "\n");

        Log.d(TAG, sb.toString());

    }

}
