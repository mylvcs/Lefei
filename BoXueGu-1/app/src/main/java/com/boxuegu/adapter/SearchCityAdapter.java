//package com.boxuegu.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.database.DataSetObserver;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ListAdapter;
//import android.widget.TextView;
//
//import com.boxuegu.R;
//import com.boxuegu.activity.CityActivity.ViewHolder;
//import com.boxuegu.bean.SearchBean;
//
//import java.util.List;
//
///**
// * Created by wangmengyun on 2018/3/4.
// */
//
//public class SearchCityAdapter extends BaseAdapter {
//    private Context mContext;
//
//    private List<SearchBean> mData;
//
//
//    public SearchCityAdapter(Context context,List<SearchBean> data) {
//        mContext = context;
//        mData = data;
//
//    }
//
//    @Override
//    public int getCount() {
//        return mData.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return mData.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int position, View view, ViewGroup viewGroup) {
//
//        ViewHolder mViewHoler = new ViewHolder();
//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        view= layoutInflater.inflate(R.layout.activity_city, null);
//        mViewHoler.text= (TextView) view.findViewById(R.id.list_view);
//        view.setTag(mViewHoler);
//
//        SearchBean bean = (SearchBean) getItem(position);
//        if(bean != null){
//            mViewHoler.text.setText(bean.getSearchCity());
//        }
//        return view;
//    }
//
//
//}
