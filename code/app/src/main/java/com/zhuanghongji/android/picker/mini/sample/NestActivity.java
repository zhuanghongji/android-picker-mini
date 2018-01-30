package com.zhuanghongji.android.picker.mini.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuanghongji.android.picker.mini.R;
import com.zhuanghongji.android.picker.mini.picker.common.util.ConvertUtils;
import com.zhuanghongji.android.picker.mini.picker.wheel.WheelView;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.CarNumberPicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.LinkagePicker;

import java.util.Locale;

/**
 * 内嵌选择器
 * <br />
 * Author:李玉江[QQ:1032694760]
 * DateTime:2016/12/16 00:42
 * Builder:Android Studio
 */
public class NestActivity extends BaseActivity {

    private TextView tvTips;
    private WheelView wvSingle;
    private LinearLayout llPickerContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest);
        initView();
        initWheelView();
        initPicker();
    }

    private void initPicker() {
        final CarNumberPicker picker = new CarNumberPicker(this);
        picker.setOffset(3);
        picker.setOnWheelLinkageListener(new LinkagePicker.OnWheelLinkageListener() {
            @Override
            public void onLinkage(int firstIndex, int secondIndex, int thirdIndex) {
                tvTips.setText(String.format(Locale.PRC, "%s : %s",
                        picker.getSelectedFirstItem(), picker.getSelectedSecondItem()));
            }
        });
        // 得到选择器视图，可内嵌到其他视图容器，不需要调用show方法
        View pickerContentView = picker.getContentView();
        llPickerContainer.addView(pickerContentView);
    }

    private void initWheelView() {
        final String[] strings = {"少数民族", "贵州穿青人", "不在56个少数民族之列", "第57个民族"};
        wvSingle.setItems(strings, 2);
        wvSingle.setTextColor(0xFFFF00FF);
        wvSingle.setTextSize(18);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setRatio((float) (1.0 / 10.0));  //线比率
        config.setColor(0xFFFF0000);            //线颜色
        config.setAlpha(100);                   //线透明度
        config.setThick(ConvertUtils.toPx(this, 5));    //线粗
        wvSingle.setDividerConfig(config);
        wvSingle.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                tvTips.setText(String.format(Locale.PRC, "index = %d, item = %s",
                        index, strings[index]));
            }
        });
    }

    private void initView() {
        tvTips = findViewById(R.id.wheelview_tips);
        wvSingle = findViewById(R.id.wheelview_single);
        llPickerContainer = findViewById(R.id.wheelview_container);
    }
}
