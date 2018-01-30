package com.zhuanghongji.android.picker.mini.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhuanghongji.android.picker.mini.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 选择器视图内嵌
     * @param view
     */
    public void onNestView(View view) {
        startActivity(new Intent(this, NestActivity.class));
    }

    /**
     * 窗口动画(基于XML)
     * @param view
     */
    public void onAnimationStyle(View view) {
    }

    /**
     * 窗口动画（基于Java）
     * @param view
     */
    public void onAnimator(View view) {
    }

    /**
     * 年月日选择
     * @param view
     */
    public void onYearMonthDayPicker(View view) {
    }

    /**
     * 年月日时间选择
     * @param view
     */
    public void onYearMonthDayTimePicker(View view) {
    }

    /**
     * 年月选择
     * @param view
     */
    public void onYearMonthPicker(View view) {
    }

    /**
     * 月日选择
     * @param view
     */
    public void onMonthDayPicker(View view) {
    }

    /**
     * 时间选择
     * @param view
     */
    public void onTimePicker(View view) {
    }

    /**
     * 单项选择
     * @param view
     */
    public void onOptionPicker(View view) {
    }

    /**
     * 单项选择（支持对象）
     * @param view
     */
    public void onSinglePicker(View view) {
    }

    /**
     * 双项选择
     * @param view
     */
    public void onDoublePicker(View view) {
    }

    /**
     * 双项选择（实现时间选择）
     * @param view
     */
    public void onBusinessTimePicker(View view) {
    }

    /**
     * 多项选择
     * @param view
     */
    public void onMultiplePicker(View view) {
    }

    /**
     * 二三级联动选择
     * @param view
     */
    public void onLinkagePicker(View view) {
    }

    /**
     * 星座选择
     * @param view
     */
    public void onConstellationPicker(View view) {
    }

    /**
     * 数字选择（如身高、体重、年龄）
     * @param view
     */
    public void onNumberPicker(View view) {
    }

    /**
     * 地址选择（包括省级、地级、县级）
     * @param view
     */
    public void onAddressPicker(View view) {
    }

    /**
     * 地址选择（使用不同的数据源解析）
     * @param view
     */
    public void onAddress4Picker(View view) {

    }

    /**
     * 地址选择（只包括地级、县级）
     * @param view
     */
    public void onAddress2Picker(View view) {
    }

    /**
     * 地址选择（只包括省级、地级）
     * @param view
     */
    public void onAddress3Picker(View view) {
    }
}
