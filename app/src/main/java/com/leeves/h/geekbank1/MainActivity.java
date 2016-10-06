package com.leeves.h.geekbank1;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leeves.h.geekbank1.aidl.AIDLActivity;
import com.leeves.h.geekbank1.xml.SAXParseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity{

    public static final int RESULT_CODE = 1234;
    private static final String TAG = MainActivity.class.getSimpleName();
//    private Button mListViewButton;
//    private Button mGridButton;
//    private LayoutInflater mLayoutInflater;
//    private Button mTextRedButton;
//    private Button mFragmentButton;
//    private Button mHandleButton;
//    private Button mMusicServiceButton;
//    private Button mBroadcastButton;
//    private Button mWebViewButton;
//    private Button mDatabaseButton;
//    private Button mNetworkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        initViews();
        new TestMethodOfDeme(this);
/*        mLayoutInflater = getLayoutInflater();
        mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mLayoutInflater = LayoutInflater.from(MainActivity.this);
       View view = mLayoutInflater.inflate(R.layout.activity_main,null);
        view.findViewById(R.id);*/

        //editText
/*        EditText ed = (EditText) findViewById(R.id.editText3);
        ed.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按事件
                return false;
            }
        });*/

        //当文本改变的时候
/*        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG,"s:"+s.toString()+"  start:"+start+"  count:"+count+"  after:"+after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG,"s:"+s.toString()+"  start:"+start+"  count:"+count+"  before:"+before);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() >=5){
                    Toast.makeText(MainActivity.this,"小肥熊不能超过5个字的",Toast.LENGTH_LONG).show();
                }
                Log.i(TAG,"s:"+s.toString());
            }
        });*/

        //抽取方法，成一个方法

/*        findViewById(R.id.button15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击就返回第一页
                Intent intent = new Intent(MainActivity.this, ListViewAcitvity.class);
                startActivity(intent);

                //跳回第一页
//                Intent intent = new Intent();
//                intent.putExtra(SplashActivity.TITLE,"我是主页，跳转完成");
//                setResult(RESULT_CODE,intent);
//                //可以用finish关闭当前页面，就会回到第一界面
//                finish();
            }
        });*/

        //拖动条
/*        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        findViewById(R.id.seekBar).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });*/

/*        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });*/

        //设置进度条
/*        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(80);*/
    }

    public class ActivityListInfo{
        private String mName;
        private Class mActivityClass;

        public ActivityListInfo(String name, Class activityClass) {
            mName = name;
            mActivityClass = activityClass;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public Class getActivityClass() {
            return mActivityClass;
        }

        public void setActivityClass(Class activityClass) {
            mActivityClass = activityClass;
        }
    }

    public class DemoActivityListAdapter extends BaseAdapter{

        Context mContext;
        List<ActivityListInfo> mActivityListInfos;

        public DemoActivityListAdapter(Context context, List<ActivityListInfo> activityListInfos) {
            mContext = context;
            mActivityListInfos = activityListInfos;
        }

        @Override
        public int getCount() {
            return mActivityListInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return mActivityListInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if(convertView == null){
                LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.item_phone_book_friend,null);
                holder = new Holder();
                holder.mTextView = (TextView) convertView.findViewById(R.id.name_text_views);
                convertView.setTag(holder);
            }else {
                holder = (Holder) convertView.getTag();
            }
            holder.mTextView.setText(mActivityListInfos.get(position).getName());
            return convertView;
        }

       public class Holder{
            TextView mTextView;
        }
    }


