package amazon.xposed.app;

import android.util.Log;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedBridge.hookAllMethods;
import static de.robv.android.xposed.XposedBridge.hookMethod;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodBestMatch;
import static de.robv.android.xposed.XposedHelpers.newInstance;

public class AmazonHacks implements IXposedHookLoadPackage {
    private String TAG = "AmazonSecurityPids";

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.amazon.kindle"))
            return;

        /*
         Class skof = findClass("com.amazon.kcp.application.StandaloneKindleObjectFactory",lpparam.classLoader);
        Class asec = findClass("com.mobipocket.android.library.reader.AndroidSecurity",lpparam.classLoader);

       Object kof = newInstance(skof);
       Object androidSec = callMethod(kof, "getSecurity");
       callMethod(androidSec, "getPids");

        MopKindleDocViewerFactory
*/
        Class asec = findClass("com.amazon.android.docviewer.MopKindleDocViewerFactory", lpparam.classLoader);
        /*
        Method dsp;
        for(Method m: asec.getDeclaredMethods()){
            if(m.getName().equalsIgnoreCase("getDocSecurityPids"))
                dsp = m;
        }
//        Method getDocSecPids = findMethodBestMatch(asec, "getDocSecurityPids");
*/
        hookAllMethods(asec, "getDocSecurityPids", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("Made it here!");
                String[] secPids = (String[]) param.getResult();
                for (String pid : secPids) {
                    XposedBridge.log(pid);
                }
            }
        });


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

        hookAllMethods(findClass("com.amazon.android.docviewer.MobiKindleDocViewerFactory",lpparam.classLoader), "createKRFDocument",  new XC_MethodHook(){
             @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                 String bookFileName = (String) param.args[0];
                XposedBridge.log("Opening: " + bookFileName);
            }
        });



        /*
        findAndHookMethod("com.amazon.android.docviewer.MopKindleDocViewerFactory", lpparam.classLoader, "getDocSecurityPids", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                String[] pids = (String[]) param.getResult();
                XposedBridge.log("asaaaaaaaaaaaaaaaaaa");

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


