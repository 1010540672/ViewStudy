package com.yqq.myscroller;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import static android.R.attr.offset;

/**
 * Created by Administrator on 2017/8/4 0004.
 *
 * 继承 view 需要支持 wrap_content  和padding.
 * 重写onDraw方法
 */
//com.yqq.myscroller.MyScrollerView
public class MyScrollerView extends View {

private static  final String TAG="MyScrollerView";
    private Paint mPaint;
    private int mColor=Color.RED;
    private int mLastX=0;
    private int mLastY=0;
    private Scroller mScroller;
    public MyScrollerView(Context context) {
        super(context);
        init();
    }

    public MyScrollerView(Context context, @Nullable AttributeSet attrs) {
        //支持自定义属性
      this(context, attrs, 0);
        mScroller=new Scroller(context);
    }

    public MyScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //支持自定义属性
        TypedArray arry=context.obtainStyledAttributes(attrs,R.styleable.MyScrollerView);
        mColor=arry.getColor(R.styleable.MyScrollerView_test_attr,Color.RED);
        arry.recycle();
        mScroller=new Scroller(context);
        init();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //事件处理
        int x=(int)event.getX();
        int y= (int)  event.getY();
        Log.e(TAG,"x==="+x);
        Log.e(TAG,"y==="+y);
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"=======down======");

                mLastX=x;
                mLastY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"=======move=====");
                //计算位移
                int offsetX=x-mLastX;
                int offsetY=y-mLastY;

                //方法一 利用view 的layout方法进行滑动
               // layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
            //方法二  对left和right进行偏移
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
                //方法三布局参数移动 LinearLayout为父容器
//                LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) getLayoutParams();
//                params.leftMargin=getLeft()+offsetX;
//                params.topMargin=getTop()+offsetY;
//                params.bottomMargin=getBottom()+offsetY;
//                params.rightMargin=getRight()+offsetX;
//                setLayoutParams(params);

                //方法四 使用 ViewGroup.MarginLayoutParams 实现
//                ViewGroup.MarginLayoutParams params= (ViewGroup.MarginLayoutParams) getLayoutParams();
//                params.topMargin=getTop()+offsetY;
//                params.leftMargin=getLeft()+offsetX;
//                params.rightMargin=getRight()+offsetX;
//                params.bottomMargin=getBottom()+offsetY;
//                setLayoutParams(params);

                //方法五    使用scrollBy滑动
                ((View)getParent()).scrollBy(-offsetX,-offsetY);



                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"=======up=====");
                break;



        }



        return true;



    }


    private void  init(){
        mPaint=new Paint();
        mPaint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      int  paddingTop=   getPaddingTop();
        int  paddingBottom=   getPaddingBottom();
        int  paddingLeft=  getPaddingLeft();
      int paddingRight=  getPaddingRight();

     canvas.drawRect(paddingLeft+100f,paddingTop+200f,paddingRight+300f,paddingBottom+400,mPaint);

      //  canvas.drawRect(100f,200f,300f,400f,mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize=MeasureSpec.getSize(heightMeasureSpec);
        if(widthMeasureMode==MeasureSpec.AT_MOST&&heightMeasureMode==MeasureSpec.AT_MOST){
         setMeasuredDimension(150,150);
        }else if(widthMeasureMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(150,heightMeasureSize);
        }else if(heightMeasureMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthMeasureSize,150);
        }


    }

    //startScroll 平滑移动一定的距离回调
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            Log.e(TAG,"========computeScroll=========");
            ((View) getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            //通过不断的重绘不断的调用computeScroll方法
            invalidate();
        }
    }




    public void  smoothScrollTo(int destX,int destY){
        int scrollX=getScrollX();
        int scrollY=getScrollY();
        int deltaX=destX-scrollX;
        int deltaY=destY-scrollY;
        //5s完成弹性滑动
        mScroller.startScroll(scrollX,scrollY,deltaX,deltaY,5000);
        invalidate();


    }



}
