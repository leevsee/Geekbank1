package com.leeves.h.geekbank1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leeves.h.geekbank1.R;
import com.leeves.h.geekbank1.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h on 2016/7/11.
 */
public class PhoneBookAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflate;
    //    private String[] mNames = {"小笨熊","聪明鹿"};
    private List<UserInfo> mUserInfoList = new ArrayList<>();

    public PhoneBookAdapter(Context context, List<UserInfo> userInfos) {
        mContext = context;
        mUserInfoList = userInfos;
        mLayoutInflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //有多少条数据
//        return mNames.length;
        return mUserInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        //返回一条数据对象
//        return mNames[position];
        return mUserInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 每一行数据显示在界面，用户能够看到
     *
     * @param position
     * @param convertView
     * @param parent
     * @return view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //返回一个视图

        ViewHolder viewHolder;
        //使用if判断视图是否为空，为空就创建，只需使用一次
        if (convertView == null) {
            //将item.xml定义的一个布局找出来，并变成textview
            convertView = mLayoutInflate.inflate(R.layout.item_phone_book_friend, null);
            //初始化ViewHolder
            viewHolder = new ViewHolder();
            //获取控件
            viewHolder.nameTextView    = (TextView) convertView.findViewById(R.id.name_text_views);
            viewHolder.ageTextView     = (TextView) convertView.findViewById(R.id.age_text_views);
            viewHolder.avatarImageView = (ImageView) convertView.findViewById(R.id.avatar_image_views);
            //设置Tag
            convertView.setTag(viewHolder);
        }else {
            //获取Tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //把数据放到视图中
//        nameTextView.setText(mNames[position]);
        viewHolder .nameTextView.setText(mUserInfoList.get(position).getUserName());
        //setText中如果有int，有个参数是resid，资源文件id要把转换成string或者一个整型加一个空格，字符串拼接
        viewHolder .ageTextView.setText(String.valueOf(mUserInfoList.get(position).getAge()));
        viewHolder .avatarImageView.setImageResource(R.drawable.ic_lu);

        //返回是converView，不是其中agaTextView或者nameTextView等
        return convertView;
    }

    //数据缓存
    class ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        ImageView avatarImageView;
    }

    /**
     * 刷新数据
     */
    public void refreshData(List<UserInfo> userInfos) {
        mUserInfoList = userInfos;
        //刷新数据
//        notifyDataSetChanged();
    }

}
