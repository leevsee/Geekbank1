package com.leeves.h.geekbank1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by h on 2016/7/15.
 */
public class TestRedButton extends View implements View.OnClickListener{

    private Paint mPaint;
    private Rect mRect;
    private int mNumber = 20;
    private int mBackgroundColor;
    private int mTextSize;

    public TestRedButton(Context context) {
        this(context,null);
    }

    public TestRedButton(Context context, AttributeSet attrs) {
        this(context, null,0);
    }

    public TestRedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    /**
     * init the view
     */
    private void init(Context context,AttributeSet attrs) {
        mPaint = new Paint();
        mRect = new Rect();
        this.setOnClickListener(this);

        //所有的属性读到
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TestRedButton);

        //设置属性
        mBackgroundColor = typedArray.getColor(R.styleable.TestRedButton_backgroundColor,Color.BLUE);

        mTextSize = typedArray.getDimensionPixelSize(R.styleable.TestRedButton_textSize,18);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置颜色
        mPaint.setColor(mBackgroundColor);
        //画一个圆形   float cx, float cy, float radius
        canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/2,mPaint);

        //设置颜色和字体大小
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(200);

        //mNumber没有赋值，初始值是0
        String text = String.valueOf(mNumber);
        mPaint.getTextBounds(text,0,text.length(),mRect);

        int textWidth = mRect.width();
        int textHeight = mRect.height();

        //文本框左下角的点就是 x ，y
        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+textHeight/2,mPaint);
    }

    @Override
    public void onClick(View v) {
        //点击一次减一
        if(mNumber > 0){
            mNumber--;
        }else {
            mNumber = 20;
        }
        //刷新数据
        invalidate();
    }
}
