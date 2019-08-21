package com.cyh.androidmodular;


import android.os.Handler;

/**
* 自定义时间倒计时
*@author cyh
*created at 2019/8/21 14:36
*/
public class CustomCountDownTimer implements Runnable{
    private final ICountDownHandler countDownHandler;
    private int time;//总时间
    private int countDownTime;//当前倒计时
    private Handler mHandler;
    private Boolean isRun;//记录是否可运行

    //1、实时回调 当前时间及倒计时到几秒  用到观察者模式
    //2、支持动态传入总时间
    //3、每过1秒，总秒数减一
    //4、倒计时结束时，要回调的状态
    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler){
        mHandler = new Handler();
        this.time = time;
        this.countDownTime = time;
        this.countDownHandler = countDownHandler;
    }

    //具体执行方法
    @Override
    public void run() {
        if (isRun){
            if (countDownHandler != null){
                countDownHandler.onTicker(countDownTime);
            }
            if (countDownTime == 0){
                cancel();
                if (countDownHandler != null){
                    countDownHandler.onFinish();
                }
            }else {
                countDownTime = time--;
                mHandler.postDelayed(this,1000);
            }
        }
    }
    //开启run方法进入倒计时
    public void start(){
        isRun = true;
        mHandler.post(this);
    }

    //取消handler倒计时执行
    public void cancel(){
        mHandler.removeCallbacks(this);
        isRun = false;
    }

    //观察者回调接口
    public interface ICountDownHandler {
        void onTicker(int time);//倒计时实时回调
        void onFinish();//完成时状态回调
    }
}
