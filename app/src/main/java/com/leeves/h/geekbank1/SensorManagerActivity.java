package com.leeves.h.geekbank1;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Function：android传感器例子
 * Created by h on 2016/9/12.
 *
 * @author Leeves
 */
public class SensorManagerActivity extends Activity implements SensorEventListener {

    private ImageView mImageView;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float mStartDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sersor_manager);
        mImageView = (ImageView) findViewById(R.id.imageView);
        //
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始的时候绑定传感器
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //暂停的时候解绑，省电
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float degree = -event.values[0];
            //动画
            RotateAnimation rotateAnimation = new RotateAnimation(
                    mStartDegree, degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            );
            rotateAnimation.setDuration(200);
            mImageView.startAnimation(rotateAnimation);
            mStartDegree = degree;

        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //微信摇一摇
            float x = event.values[SensorManager.DATA_X];
            float y = event.values[SensorManager.DATA_Y];
            float z = event.values[SensorManager.DATA_Z];
            if (x > 18 || y > 18 || z > 18) {

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
