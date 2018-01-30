package com.zhuanghongji.android.picker.mini.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhuanghongji.android.picker.mini.R;
import com.zhuanghongji.android.picker.mini.picker.common.util.ConvertUtils;
import com.zhuanghongji.android.picker.mini.picker.common.util.DateUtils;
import com.zhuanghongji.android.picker.mini.picker.common.util.LogUtils;
import com.zhuanghongji.android.picker.mini.picker.wheel.WheelView;
import com.zhuanghongji.android.picker.mini.picker.wheel.entity.City;
import com.zhuanghongji.android.picker.mini.picker.wheel.entity.County;
import com.zhuanghongji.android.picker.mini.picker.wheel.entity.Province;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.AddressPicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.DatePicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.DateTimePicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.DoublePicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.LinkagePicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.NumberPicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.OptionPicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.SinglePicker;
import com.zhuanghongji.android.picker.mini.picker.wheel.picker.TimePicker;
import com.zhuanghongji.android.picker.mini.sample.temp.AddressInitTask;
import com.zhuanghongji.android.picker.mini.sample.temp.AddressPickTask;
import com.zhuanghongji.android.picker.mini.sample.temp.CustomHeaderAndFooterPicker;
import com.zhuanghongji.android.picker.mini.sample.temp.GoodsCategory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
        final NumberPicker picker = new NumberPicker(this);
        picker.setItemWidth(200);
        View headerView = View.inflate(mContext, R.layout.picker_header, null);
        final TextView titleView = (TextView) headerView.findViewById(R.id.picker_title);
        titleView.setText("自定义顶部视图");
        headerView.findViewById(R.id.picker_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.dismiss();
            }
        });
        picker.setHeaderView(headerView);
        picker.setAnimationStyle(R.style.Animation_CustomPopup);
        picker.setCycleDisable(false);
        picker.setOffset(5);//偏移量
        picker.setRange(10.5, 20, 1.5);//数字范围
        picker.setSelectedItem(18.0);
        picker.setLabel("℃");
        picker.setOnWheelListener(new NumberPicker.OnWheelListener() {
            @Override
            public void onWheeled(int index, Number item) {
                titleView.setText(String.valueOf(item.floatValue()));
            }
        });
        picker.show();
    }

    /**
     * 窗口动画（基于Java）
     * @param view
     */
    public void onAnimator(View view) {
        CustomHeaderAndFooterPicker picker = new CustomHeaderAndFooterPicker(this);
        picker.setOffset(3);                //显示的条目的偏移量，条数为（offset*2+1）
        picker.setGravity(Gravity.CENTER);  //居中
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                showToast("index=" + position + ", item=" + option);
            }
        });
        picker.show();
    }

    /**
     * 年月日选择
     * @param view
     */
    public void onYearMonthDayPicker(View view) {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(2111, 1, 11);
        picker.setRangeStart(2016, 8, 29);
        picker.setSelectedItem(2050, 10, 14);
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                showToast(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 年月日时间选择
     * @param view
     */
    public void onYearMonthDayTimePicker(View view) {
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        picker.setDateRangeStart(2017, 1, 1);
        picker.setDateRangeEnd(2025, 11, 11);
        picker.setTimeRangeStart(9, 0);
        picker.setTimeRangeEnd(20, 30);
        picker.setTopLineColor(0x99FF0000);
        picker.setLabelTextColor(0xFFFF0000);
        picker.setDividerColor(0xFFFF0000);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                showToast(year + "-" + month + "-" + day + " " + hour + ":" + minute);
            }
        });
        picker.show();
    }

    /**
     * 年月选择
     * @param view
     */
    public void onYearMonthPicker(View view) {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
        picker.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        picker.setWidth((int) (picker.getScreenWidthPixels() * 0.6));
        picker.setRangeStart(2016, 10, 14);
        picker.setRangeEnd(2020, 11, 11);
        picker.setSelectedItem(2017, 9);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                showToast(year + "-" + month);
            }
        });
        picker.show();
    }

    /**
     * 月日选择
     * @param view
     */
    public void onMonthDayPicker(View view) {
        DatePicker picker = new DatePicker(this, DatePicker.MONTH_DAY);
        picker.setUseWeight(false);
        picker.setTextPadding(ConvertUtils.toPx(this, 15));//加宽显示项
        picker.setGravity(Gravity.CENTER);//弹框居中
        picker.setRangeStart(5, 1);
        picker.setRangeEnd(12, 31);
        picker.setSelectedItem(10, 14);
        picker.setOnDatePickListener(new DatePicker.OnMonthDayPickListener() {
            @Override
            public void onDatePicked(String month, String day) {
                showToast(month + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 时间选择
     * @param view
     */
    public void onTimePicker(View view) {
        TimePicker picker = new TimePicker(this, TimePicker.HOUR_24);
        picker.setUseWeight(false);
        picker.setCycleDisable(false);
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(false);
        picker.setTextPadding(ConvertUtils.toPx(this, 15));
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                showToast(hour + ":" + minute);
            }
        });
        picker.show();
    }

    /**
     * 单项选择
     * @param view
     */
    public void onOptionPicker(View view) {
        OptionPicker picker = new OptionPicker(this, new String[]{
                "第一项", "第二项", "这是一个很长很长很长很长很长很长很长很长很长的很长很长的很长很长的项"
        });
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setShadowColor(Color.RED, 40);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(true);
        picker.setTextSize(11);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                showToast("index=" + index + ", item=" + item);
            }
        });
        picker.show();
    }

    /**
     * 单项选择（支持对象）
     * @param view
     */
    public void onSinglePicker(View view) {
        List<GoodsCategory> data = new ArrayList<>();
        data.add(new GoodsCategory(1, "食品生鲜"));
        data.add(new GoodsCategory(2, "家用电器"));
        data.add(new GoodsCategory(3, "家居生活"));
        data.add(new GoodsCategory(4, "医疗保健"));
        data.add(new GoodsCategory(5, "酒水饮料"));
        data.add(new GoodsCategory(6, "图书音像"));
        SinglePicker<GoodsCategory> picker = new SinglePicker<>(this, data);
        picker.setCanceledOnTouchOutside(false);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(false);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<GoodsCategory>() {
            @Override
            public void onItemPicked(int index, GoodsCategory item) {
                showToast("index=" + index + ", id=" + item.getId() + ", name=" + item.getName());
            }
        });
        picker.show();
    }

    /**
     * 双项选择
     * @param view
     */
    public void onDoublePicker(View view) {
        final ArrayList<String> firstData = new ArrayList<>();
        firstData.add("2017年5月2日星期二");
        firstData.add("2017年5月3日星期三");
        firstData.add("2017年5月4日星期四");
        firstData.add("2017年5月5日星期五");
        firstData.add("2017年5月6日星期六");
        final ArrayList<String> secondData = new ArrayList<>();
        secondData.add("电动自行车");
        secondData.add("二轮摩托车");
        secondData.add("私家小汽车");
        secondData.add("公共交通汽车");
        final DoublePicker picker = new DoublePicker(this, firstData, secondData);
        picker.setDividerVisible(true);
        picker.setCycleDisable(false);
        picker.setSelectedIndex(0, 0);
        picker.setFirstLabel("于", null);
        picker.setSecondLabel("骑/乘", "出发");
        picker.setTextSize(12);
        picker.setContentPadding(15, 10);
        picker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                showToast(firstData.get(selectedFirstIndex) + " " + secondData.get(selectedSecondIndex));
            }
        });
        picker.show();
    }

    /**
     * 双项选择（实现时间选择）
     * @param view
     */
    public void onBusinessTimePicker(View view) {
        final ArrayList<String> hours = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            hours.add(DateUtils.fillZero(i));
        }
        final ArrayList<String> minutes = new ArrayList<>();
        minutes.add("00");
        minutes.add("15");
        minutes.add("30");
        DoublePicker picker = new DoublePicker(this, hours, minutes);
        picker.setCanceledOnTouchOutside(true);
        picker.setTopLineColor(0xFFFB2C3C);
        picker.setSubmitTextColor(0xFFFB2C3C);
        picker.setCancelTextColor(0xFFFB2C3C);
        picker.setLineSpaceMultiplier(2.2f);
        picker.setTextSize(15);
        picker.setTitleText("营业时间");
        picker.setContentPadding(10, 8);
        picker.setUseWeight(true);
        picker.setFirstLabel("", ":");
        picker.setSecondLabel("", "");
        picker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                showToast(hours.get(selectedFirstIndex) + ":" + minutes.get(selectedSecondIndex));
            }
        });
        picker.show();
    }

    /**
     * 二三级联动选择
     * @param view
     */
    public void onLinkagePicker(View view) {
        // 联动选择器的更多用法，可参见AddressPicker和CarNumberPicker
        LinkagePicker.DataProvider provider = new LinkagePicker.DataProvider() {

            @Override
            public boolean isOnlyTwo() {
                return true;
            }

            @NonNull
            @Override
            public List<String> provideFirstData() {
                ArrayList<String> firstList = new ArrayList<>();
                firstList.add("12");
                firstList.add("24");
                return firstList;
            }

            @NonNull
            @Override
            public List<String> provideSecondData(int firstIndex) {
                ArrayList<String> secondList = new ArrayList<>();
                for (int i = 1; i <= (firstIndex == 0 ? 12 : 24); i++) {
                    String str = DateUtils.fillZero(i);
                    if (firstIndex == 0) {
                        str += "￥";
                    } else {
                        str += "$";
                    }
                    secondList.add(str);
                }
                return secondList;
            }

            @Nullable
            @Override
            public List<String> provideThirdData(int firstIndex, int secondIndex) {
                return null;
            }

        };
        LinkagePicker picker = new LinkagePicker(this, provider);
        picker.setCycleDisable(true);
        picker.setUseWeight(true);
        picker.setLabel("小时制", "点");
        picker.setSelectedIndex(0, 8);
        // picker.setSelectedItem("12", "9");
        picker.setContentPadding(10, 0);
        picker.setOnStringPickListener(new LinkagePicker.OnStringPickListener() {
            @Override
            public void onPicked(String first, String second, String third) {
                showToast(first + "-" + second + "-" + third);
            }
        });
        picker.show();
    }

    /**
     * 星座选择
     * @param view
     */
    public void onConstellationPicker(View view) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        OptionPicker picker = new OptionPicker(this,
                isChinese ? new String[]{
                        "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
                        "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"
                } : new String[]{
                        "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer",
                        "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn"
                });
        picker.setCycleDisable(false);  // 不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(30);
        picker.setTopLineColor(0xFFEE0000);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFFEE0000);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFFEE0000);
        picker.setSubmitTextSize(13);
        picker.setTextColor(0xFFEE0000, 0xFF999999);
        WheelView.DividerConfig config = new WheelView.DividerConfig();
        config.setColor(0xFFEE0000);            //线颜色
        config.setAlpha(140);                   //线透明度
        config.setRatio((float) (1.0 / 8.0));   //线比率
        picker.setDividerConfig(config);
        picker.setBackgroundColor(0xFFE1E1E1);
        // picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setCanceledOnTouchOutside(true);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                showToast("index = " + index + ", item = " + item);
            }
        });
        picker.show();
    }

    /**
     * 数字选择（如身高、体重、年龄）
     * @param view
     */
    public void onNumberPicker(View view) {
        NumberPicker picker = new NumberPicker(this);
        picker.setWidth(picker.getScreenWidthPixels() / 2);
        picker.setCycleDisable(false);
        picker.setDividerVisible(false);
        picker.setOffset(2);//偏移量
        picker.setRange(145, 200, 1);//数字范围
        picker.setSelectedItem(172);
        picker.setLabel("厘米");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                showToast("index=" + index + ", item=" + item.intValue());
            }
        });
        picker.show();
    }

    /**
     * 地址选择（包括省级、地级、县级）
     * @param view
     */
    public void onAddressPicker(View view) {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    showToast(province.getAreaName() + city.getAreaName());
                } else {
                    showToast(province.getAreaName() + city.getAreaName() + county.getAreaName());
                }
            }
        });
        task.execute("贵州", "毕节", "纳雍");
    }

    /**
     * 地址选择（只包括地级、县级）
     * @param view
     */
    public void onAddress2Picker(View view) {
        try {
            ArrayList<Province> data = new ArrayList<>();
            String json = ConvertUtils.toString(getAssets().open("city2.json"));
            data.addAll(JSON.parseArray(json, Province.class));
            AddressPicker picker = new AddressPicker(this, data);
            picker.setShadowVisible(true);
            picker.setTextSizeAutoFit(false);
            picker.setHideProvince(true);
            picker.setSelectedItem("贵州", "贵阳", "花溪");
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(Province province, City city, County county) {
                    showToast("province : " + province + ", city: " + city + ", county: " + county);
                }
            });
            picker.show();
        } catch (Exception e) {
            showToast(LogUtils.toStackTraceString(e));
        }
    }

    /**
     * 地址选择（只包括省级、地级）
     * @param view
     */
    public void onAddress3Picker(View view) {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideCounty(true);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                showToast(province.getAreaName() + " " + city.getAreaName());
            }
        });
        task.execute("四川", "阿坝");
    }

    /**
     * 地址选择（使用不同的数据源解析）
     * @param view
     */
    public void onAddress4Picker(View view) {
        new AddressInitTask(this, new AddressInitTask.InitCallback() {
            @Override
            public void onDataInitFailure() {
                showToast("数据初始化失败");
            }

            @Override
            public void onDataInitSuccess(ArrayList<Province> provinces) {
                AddressPicker picker = new AddressPicker((Activity) mContext, provinces);
                picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        String provinceName = province.getName();
                        String cityName = "";
                        if (city != null) {
                            cityName = city.getName();
                            //忽略直辖市的二级名称
                            if (cityName.equals("市辖区") || cityName.equals("市") || cityName.equals("县")) {
                                cityName = "";
                            }
                        }
                        String countyName = "";
                        if (county != null) {
                            countyName = county.getName();
                        }
                        showToast(provinceName + " " + cityName + " " + countyName);
                    }
                });
                picker.show();
            }
        }).execute();
    }
}
