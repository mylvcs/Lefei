//package com.boxuegu.view;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ListView;
//
//import com.boxuegu.R;
//import com.boxuegu.adapter.SearchCityAdapter;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
///**
// * Created by wangmengyun on 2018/3/4.
// */
//
//public class CityView {
//
//    private ListView lv_list;
// //   private SearchCityAdapter adapter;
//    private Activity mContext;
//    private LayoutInflater mInflater;
//    private View mCurrentView;
//
//    public CityView(Activity context) {
//        mContext = context;
//        // 为之后将Layout转化为view时用
//        mInflater = LayoutInflater.from(mContext);
//    }
//
//    private void createView() {
//        initView();
//    }
//
//    /**
//     * 初始化控件
//     */
//    private void initView() {
//        mCurrentView = mInflater
//                .inflate(R.layout.flight_list_item, null);
//        lv_list = (ListView) mCurrentView.findViewById(R.id.lv_list);
//        adapter = new SearchCityAdapter(mContext);
//        initData();
//   //     adapter.setData(ebl);
//        lv_list.setAdapter(adapter);
//    }
//
//    private void initData() {
//
////TODO
//    }
//
//    public View getView() {
//        if (mCurrentView == null) {
//            createView();
//        }
//        return mCurrentView;
//    }
//    /**
//     * 显示当前导航栏上方所对应的view界面
//     */
//    public void showView() {
//        if (mCurrentView == null) {
//            createView();
//        }
//        mCurrentView.setVisibility(View.VISIBLE);
//    }
//
//    String[] city = {"上海","北京","广州","南京","杭州","成都","重庆","天津" ,};
//    List<String> Cities= new ArrayList<String>(Arrays.asList(city));
//
//  //  adapter =new ArrayAdapter<String>( getActivity(), R.layout.activity_city, R.id.lv_list, Cities);
//
//
//}
//
