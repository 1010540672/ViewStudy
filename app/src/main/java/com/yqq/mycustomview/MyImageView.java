package com.yqq.mycustomview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/8/4 0004.
 *
 * 此种自定义view 不需要自己处理wrap_content 和padding
 *
 * com.yqq.mycustomview.MyImageView
 */
public class MyImageView extends ImageView {
    private static  final String TAG="MyImageView";
    private int  mLastX=0;
    private int mLastY=0;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y= (int) event.getY();
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                Log.e(TAG,"====down====");
                mLastX=x;
                mLastY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"====move====getLeft()="+getLeft());
                int offsetX=x-mLastX;
                int offsetY=y-mLastY;
                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"====up====");
                layout(100,100,100+getWidth(),100+getHeight());
                break;



        }


        return true;
    }


}
