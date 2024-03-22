package v5910e3ec;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class l5910e3ec extends Application {
    public static String g;
    public static String h;
    public static Context i;
    public String b;
    public String c;
    // The application to be replaced
    public Application d;
    public Object e;
    // The path to the desired resources
    public String f;

    /* loaded from: classes.dex */
    public class jntm implements Application.ActivityLifecycleCallbacks {
        public jntm(l5910e3ec l5910e3ecVar) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            l5910e3ec.I5910e3ec_02(activity, bundle);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            l5910e3ec.I5910e3ec_04(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            l5910e3ec.I5910e3ec_03(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    public l5910e3ec() {
        g = "Virbox";
        h = l5910e3ec.class.getSimpleName();
    }

    public static native void I5910e3ec_00(Context context);

    public static native void I5910e3ec_01(Context context, Application application, String str);

    public static native void I5910e3ec_02(Activity activity, Bundle bundle);

    public static native void I5910e3ec_03(Activity activity);

    public static native void I5910e3ec_04(Activity activity);

    public static boolean c(Context context, String str, String str2, String str3) {
        String str4 = str2 + File.separator + str3;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str4);
        try {
            InputStream open = context.getResources().getAssets().open(str);
            if (file2.exists() && h(open, new FileInputStream(file2))) {
                open.close();
                return true;
            }
            file2.delete();
            open.close();
            InputStream open2 = context.getResources().getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str4);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open2.read(bArr);
                if (read == -1) {
                    open2.close();
                    fileOutputStream.close();
                    file2.setReadable(true, false);
                    file2.setExecutable(true, false);
                    file2.setWritable(false, false);
                    return true;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Field f(Object obj, String str) {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new IllegalStateException(str);
    }

    public static Context getAppContext() {
        return i;
    }

    public static boolean h(InputStream inputStream, InputStream inputStream2) {
        try {
            byte[] bArr = new byte[1024];
            byte[] bArr2 = new byte[1024];
            do {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return true;
                }
                if (read != inputStream2.read(bArr2)) {
                    break;
                }
            } while (Arrays.equals(bArr, bArr2));
            return false;
        } catch (IOException unused) {
            return false;
        }
    }

    public static String i(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ 105);
        }
        return new String(bArr, 0, bArr.length);
    }

    // Replaces the current application with another application
    public final void a() {
        Class<?> cls;
        Field field;
        try {
            
            // Get the ActivityThread class
            Class<?> cls2 = Class.forName(i(new byte[]{8, 7, 13, 27, 6, 0, 13, 71, 8, 25, 25, 71, 40, 10, 29, 0, 31, 0, 29, 16, 61, 1, 27, 12, 8, 13})); //android.app.ActivityThread
            
            // Get the currentActivityThread method to access the current ActivityThread instance
            Method method = cls2.getMethod(i(new byte[]{10, 28, 27, 27, 12, 7, 29, 40, 10, 29, 0, 31, 0, 29, 16, 61, 1, 27, 12, 8, 13}), new Class[0]); //currentActivityThread
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[0]);

            // Replace the application in the mAllApplications list
            Field declaredField = cls2.getDeclaredField(i(new byte[]{4, 32, 7, 0, 29, 0, 8, 5, 40, 25, 25, 5, 0, 10, 8, 29, 0, 6, 7})); //mInitialApplication
            declaredField.setAccessible(true);
            if (((Application) declaredField.get(invoke)) == this) {
                declaredField.set(invoke, this.d);
            }

            // Replace the application in the mAllApplications list
            Field declaredField2 = cls2.getDeclaredField(i(new byte[]{4, 40, 5, 5, 40, 25, 25, 5, 0, 10, 8, 29, 0, 6, 7, 26})); //mAllApplications
            declaredField2.setAccessible(true);
            List list = (List) declaredField2.get(invoke);
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (list.get(i2) == this) {
                    list.set(i2, this.d);
                }
            }

            // Get the LoadedApk or PackageInfo class to access the application and resource directory
            try {
                cls = Class.forName(i(new byte[]{8, 7, 13, 27, 6, 0, 13, 71, 8, 25, 25, 71, 37, 6, 8, 13, 12, 13, 40, 25, 2})); //android.app.LoadedApk
            } catch (ClassNotFoundException unused) {
                cls = Class.forName(i(new byte[]{8, 7, 13, 27, 6, 0, 13, 71, 8, 25, 25, 71, 40, 10, 29, 0, 31, 0, 29, 16, 61, 1, 27, 12, 8, 13, 77, 57, 8, 10, 2, 8, 14, 12, 32, 7, 15, 6})); //android.app.ActivityThread$PackageInfo
            }

            // Try to get the mLoadedApk field from the Application class
            Field declaredField3 = cls.getDeclaredField(i(new byte[]{4, 40, 25, 25, 5, 0, 10, 8, 29, 0, 6, 7})); //mApplication
            declaredField3.setAccessible(true);
            Field declaredField4 = cls.getDeclaredField(i(new byte[]{4, 59, 12, 26, 45, 0, 27})); //mResDir
            declaredField4.setAccessible(true);
            try {
                field = Application.class.getDeclaredField(i(new byte[]{4, 37, 6, 8, 13, 12, 13, 40, 25, 2})); //mLoadedApk
            } catch (NoSuchFieldException unused2) {
                field = null;
            }

            // Iterate through mPackages and mResourcePackages to replace the application
            String[] strArr = {i(new byte[]{4, 57, 8, 10, 2, 8, 14, 12, 26}), i(new byte[]{4, 59, 12, 26, 6, 28, 27, 10, 12, 57, 8, 10, 2, 8, 14, 12, 26})}; //mPackages, mResourcePackages
            for (int i3 = 0; i3 < 2; i3++) {
                Field declaredField5 = cls2.getDeclaredField(strArr[i3]);
                declaredField5.setAccessible(true);
                for (Map.Entry entry : ((Map) declaredField5.get(invoke)).entrySet()) {
                    Object obj = ((WeakReference) entry.getValue()).get();
                    if (obj != null && declaredField3.get(obj) == this) {
                        declaredField3.set(obj, this.d);
                        if (this.f != null) {
                            declaredField4.set(obj, this.f);
                        }
                        if (field != null) {
                            field.set(this.d, obj);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0184  */
    @Override // android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void attachBaseContext(android.content.Context r24) {
        /*
            Method dump skipped, instructions count: 620
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: v5910e3ec.l5910e3ec.attachBaseContext(android.content.Context):void");
    }

    public final void b() {
        Collection<WeakReference> collection;
        if (this.f == null) {
            return;
        }
        try {
            AssetManager assetManager = (AssetManager) AssetManager.class.getConstructor(new Class[0]).newInstance(new Object[0]);
            Method declaredMethod = AssetManager.class.getDeclaredMethod(i(new byte[]{8, 13, 13, 40, 26, 26, 12, 29, 57, 8, 29, 1}), String.class);
            declaredMethod.setAccessible(true);
            if (((Integer) declaredMethod.invoke(assetManager, this.f)).intValue() == 0) {
                throw new IllegalStateException(i(new byte[]{42, 6, 28, 5, 13, 73, 7, 6, 29, 73, 10, 27, 12, 8, 29, 12, 73, 7, 12, 30, 73, 40, 26, 26, 12, 29, 36, 8, 7, 8, 14, 12, 27}));
            }
            if (Build.VERSION.SDK_INT <= 19) {
                Method declaredMethod2 = AssetManager.class.getDeclaredMethod(i(new byte[]{12, 7, 26, 28, 27, 12, 58, 29, 27, 0, 7, 14, 43, 5, 6, 10, 2, 26}), new Class[0]);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(assetManager, new Object[0]);
            }
            Class<?> cls = Class.forName(i(new byte[]{8, 7, 13, 27, 6, 0, 13, 71, 8, 25, 25, 71, 59, 12, 26, 6, 28, 27, 10, 12, 26, 36, 8, 7, 8, 14, 12, 27}));
            Method declaredMethod3 = cls.getDeclaredMethod(i(new byte[]{14, 12, 29, 32, 7, 26, 29, 8, 7, 10, 12}), new Class[0]);
            declaredMethod3.setAccessible(true);
            Object invoke = declaredMethod3.invoke(null, new Object[0]);
            try {
                Field declaredField = cls.getDeclaredField(i(new byte[]{4, 40, 10, 29, 0, 31, 12, 59, 12, 26, 6, 28, 27, 10, 12, 26}));
                declaredField.setAccessible(true);
                collection = ((ArrayMap) declaredField.get(invoke)).values();
            } catch (NoSuchFieldException unused) {
                Field declaredField2 = cls.getDeclaredField(i(new byte[]{4, 59, 12, 26, 6, 28, 27, 10, 12, 59, 12, 15, 12, 27, 12, 7, 10, 12, 26}));
                declaredField2.setAccessible(true);
                collection = (Collection) declaredField2.get(invoke);
            }
            for (WeakReference weakReference : collection) {
                Resources resources = (Resources) weakReference.get();
                try {
                    Field declaredField3 = Resources.class.getDeclaredField(i(new byte[]{4, 40, 26, 26, 12, 29, 26}));
                    declaredField3.setAccessible(true);
                    declaredField3.set(resources, assetManager);
                } catch (NoSuchFieldException unused2) {
                    Field declaredField4 = Resources.class.getDeclaredField(i(new byte[]{4, 59, 12, 26, 6, 28, 27, 10, 12, 26, 32, 4, 25, 5}));
                    declaredField4.setAccessible(true);
                    Object obj = declaredField4.get(resources);
                    Field declaredField5 = obj.getClass().getDeclaredField(i(new byte[]{4, 40, 26, 26, 12, 29, 26}));
                    declaredField5.setAccessible(true);
                    declaredField5.set(obj, assetManager);
                }
                resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public final void d() {
        try {
            Method method = Class.forName(i(new byte[]{8, 7, 13, 27, 6, 0, 13, 71, 8, 25, 25, 71, 40, 10, 29, 0, 31, 0, 29, 16, 61, 1, 27, 12, 8, 13})).getMethod(i(new byte[]{10, 28, 27, 27, 12, 7, 29, 40, 10, 29, 0, 31, 0, 29, 16, 61, 1, 27, 12, 8, 13}), new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[0]);
            Object obj = f(invoke, i(new byte[]{4, 43, 6, 28, 7, 13, 40, 25, 25, 5, 0, 10, 8, 29, 0, 6, 7})).get(invoke);
            Field f = f(obj, i(new byte[]{25, 27, 6, 31, 0, 13, 12, 27, 26}));
            try {
                this.e = f.get(obj);
                f.set(obj, null);
            } catch (Exception e) {
                e = e;
                throw new IllegalStateException(e);
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final void e() {
        try {
            Class<?> cls = Class.forName(i(new byte[]{8, 7, 13, 27, 6, 0, 13, 71, 8, 25, 25, 71, 40, 10, 29, 0, 31, 0, 29, 16, 61, 1, 27, 12, 8, 13}));
            Method method = cls.getMethod(i(new byte[]{10, 28, 27, 27, 12, 7, 29, 40, 10, 29, 0, 31, 0, 29, 16, 61, 1, 27, 12, 8, 13}), new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[0]);
            Object obj = f(invoke, i(new byte[]{4, 43, 6, 28, 7, 13, 40, 25, 25, 5, 0, 10, 8, 29, 0, 6, 7})).get(invoke);
            f(obj, i(new byte[]{25, 27, 6, 31, 0, 13, 12, 27, 26})).set(obj, this.e);
            if (this.e != null) {
                Method declaredMethod = cls.getDeclaredMethod(i(new byte[]{0, 7, 26, 29, 8, 5, 5, 42, 6, 7, 29, 12, 7, 29, 57, 27, 6, 31, 0, 13, 12, 27, 26}), Context.class, List.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(invoke, this.d, this.e);
                this.e = null;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public final String g(String str, String str2, boolean z) {
        if (!z) {
            return str2.contains(i(new byte[]{8, 27, 4, 12, 8, 11, 0})) ? i(new byte[]{54, 8, 90, 91, 71, 26, 6}) : str2.contains(i(new byte[]{8, 27, 4, 95, 93})) ? i(new byte[]{54, 8, 95, 93, 71, 26, 6}) : str2.contains(i(new byte[]{17, 81, 95, 54, 95, 93})) ? i(new byte[]{54, 17, 95, 93, 71, 26, 6}) : str2.contains(i(new byte[]{17, 81, 95})) ? i(new byte[]{54, 17, 81, 95, 71, 26, 6}) : str2.contains(i(new byte[]{4, 0, 25, 26, 95, 93})) ? i(new byte[]{54, 4, 95, 93, 71, 26, 6}) : str2.contains(i(new byte[]{4, 0, 25, 26})) ? i(new byte[]{54, 4, 0, 25, 26, 71, 26, 6}) : "";
        } else if (str2.contains(i(new byte[]{8, 27, 4, 12, 8, 11, 0}))) {
            return i(new byte[]{54, 17, 81, 95, 71, 26, 6});
        } else {
            if (!str2.contains(i(new byte[]{8, 27, 4, 95, 93})) && !str2.contains(i(new byte[]{17, 81, 95, 54, 95, 93}))) {
                return str2.contains(i(new byte[]{17, 81, 95})) ? i(new byte[]{54, 17, 81, 95, 71, 26, 6}) : str2.contains(i(new byte[]{4, 0, 25, 26, 95, 93})) ? i(new byte[]{54, 4, 95, 93, 71, 26, 6}) : str2.contains(i(new byte[]{4, 0, 25, 26})) ? i(new byte[]{54, 4, 0, 25, 26, 71, 26, 6}) : "";
            }
            return i(new byte[]{54, 17, 95, 93, 71, 26, 6});
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        if (this.d != null) {
            try {
                a();
                b();
                e();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onCreate();
        Application application = this.d;
        if (application != null) {
            try {
                application.onCreate();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
