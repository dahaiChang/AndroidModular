package com.cyh.androidmodular;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;
/**
* 自定义View，重写onMeasure方法，实现全屏
*@author cyh
*created at 2019/8/21 14:20
*/
public class FullScreenVideoView extends VideoView {

    //主要用于这个直接new出来的对象
    public FullScreenVideoView(Context context) {
        super(context);
    }
    //主要用于xml文件中，支持自定义属性
    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //也是主要用于xml文件中，支持自定义属性，同时支持style样式
    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //继承自View完全自定义 尺寸测量onMeasure
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec 包含两个主要的内容 1、测量模式 2、测量大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0,widthMeasureSpec);
        int height = getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,height);
    }


}
