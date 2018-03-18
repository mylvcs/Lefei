package com.example.wangmengyun.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.wangmengyun.Bean.ExercisesBean;
import com.example.wangmengyun.adapter.ExercisesAdapter;
import com.example.wangmengyun.lefei.R;

import java.util.List;



public class TicketView {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> ebl;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;

    public TicketView(Activity context) {
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
        mCurrentView = mInflater
                .inflate(R.layout.main_view_exercises, null);
        lv_list = (ListView) mCurrentView.findViewById(R.id.lv_list);
        adapter = new ExercisesAdapter(mContext);
        //    initData();
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }
    /**
     * 设置数据
     */
//        private void initData() {
//          //TODO
//
//            }
//        }

    /**
     * 获取当前在导航栏上方显示对应的View
     */
    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }

    /**
     * 显示当前导航栏上方所对应的view界面
     */
    public void showView() {
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
