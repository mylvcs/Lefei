package com.example.wangmengyun.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.wangmengyun.lefei.R;


public class FlightView {
    private ListView lv_list;


    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    public FlightView(Activity context) {
        mContext = context;
        // 为之后将Layout转化为view时用
        mInflater = LayoutInflater.from(mContext);
    }
    private void createView() {
       initView();
    }
    /**
     * 初始化控件
     */
    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.activity_flight, null);
//        lv_list = (ListView) mCurrentView.findViewById(R.id.);
//        adapter = new ExercisesAdapter(mContext);
//        initData();
//        adapter.setData(ebl);
//        lv_list.setAdapter(adapter);
    }
    /**
     * 设置数据
     */
    private void initData() {
//        ebl = new ArrayList<ExercisesBean>();
//        for (int i = 0; i < 10; i++) {
//            ExercisesBean bean = new ExercisesBean();
//            bean.id=(i + 1);
//            switch (i) {
//
//            }
//            ebl.add(bean);
//        }
    }
    /**
     * 获取当前在导航栏上方显示对应的View
     */
    public static View getView() {
//        if (mCurrentView == null) {qq
//            createView();
//        }
//        return mCurrentView;
        return null;
    }
    /**
     * 显示当前导航栏上方所对应的view界面
     */
    public static void showView() {
//        if (mCurrentView == null) {
//            createView();
//        }
//        mCurrentView.setVisibility(View.VISIBLE);}
    }
}