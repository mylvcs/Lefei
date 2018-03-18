package com.example.wangmengyun.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.example.wangmengyun.Bean.City;
import com.example.wangmengyun.Utils.PingYinUtil;
import com.example.wangmengyun.adapter.CityListAdapter;
import com.example.wangmengyun.adapter.SearchResultAdapter;
import com.example.wangmengyun.lefei.R;
import com.example.wangmengyun.sqlite.AllCitySqliteOpenHelper;
import com.example.wangmengyun.sqlite.CitySqliteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class MainActivity2 extends Activity {

    protected static final String TAG = "MainActivity";
//   private MyLetterView myLetterView;//×Ô¶¨ÒåµÄView
    private TextView tvDialog;//Ö÷½çÃæÏÔÊ¾×ÖÄ¸µÄTextView
    private ListView lvCity;//½øÐÐ³ÇÊÐÁÐ±íÕ¹Ê¾
    private EditText etSearch;
    private ListView lvResult;//ËÑË÷½á¹ûÁÐ±íÕ¹Ê¾
    private TextView tvNoResult;//ËÑË÷ÎÞ½á¹ûÊ±ÎÄ×ÖÕ¹Ê¾

    private List<City> allCityList;//ËùÓÐµÄ³ÇÊÐ
    private List<City> hotCityList;//ÈÈÃÅ³ÇÊÐÁÐ±í
    private List<City> searchCityList;//ËÑË÷³ÇÊÐÁÐ±í
    private List<String> recentCityList;//×î½ü·ÃÎÊ³ÇÊÐÁÐ±í

    public CitySqliteOpenHelper cityOpenHelper;//¶Ô±£´æÁË×î½ü·ÃÎÊ³ÇÊÐµÄÊý¾Ý¿â²Ù×÷µÄ°ïÖúÀà
    public SQLiteDatabase cityDb;//±£´æ×î½ü·ÃÎÊ³ÇÊÐµÄÊý¾Ý¿â
    public CityListAdapter cityListAdapter;
    public SearchResultAdapter searchResultAdapter;
    private boolean isScroll=false;
    private boolean mReady=false;
    private Handler handler;
    private OverlayThread overlayThread; //ÏÔÊ¾Ê××ÖÄ¸¶Ô»°¿ò

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        initView();
        initData();
        setListener();

        //³õÊ¼»¯ËùÓÐ³ÇÊÐÁÐ±í
        initAllCityData();
        initRecentVisitCityData();//³õÊ¼»¯×î½ü·ÃÎÊµÄ³ÇÊÐÊý¾Ý
        initHotCityData();//³õÊ¼»¯ÈÈÃÅ³ÇÊÐ
        setAdapter();//ÉèÖÃÊÊÅäÆ÷
        mReady=true;
    }

    private void setAdapter() {
        cityListAdapter = new CityListAdapter(this,allCityList,hotCityList,recentCityList);
        searchResultAdapter=new SearchResultAdapter(this,searchCityList);
        lvCity.setAdapter(cityListAdapter);
        lvResult.setAdapter(searchResultAdapter);
    }


    private void initView() {
//        myLetterView = (MyLetterView) findViewById(R.id.my_letterview);
        tvDialog = (TextView) findViewById(R.id.tv_dialog);
 //       myLetterView.setTextView(tvDialog);
        lvCity=(ListView) findViewById(R.id.lv_city);
        etSearch=(EditText) findViewById(R.id.et_search);
        lvResult=(ListView) findViewById(R.id.lv_result);
        tvNoResult=(TextView) findViewById(R.id.tv_noresult);
    }

    private void setListener() {


            etSearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString()==null||"".equals(s.toString())){

                        lvCity.setVisibility(View.VISIBLE);
                        lvResult.setVisibility(View.GONE);
                        tvNoResult.setVisibility(View.GONE);
                    }else{
                        searchCityList.clear();

                        lvCity.setVisibility(View.GONE);
                        getResultCityList(s.toString());
                        if (searchCityList.size() <= 0) {
                            lvResult.setVisibility(View.GONE);
                            tvNoResult.setVisibility(View.VISIBLE);
                        } else {
                            lvResult.setVisibility(View.VISIBLE);
                            tvNoResult.setVisibility(View.GONE);
                        }
                    }
                }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lvCity.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL
                        || scrollState == SCROLL_STATE_FLING) {
                    isScroll = true;
                }

            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (!isScroll) {
                    return;
                }
                if (mReady) {
                    String text;
                    String name = allCityList.get(firstVisibleItem).getName();
                    String pinyin = allCityList.get(firstVisibleItem).getPinyin();
                    if (firstVisibleItem < 4) {
                        text = name;
                    } else {
                        text = PingYinUtil.converterToFirstSpell(pinyin)
                                .substring(0, 1).toUpperCase();
                    }
                    tvDialog.setText(text);
                    tvDialog.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(overlayThread);
//					Toast.makeText(MainActivity.this,"²âÊÔ",0).show();
//					 ÑÓ³ÙÒ»ÃëºóÖ´ÐÐ£¬ÈÃÖÐ¼äÏÔÊ¾µÄTextViewÎª²»¿É¼û
                    handler.postDelayed(overlayThread,1000);
                }
            }
        });
    }

    private void initData() {
        cityOpenHelper=new CitySqliteOpenHelper(MainActivity2.this);
        cityDb=cityOpenHelper.getWritableDatabase();
        allCityList=new ArrayList<City>();
        hotCityList=new ArrayList<City>();
        searchCityList=new ArrayList<City>();
        recentCityList=new ArrayList<String>();
        handler = new Handler();
        overlayThread = new OverlayThread();
    }

    private void initAllCityData() {

        City city = new City("¶¨Î»", "0"); // µ±Ç°¶¨Î»³ÇÊÐ
        allCityList.add(city);
        city=new City("Shanghai","1");
        allCityList.add(city);
        city=new City("New York","2");
        allCityList.add(city);
        city=new City("Los Angeles","3");
        allCityList.add(city);
        allCityList.addAll(getCityList());
    }

    @SuppressWarnings("unchecked")
    private ArrayList<City> getCityList() {
        SQLiteDatabase db;
        Cursor cursor = null;
        AllCitySqliteOpenHelper op=new AllCitySqliteOpenHelper(MainActivity2.this);
        ArrayList<City> cityList=new ArrayList<City>();
        try {
            op.createDataBase();
            db = op.getWritableDatabase();
            cursor= db.rawQuery("select * from city",null);

            while (cursor.moveToNext()) {
                String cityName=cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
                City city=new City(cityName,cityPinyin);
                cityList.add(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
//            if (cursor == null) throw new AssertionError();
            cursor.close();
        }
        Collections.sort(cityList, comparator);
        return cityList;

    }

    private void initRecentVisitCityData() {
        InsertCity("±±¾©");
        InsertCity("ÉÏº£");
        InsertCity("¹ãÖÝ");
        SQLiteDatabase recentVisitDb = cityOpenHelper.getWritableDatabase();
        Cursor cursor = recentVisitDb.rawQuery("select * from recentcity order by date desc limit 0, 3", null);
        while (cursor.moveToNext()) {
            String recentVisitCityName=cursor.getString(cursor.getColumnIndex("name"));
            recentCityList.add(recentVisitCityName);
        }
        cursor.close();
        recentVisitDb.close();
    }

    private void initHotCityData() {
        City city=new City("±±¾©","2");
        hotCityList.add(city);
        city=new City("ÉÏº£","2");
        hotCityList.add(city);
        city=new City("¹ãÖÝ","2");
        hotCityList.add(city);
        city=new City("ÄÏ¾©","2");
        hotCityList.add(city);
        city=new City("ºÏ·Ê","2");
        hotCityList.add(city);
        city=new City("°²»Õ","2");
        hotCityList.add(city);
        city=new City("í¸É½","2");
        hotCityList.add(city);
        city=new City("ÈÕ±¾","2");
        hotCityList.add(city);
    }

    /**
     * ×Ô¶¨ÒåµÄÅÅÐò¹æÔò£¬°´ÕÕA-Z½øÐÐÅÅÐò
     */
    @SuppressWarnings("rawtypes")
    Comparator comparator = new Comparator<City>() {
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            int flag = a.compareTo(b);
            if (flag == 0) {
                return a.compareTo(b);
            } else {
                return flag;
            }
        }
    };

    @SuppressWarnings("unchecked")
    private void getResultCityList(String keyword) {
        AllCitySqliteOpenHelper dbHelper = new AllCitySqliteOpenHelper(this);
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(
                    "select * from city where name like \"%" + keyword
                            + "%\" or pinyin like \"%" + keyword + "%\"", null);
            City city;
            while (cursor.moveToNext()) {
                String cityName=cursor.getString(cursor.getColumnIndex("name"));
                String cityPinyin=cursor.getString(cursor.getColumnIndex("pinyin"));
                city = new City(cityName,cityPinyin);
                searchCityList.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //½«µÃµ½µÄ¼¯ºÏ°´ÕÕ×Ô¶¨ÒåµÄcomparatorµÄ¹æÔò½øÐÐÅÅÐò
        Collections.sort(searchCityList, comparator);
    }

    // ÉèÖÃÏÔÊ¾×ÖÄ¸µÄTextViewÎª²»¿É¼û
    private class OverlayThread implements Runnable {
        @Override
        public void run() {
            tvDialog.setVisibility(View.INVISIBLE);
        }
    }
    //²åÈëÊý¾Ýµ½×î½ü·ÃÎÊµÄ³ÇÊÐ
    public void InsertCity(String name) {
        SQLiteDatabase db = cityOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recentcity where name = '"
                + name + "'", null);
        if (cursor.getCount() > 0) { //
            db.delete("recentcity", "name = ?", new String[] { name });
        }
        db.execSQL("insert into recentcity(name, date) values('" + name + "', "
                + System.currentTimeMillis() + ")");
        db.close();
    }
}
