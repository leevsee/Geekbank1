package com.leeves.h.geekbank1.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.leeves.h.geekbank1.IMyAidlInterface;
import com.leeves.h.geekbank1.R;

/**
 * Function：
 * Created by h on 2016/9/5.
 *
 * @author Leeves
 */
public class AIDLActivity extends Activity{

    private IMyAidlInterface mMyAidlInterface;

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        findViewById(R.id.aidl_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMyAidlInterface !=null){
                    try {
                        String name = mMyAidlInterface.getName("喵喵喵");
                        Toast.makeText(AIDLActivity.this,name+ "___",Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        bindService(new Intent(this,AIDLService.class),mServiceConnection, Context.BIND_AUTO_CREATE);
    }
}
