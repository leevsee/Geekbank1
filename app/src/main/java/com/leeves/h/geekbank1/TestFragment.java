package com.leeves.h.geekbank1;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by h on 2016/7/21.
 */
public class TestFragment extends Fragment {

    public static final String TAG = TestFragment.class.getSimpleName();
    public static final String SMANE = "name";
    public static final String SNUMBER = "number";
    private String mMName;
    private int mMNumber;

    //传递数据
    public static TestFragment newInstance(String name,int number) {

        //设置绑定类
        Bundle bundle = new Bundle();
        bundle.putString(SMANE,name);
        bundle.putInt(SNUMBER,number);

        TestFragment fragment = new TestFragment();
        //把数据绑定到frament中
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"Fragment_onCreate"+TAG);
        super.onCreate(savedInstanceState);

        //获得绑定数据
        Bundle bundle = getArguments();
        if (bundle != null){
            mMName = bundle.getString(SMANE);
            mMNumber = bundle.getInt(SNUMBER);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"Fragment_onCreateView");

        //把.xml中的布局找出，变成view
        View view = inflater.inflate(R.layout.item_phone_book_friend,container,false);
        //在view中找ID
        TextView nameTextView = (TextView) view.findViewById(R.id.name_text_views);
        //设置值
        nameTextView.setText(mMName);
//        nameTextView.setText("4564");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.i(TAG,"Fragment_onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"Fragment_onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
