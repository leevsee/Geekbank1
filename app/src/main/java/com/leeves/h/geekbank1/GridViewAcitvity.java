package com.leeves.h.geekbank1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.leeves.h.geekbank1.adapter.PhoneBookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h on 2016/7/11.
 */
public class GridViewAcitvity extends Activity {


    private GridView mPhonewBookGridView;
    private List<UserInfo> userInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //找到listview
        setContentView(R.layout.activity_gridview);
        mPhonewBookGridView = (GridView) findViewById(R.id.grid_view);
        userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("超级小笨猫",12));
        userInfos.add(new UserInfo("超级聪明猫",34));
        userInfos.add(new UserInfo("超级肥肥猫",56));
        userInfos.add(new UserInfo("超级瘦瘦猫",78));
        userInfos.add(new UserInfo("可爱小笨猫",90));
        userInfos.add(new UserInfo("可爱小笨猫",90));
        userInfos.add(new UserInfo("可爱小笨猫",90));
        userInfos.add(new UserInfo("可爱小笨猫",90));
        userInfos.add(new UserInfo("可爱小笨猫",90));
        //把数据传进去
        final PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(GridViewAcitvity.this, userInfos);
        mPhonewBookGridView.setAdapter(phoneBookAdapter);

        //
        phoneBookAdapter.notifyDataSetChanged();

        //点击时弹出名字
        mPhonewBookGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    //新建另外一批数据
                    userInfos.clear();
                    //替换数据
                    userInfos.add(new UserInfo("超级小笨狗狗狗狗",12223));
                    userInfos.add(new UserInfo("超级聪明狗狗狗",34445));
                    //刷新listview，让它更新自己的视图
                    phoneBookAdapter.refreshData(userInfos);
                    //刷新数据，在phoneBookAdapter中刷新或者这里刷新也可以
                    phoneBookAdapter.notifyDataSetChanged();
                }
                Toast.makeText(GridViewAcitvity.this,userInfos.get(position).getUserName(),Toast.LENGTH_SHORT).show();
            }
        });

        //长按时弹出年龄
        mPhonewBookGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewAcitvity.this,String.valueOf(userInfos.get(position).getAge()),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }



}