//    //转换为json
//    public String getStringByInputStream(InputStream is) {
//        return null;
//    }

    private void initViews() {
//        mListViewButton = (Button) findViewById(R.id.button13);
//        mGridButton = (Button) findViewById(R.id.button14);
//        mTextRedButton = (Button) findViewById(R.id.button15);
//        mFragmentButton = (Button)findViewById(R.id.button12);
//        mHandleButton = (Button)findViewById(R.id.button16);
//        mMusicServiceButton = (Button)findViewById(R.id.button17);
//        mBroadcastButton = (Button) findViewById(R.id.broadcastButton);
//        mWebViewButton = (Button) findViewById(R.id.web_button);
//        mDatabaseButton = (Button) findViewById(R.id.database_test);
//        mNetworkButton = (Button) findViewById(R.id.network_button);
//
//        mListViewButton.setOnClickListener(this);
//        mGridButton.setOnClickListener(this);
//        mTextRedButton.setOnClickListener(this);
//        mFragmentButton.setOnClickListener(this);
//        mHandleButton.setOnClickListener(this);
//        mMusicServiceButton.setOnClickListener(this);
//        mBroadcastButton.setOnClickListener(this);
//        mWebViewButton.setOnClickListener(this);
//        mDatabaseButton.setOnClickListener(this);
//        mNetworkButton.setOnClickListener(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        final List<ActivityListInfo> activityListInfos = new ArrayList<>();
        activityListInfos.add(new ActivityListInfo("listview", ListViewAcitvity.class));
        activityListInfos.add(new ActivityListInfo("GridView", GridViewAcitvity.class));
        activityListInfos.add(new ActivityListInfo("RedButton", TestRedButtonActivity.class));
        activityListInfos.add(new ActivityListInfo("Fragment", TestFragmentActivity.class));
        activityListInfos.add(new ActivityListInfo("Handler", HandlerButtonActivity.class));
        activityListInfos.add(new ActivityListInfo("MusicService", MusicServiceActivity.class));
        activityListInfos.add(new ActivityListInfo("SendBroadcast", SendBroadcastActivity.class));
        activityListInfos.add(new ActivityListInfo("WebViewButton", WebViewButtonActivity.class));
        activityListInfos.add(new ActivityListInfo("DatabaseButton", DatabaseButtonActivity.class));
        activityListInfos.add(new ActivityListInfo("Network", NetworkActivity.class));
        activityListInfos.add(new ActivityListInfo("Download",ThreadActivity.class));
        activityListInfos.add(new ActivityListInfo("AIDL", AIDLActivity.class));
        activityListInfos.add(new ActivityListInfo("BiaduMap", MapTestActivity.class));
        if (listView != null) {
            listView.setAdapter(new DemoActivityListAdapter(MainActivity.this, activityListInfos));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(MainActivity.this, activityListInfos.get(position).getActivityClass()));
                }
            });
        }


    }


    private void handlenIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra(SplashActivity.TITLE);
            UserInfo userInfo = (UserInfo) intent.getSerializableExtra(SplashActivity.USER_INFO);
//            setTitle(title);
            setTitle("名字是："+userInfo.getUserName());
        }else {
            setTitle("没传过来");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart"+TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    //点击事件，通过实现implements View.OnClickListener接口
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.button13:
//                startActivity(new Intent(MainActivity.this, ListViewAcitvity.class));
//                break;
//            case R.id.button14:
//                startActivity(new Intent(MainActivity.this, GridViewAcitvity.class));
//                break;
//            case R.id.button15:
//                startActivity(new Intent(MainActivity.this, TestRedButtonActivity.class));
//                break;
//            case R.id.button12:
//                startActivity(new Intent(MainActivity.this, TestFragmentActivity.class));
//                break;
//            case R.id.button16:
//                startActivity(new Intent(MainActivity.this, HandlerButtonActivity.class));
//                break;
//            case R.id.button17:
//                startActivity(new Intent(MainActivity.this, MusicServiceActivity.class));
//                break;
//            case R.id.broadcastButton:
//                startActivity(new Intent(MainActivity.this, SendBroadcastActivity.class));
//                break;
//            case R.id.web_button:
//                startActivity(new Intent(MainActivity.this,WebViewButtonActivity.class));
//                break;
//            case R.id.database_test:
//                startActivity(new Intent(MainActivity.this,DatabaseButtonActivity.class));
//                break;
//            case R.id.network_button:
//                startActivity(new Intent(MainActivity.this,NetworkActivity.class));
//                break;
//        }
//    }
}
