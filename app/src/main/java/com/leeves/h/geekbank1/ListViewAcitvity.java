package com.leeves.h.geekbank1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.leeves.h.geekbank1.adapter.PhoneBookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h on 2016/7/11.
 */
public class ListViewAcitvity extends Activity implements View.OnClickListener{

    public static final String PREFERENCE_NAME = "preference_name";
    public static final String LIST_VIEW_DATA_COUNTS = "list_view_data_counts";
    private ListView mPhonewBookListView;
    private List<UserInfo> userInfos;
    public int mDataCount = 2;
    public PhoneBookAdapter mPhoneBookAdapter;
    public EditText mDataCountEditText;
    public Button mConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //找到listview
        setContentView(R.layout.activity_listview);
        findViewById();
        setData();
        setListence();
    }

    private void setData() {
        //定义SharedPreferences对象
        SharedPreferences sharePreferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        //从SharedPreferences中获取值
        mDataCount = sharePreferences.getInt(LIST_VIEW_DATA_COUNTS,mDataCount);
        //显示当前的值
        mDataCountEditText.setText(String.valueOf(mDataCount));

        userInfos = new ArrayList<>();
        for (int i = 0; i < mDataCount; i++) {
            userInfos.add(new UserInfo("超级小笨猫",12));
        }
        userInfos.add(new UserInfo("可爱小笨猫",90));
        //把数据传进去
        mPhoneBookAdapter = new PhoneBookAdapter(ListViewAcitvity.this, userInfos);
        mPhonewBookListView.setAdapter(mPhoneBookAdapter);
    }

    private void setListence() {
        //
        mPhoneBookAdapter.notifyDataSetChanged();

        //点击时弹出名字
        mPhonewBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    //新建另外一批数据
                    userInfos.clear();
                    //替换数据
                    userInfos.add(new UserInfo("超级小笨狗狗狗狗",12223));
                    userInfos.add(new UserInfo("超级聪明狗狗狗",34445));
                    //刷新listview，让它更新自己的视图
                    mPhoneBookAdapter.refreshData(userInfos);
                    //刷新数据，在phoneBookAdapter中刷新或者这里刷新也可以
                    mPhoneBookAdapter.notifyDataSetChanged();
                }

                Toast.makeText(ListViewAcitvity.this,userInfos.get(position).getUserName(),Toast.LENGTH_SHORT).show();
            }
        });

        //长按时弹出年龄
        mPhonewBookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewAcitvity.this,String.valueOf(userInfos.get(position).getAge()),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mConfirmButton.setOnClickListener(this);
    }

    private void findViewById() {
        mPhonewBookListView = (ListView) findViewById(R.id.list_view);
        mDataCountEditText = (EditText) findViewById(R.id.sharedPreferences_counts);
        mConfirmButton = (Button) findViewById(R.id.sharedPreferences_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sharedPreferences_button :
                String countString = mDataCountEditText.getText().toString();
                mDataCount = Integer.valueOf(countString);
                refreshListView();
                saveData2Preference(mDataCount);
                break;
        }

    }

    private void saveData2Preference(int dataCount) {
        //系统会自动帮我们创建一个XML文件 名字是"preference_name" 实例化SharedPreferences对象
        SharedPreferences sharedPreferences = ListViewAcitvity.this.getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //用putString的方法保存数据
        editor.putInt(LIST_VIEW_DATA_COUNTS,dataCount);
//        editor.putInt(LIST_VIEW_DATA_COUNTS,dataCount+1); //重新存进去就是修改
//        editor.remove(LIST_VIEW_DATA_COUNTS); //里面参数是key
        //提交
//        editor.commit();
        //和网络相关，和IO操作相关的，都要用异步
        //异步后台写数据，另开线程
        editor.apply();

//        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
//            @Override
//            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//            }
//        });

    }
    //刷新视图
    private void refreshListView() {
        userInfos.clear();
        for (int i = 0; i < mDataCount; i++) {
            userInfos.add(new UserInfo("超级小笨猫",12));
        }
        //刷新listview，让它更新自己的视图
        mPhoneBookAdapter.refreshData(userInfos);
        //刷新数据，在phoneBookAdapter中刷新或者这里刷新也可以
        mPhoneBookAdapter.notifyDataSetChanged();
    }
}
