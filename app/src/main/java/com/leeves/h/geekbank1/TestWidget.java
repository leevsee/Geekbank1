package com.leeves.h.geekbank1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Function：
 * Created by h on 2016/8/12.
 *
 * @author Leeves
 */
public class TestWidget extends AppWidgetProvider {

    public static final String WIDGET_BUTTON_ACTION = "widget_button_action";
    public RemoteViews mRemoteViews ;
    //每接收一次广播消息就调用一次，使用频繁
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        //判断intent里面的action是否为空且等于该Action
        if (intent != null && TextUtils.equals(intent.getAction(),WIDGET_BUTTON_ACTION)){
            Log.i(WIDGET_BUTTON_ACTION,"be clicked");
            //widget要使用到的RemoteViews
            instanceRemoteViews(context);
            mRemoteViews.setTextViewText(R.id.widget_textView,"be clicked");
            mRemoteViews.setTextColor(R.id.widget_textView, Color.RED);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context,TestWidget.class);
            //如果是在onUpdate()方法外，更新AppWidget界面，传递的是componentName
            appWidgetManager.updateAppWidget(componentName,mRemoteViews);
        }
    }

    private void instanceRemoteViews(Context context) {
        mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
    }

    //每次更新都调用一次该方法，使用频繁
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //获得当前布局widget
        instanceRemoteViews(context);

        //使用Intent传递参数，即action
        Intent intent = new Intent();
        intent.setClass(context,TestWidget.class);
        intent.setAction(WIDGET_BUTTON_ACTION);
        //pending表示即将发生或来临的事情；Intent 是及时启动，intent 随所在的activity 消失而消失
        // 用Intent实例化一个PendingIntent,利用PendingIntent的getService方法来启动一个服务
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        //设置点击事件
        mRemoteViews.setOnClickPendingIntent(R.id.widget_button,pendingIntent);
        //在onUpdate中，更新AppWidget界面，传递的是appWidgetIds
        appWidgetManager.updateAppWidget(appWidgetIds, mRemoteViews);
    }

    //每删除一个就调用一次
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    //当该Widget第一次添加到桌面是调用该方法，可添加多次但只第一次调用
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    //当最后一个该Widget删除是调用该方法，注意是最后一个
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
