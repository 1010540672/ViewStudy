package com.yqq.myscroller;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yqq.mycustomview.ImageViewActivity;

public class MainActivity extends AppCompatActivity {
    MyScrollerView myScrollerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myScrollerView=(MyScrollerView)findViewById(R.id.testView);
        //弹性滑动方法一
       // myScrollerView.smoothScrollTo(-600,-800);
        //弹性滑动方法二使用属性动画使view滑动（3.0以上移动位置）
      ObjectAnimator.ofFloat(myScrollerView,"translationX",0,600).setDuration(10000).start();
       ObjectAnimator.ofFloat(myScrollerView,"translationY",0,600).setDuration(10000).start();
        //弹性滑动方法三使用View动画使view滑动(只移动内容)
      // myScrollerView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));


    }

    public void doWork(View view){
        startActivity(new Intent(MainActivity.this, ImageViewActivity.class));



    }




}
