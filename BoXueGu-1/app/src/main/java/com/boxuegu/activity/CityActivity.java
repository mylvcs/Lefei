package com.boxuegu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.boxuegu.R;
import com.boxuegu.bean.SearchBean;

/**
 * Created by wangmengyun on 2018/3/4.
 */


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CityActivity extends Activity {
    private ListView list_view;
    private EditText search;
    private View mCurrentView;
    private List<SearchBean> mData;
    private Inflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        list_view = (ListView) findViewById(R.id.list_view);
        initData();
        //initView();

       final MyAdapter mAdapter = new MyAdapter(this);//得到一个MyAdapter对象
        list_view.setAdapter(mAdapter);
        search = (EditText) findViewById(R.id.search);
        //搜索关键字
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String a = search.getText().toString();
                //调用适配器里面的搜索方法
                mAdapter.SearchCity(a);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    /**
     * 添加一个得到数据的方法，方便使用
//     */
//    private ArrayList<String> get() {
//        /**为动态数组添加数据*/
//
//        ArrayList<String>  list = new ArrayList<String>();
//
//        return list;
//    }

    /**
     * 新建一个类继承BaseAdapter，实现视图与数据的绑定
     */
    private class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
        private List<String> olist;
        private List<String> list;

        /**
         * 构造函数
         */
        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
   //         this.list = getDate();
            this.olist = list;
        }

        public void SearchCity(String city) {
            this.list = Search(city);
            notifyDataSetChanged();
        }

        private List<String> Search(String city) {
            //如果查询的值不是空的就走进来然后返回搜索后的值，否则返回原本的值
            if (city != null && city.length() > 0) {
                //new一个新的容器
                ArrayList<String> area = new ArrayList<String>();
                //循环olist集合
                for (String a : this.olist) {
                    //判断a里面如果包含了搜索的值，有就添加，没有否则就不添加
                    if (a.toString().indexOf(city) != -1) {
                        area.add(a);
                    }
                }
                return area;
            } else {
                return this.olist;
            }

        }

        @Override
        public int getCount() {
            return list.size();//返回数组的长度
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 书中详细解释该方法
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewholder;
            //观察convertView随ListView滚动情况
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.flight_list_item, null);
                viewholder = new ViewHolder();
                /**得到各个控件的对象*/
                viewholder.text = (TextView) convertView.findViewById(R.id.list_view);
                convertView.setTag(viewholder);//绑定ViewHolder对象
            } else {
                viewholder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            /**设置TextView显示的内容，即我们存放在动态数组中的数据*/
            viewholder.text.setText(list.get(position));
            return convertView;
        }

   }

    private void initData() {
        mData = new ArrayList<SearchBean>();
        SearchBean bean1= new SearchBean();
        bean1.setSearchCity("Shanghai");
        SearchBean bean2= new SearchBean();
        bean2.setSearchCity("Beijing");
        SearchBean bean3= new SearchBean();
        bean3.setSearchCity("Chengdu");
        SearchBean bean4= new SearchBean();
        bean4.setSearchCity("Chongqing");
        SearchBean bean5= new SearchBean();
        bean5.setSearchCity("Nanjing");
        mData.add(bean1);
        mData.add(bean2);
        mData.add(bean3);
        mData.add(bean4);
        mData.add(bean5);

    }



    class ViewHolder {

         TextView text;


    }
}
