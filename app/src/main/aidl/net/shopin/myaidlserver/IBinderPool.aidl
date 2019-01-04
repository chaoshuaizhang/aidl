// IBinderPool.aidl
package net.shopin.myaidlserver;

// Declare any non-default types here with import statements
import net.shopin.myaidlserver.IBookManager;
interface IBinderPool {
    IBinder getBinderByTag(int tag);

    IBookManager getBinderByTagFun2(int tag);
}
