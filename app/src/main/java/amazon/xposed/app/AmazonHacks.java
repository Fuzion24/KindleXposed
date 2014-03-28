package amazon.xposed.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedBridge.hookAllMethods;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class AmazonHacks implements IXposedHookLoadPackage {
    private String TAG = "AmazonSecurityPids";

    private static final String PACKAGE_NAME = AmazonHacks.class.getPackage().getName();
    XSharedPreferences savePrefs = new XSharedPreferences(PACKAGE_NAME);


    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.amazon.kindle"))
            return;

        hookAllMethods(findClass("com.amazon.system.security.Security", lpparam.classLoader), "getPids", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String[] secPids = (String[]) param.getResult();
                Set<String> secPidsSet = new HashSet<String>(Arrays.asList(secPids));
                savePrefs.edit().putStringSet("KindleSecurityPids", secPidsSet);
            }
        });

       /*
        hookAllMethods(findClass("com.amazon.android.docviewer.MobiKindleDocViewerFactory",lpparam.classLoader), "createKRFDocument",  new XC_MethodHook(){
             @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                 String bookFileName = (String) param.args[0];
                XposedBridge.log("Opening: " + bookFileName);
            }
        });
        findAndHookMethod("com.amazon.android.docviewer.MopKindleDocViewerFactory", lpparam.classLoader, "getDocSecurityPids", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                String[] pids = (String[]) param.getResult();
                for (String pid :pids) {
                    XposedBridge.log(pid);
                    Log.d(TAG, pid);
                }

                return pids;
            }
        });
        */
    }
}


