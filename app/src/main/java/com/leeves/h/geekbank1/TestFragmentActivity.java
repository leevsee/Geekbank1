package com.leeves.h.geekbank1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by h on 2016/7/19.
 */
public class TestFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //创建fragment事务，并打开事务
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //向TestFragment实例化，并传入数据
//        TestFragment testFragment = new TestFragment();
        TestFragment testFragment = TestFragment.newInstance("barely",12345);

        //讲起添加到ViewGroup，提交
        fragmentTransaction.add(R.id.fragment_view,testFragment,"fragment_tag").commit();

        //通过id或tag去找到fragment
//        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_test);

//        Fragment fragment2 = fragmentManager.findFragmentByTag("fragment_tag");

//        if (fragment instanceof TestFragment){
//
//        }else {
//            throw new IllegalStateException("is not testFragment");
//        }

        //将其移除
//        fragmentTransaction.remove(testFragment).commit();

    }
}
