package com.cyh.androidmodular;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
/**
* Splash启动动画Activity
*@author cyh
*created at 2019/8/21 14:36
*/
public class SplashActivity extends AppCompatActivity {
    private VideoView mVideoView;
    private TextView tvCountDown;
    private CustomCountDownTimer customCountDownTimer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mVideoView = findViewById(R.id.videoview_splash);
        tvCountDown= (TextView) findViewById(R.id.tv_splsh_countdown);

        //导入路径
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        //开始播放，播放完成会停止
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        //等播放完成后继续播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        customCountDownTimer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                tvCountDown.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                tvCountDown.setText("跳过");
            }
        });
        customCountDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customCountDownTimer.cancel();
    }
}
