package amazon.xposed.app;

import android.util.Log;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedBridge.hookAllMethods;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class AmazonHacks implements IXposedHookLoadPackage {
    private String TAG = "AmazonSecurityPids";

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.amazon.kindle"))
            return;

        hookAllMethods(findClass("com.amazon.system.security.Security", lpparam.classLoader), "getPids",  new XC_MethodHook(){
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String [] secPids = (String[])param.getResult();
                for(String sPid : secPids){
                    XposedBridge.log("Amazon kindle security pid: " + sPid);
                }
                XposedBridge.log("abstract getPIDS called!");
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


