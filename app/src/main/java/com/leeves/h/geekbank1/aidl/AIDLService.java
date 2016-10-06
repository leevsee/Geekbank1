package com.leeves.h.geekbank1.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.leeves.h.geekbank1.IMyAidlInterface;

/**
 * Function：
 * Created by h on 2016/9/5.
 *
 * @author Leeves
 */
public class AIDLService extends Service {

    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName(String nickName) throws RemoteException {
            return nickName+"1564645";
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
