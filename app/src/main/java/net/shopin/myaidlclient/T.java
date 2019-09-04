//package net.shopin.myaidlclient;
//
//import android.os.IBinder;
//import android.os.RemoteException;
//
//import java.util.HashMap;
//
///**
// * Created by zcs on 2019/1/4.
// */
//
//class T {
//}
//
//
//public interface IServiceManager extends IInterface {
//    /**
//     发现IServiceManager继承自IInterface接口,
//     是不是和AIDL有类似？
//     */
//
//}
//
///**
// * ServiceManagerNative的asInterface方法
// * 参数是传过来的一个IBinder对象
// * */
//static public IServiceManager asInterface(IBinder obj) {
//    if (obj == null) {
//        return null;
//    }
//    //根据IBinder对象对应的descriptor，获取对应的IServiceManager
//    IServiceManager in = (IServiceManager)obj.queryLocalInterface(descriptor);
//    if (in != null) {
//        return in;
//    }
//    //上述in为null，则执行代理
//    return new ServiceManagerProxy(obj);
//}
//
//
//
//public final class ServiceManager {
//    //一个IServiceManager接口对象
//    private static IServiceManager sServiceManager;
//    private static HashMap<String, IBinder> sCache = new HashMap<String, IBinder>();
//    //整个类的操作全都依靠这个单例 IServiceManager
//    private static IServiceManager getIServiceManager() {
//        if (sServiceManager != null) {
//            return sServiceManager;
//        }
//        //注意这里传入BinderInternal.getContextObject()-IBinder对象
//        sServiceManager = ServiceManagerNative.asInterface(BinderInternal.getContextObject());
//        return sServiceManager;
//    }
//
//    public static IBinder getService(String name) {
//        try {
//            IBinder service = sCache.get(name);
//            if (service != null) {
//                return service;
//            } else {
//                //返回的是一个Binder对象，说明：'name'对应的一定是IBinder的实例
//                return getIServiceManager().getService(name);
//            }
//        } catch (RemoteException e) {
//        }
//        return null;
//    }
//
//    public static void addService(String name, IBinder service) {
//        try {
//            //add的就是name和IBinder实例的对应关系
//            getIServiceManager().addService(name, service, false);
//        } catch (RemoteException e) {
//        }
//    }
//
//    //allowIsolated是否允许“隔离”？
//    public static void addService(String name, IBinder service, boolean allowIsolated) {
//        try {
//            getIServiceManager().addService(name, service, allowIsolated);
//        } catch (RemoteException e) {
//        }
//    }
//
//    public static IBinder checkService(String name) {
//        try {
//            IBinder service = sCache.get(name);
//            if (service != null) {
//                return service;
//            } else {
//                return getIServiceManager().checkService(name);
//            }
//        } catch (RemoteException e) {
//            return null;
//        }
//    }
//
//    public static String[] listServices() {
//        try {
//            return getIServiceManager().listServices();
//        } catch (RemoteException e) {
//            return null;
//        }
//    }
//
//    public static void initServiceCache(Map<String, IBinder> cache) {
//        if (sCache.size() != 0) {
//            throw new IllegalStateException("setServiceCache may only be called once");
//        }
//        sCache.putAll(cache);
//    }
//}
//
