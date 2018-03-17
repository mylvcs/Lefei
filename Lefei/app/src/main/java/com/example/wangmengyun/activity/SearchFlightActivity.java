package com.example.wangmengyun.activity;
/**
 * Created by wangmengyun on 2018/3/7.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wangmengyun.lefei.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import wangmengyun.AsyncTask.Asynctask;

import static android.view.View.OnClickListener;
import static android.view.View.VISIBLE;


/**
 * Created by wangmengyun on 2018/3/4.
 */


public class SearchFlightActivity extends AppCompatActivity {
    private TextView tv_dancheng, tv_wangfan, tv_chapter_intro;
    private ListView lv_flight_search;
    private Button mButton;
    private Spinner spinner;
    private List<String> datalist;
    private ArrayAdapter<String> adapter;
    private EditText departCity;
    private EditText arriveCity;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_searchflight);


        tv_dancheng = (TextView) findViewById(R.id.tv_dancheng);
        tv_wangfan = (TextView) findViewById(R.id.tv_wangfan);
        mButton = (Button) findViewById(R.id.search_button);
        departCity = (EditText) findViewById(R.id.departure);
        arriveCity = findViewById(R.id.arrive);

//        tv_wangfan.setOnClickListener(this);
//        departCity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SearchFlightActivity.this, PickCityActivity.class);
//                startActivity(intent);
//            }
//
//
//        });

        datalist = new ArrayList<String>();
        datalist.add("经济舱");
        datalist.add("商务舱");

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datalist);

        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setOnItemClickListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //TODO
//                //textView.setText("您当前选择的是："+adapter.getItem(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//              //  textView.setText("请选择您的城市");
//
//            }
//        });
//
        mButton.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
              Intent intent = new Intent(SearchFlightActivity.this, Asynctask.class);

              Bundle bd = new Bundle();
              bd.putString("departCity",departCity.getText().toString());
              bd.putString("arriveCity",arriveCity.getText().toString());
              intent.putExtras(bd);

          //    intent.putExtra(Intent.EXTRA_TEXT,departCity.getText().toString());
         //       intent.putExtra(Intent.EXTRA_TEXT,arriveCity.getText().toString());
              startActivity(intent);
            }
        });


        tv_dancheng.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_wangfan.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_dancheng.setTextColor(Color.parseColor("#FFFFFF"));
        tv_wangfan.setTextColor(Color.parseColor("#000000"));
    }

//    private void makeFlightSearchQuery() {
//        String departCityQuery = departCity.getText().toString();
//        //  String arriveCityQuery = arriveCity.getText().toString();
//
//        URL flightSearchUrl = NetworkUtils.buildUrl(departCityQuery);
//        // mUrlDisplayTextView.setText(flightSearchUrl.toString());
//        // COMPLETED (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
//        new FlightQueryTask().execute(flightSearchUrl);
//    }
//
//    // COMPLETED (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
//    public class FlightQueryTask extends AsyncTask<URL, Void, String> {
//
//        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
//        @Override
//        protected String doInBackground(URL... params) {
//            URL searchUrl = params[0];
//            String flightSearchResults = null;
//            try {
//                flightSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return flightSearchResults;
//        }
//
//        // COMPLETED (3) Override onPostExecute to display the results in the TextView
//        @Override
//        protected void onPostExecute(String flightSearchResults) {
//            if (flightSearchResults != null && !flightSearchResults.equals("")) {
//               // mFlightResultsTextView.setText(flightSearchResults);
//                Log.i("flight","searched");
//            }
//        }
//
//    }

    /**
     * 控件的点击事件
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dancheng:// 单程
                //    lv_flight_search.setVisibility(View.GONE);
                //    sv_chapter_intro.setVisibility(View.VISIBLE);
                tv_dancheng.setBackgroundColor(Color.parseColor("#30B4FF"));
                tv_wangfan.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tv_dancheng.setTextColor(Color.parseColor("#FFFFFF"));
                tv_wangfan.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.tv_wangfan:// 往返
                //    lv_flight_search.setVisibility(View.VISIBLE);
                //     sv_chapter_intro.setVisibility(View.GONE);
                tv_dancheng.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tv_wangfan.setBackgroundColor(Color.parseColor("#30B4FF"));
                tv_dancheng.setTextColor(Color.parseColor("#000000"));
                tv_wangfan.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case R.id.search_button:
                //search();
              break;

            default:
                break;
        }
    }


    /**
     * 读取数据流,参数in是数据流
     */
    private String read(InputStream in) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line=null;
        try {
            sb = new StringBuilder();//实例化一个StringBuilder对象
            //用InputStreamReader把in这个字节流转换成字符流BufferedReader
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine())!=null){//从reader中读取一行的内容判断是否为空
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (in != null)
                    in.close();
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    /**
     * 从SharedPreferences中读取登录状态
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            int position=data.getIntExtra("position", 0);
//            adapter.setSelectedPosition(position);// 设置被选中的位置
            // 目录选项卡被选中时所有图标的颜色值
            lv_flight_search.setVisibility(VISIBLE);
//            sv_chapter_intro.setVisibility(View.GONE);
//            tv_intro.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            tv_video.setBackgroundColor(Color.parseColor("#30B4FF"));
//            tv_intro.setTextColor(Color.parseColor("#000000"));
//            tv_video.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }



//
//    public static void search(){
//        try{
//            // 连接到 mongodb 服务
//            MongoClient mongoClient = new MongoClient( "39.108.51.161" , 27017 );
//
//            // 连接到数据库
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("findtrip");
//            //  System.out.println("Connect to database successfully");
//
//            if(mongoDatabase!=null) {
//                Log.i("database","Connected");
//            }else{
//                Log.i("database","not connected");
//            }
//            MongoCollection<Document> collection = mongoDatabase.getCollection("Ctrip");
//            //  System.out.println("集合 flight 选择成功");
//            Log.i("collections","flight");
//            //检索所有文档
//            /**
//             * 1. 获取迭代器FindIterable<Document>
//             * 2. 获取游标MongoCursor<Document>
//             * 3. 通过游标遍历检索出的文档集合
//             * */
//            FindIterable<Document> findIterable = collection.find();
//            MongoCursor<Document> mongoCursor = findIterable.iterator();
//            while(mongoCursor.hasNext()){
//                //TODO
//                Log.i("Flight","get");
//                // System.out.println(mongoCursor.next());
//
//            }
//
//        }catch(Exception e){
//            //TODO
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//        }
//    }
}