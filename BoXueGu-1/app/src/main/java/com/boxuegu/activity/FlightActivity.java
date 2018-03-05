package com.boxuegu.activity;

/**
 * Created by wangmengyun on 2018/3/4.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.boxuegu.R;
import com.boxuegu.adapter.VideoListAdapter;
import com.boxuegu.bean.VideoBean;
import com.boxuegu.utils.AnalysisUtils;
import com.boxuegu.utils.DBUtils;
import com.boxuegu.view.FlightView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class FlightActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_dancheng, tv_wangfan, tv_chapter_intro;
    private ListView lv_flight_search;
   private Button mButton;

 //   private VideoListAdapter adapter;
//    private List<VideoBean> videoList;
    private int chapterId;
    private String intro;
    private DBUtils db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view_flight);
        // 设置此界面为竖屏
  //      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 从课程界面传递过来的章节id
   //     chapterId = getIntent().getIntExtra("id", 0);
        // 从课程界面传递过来的章节简介
    //   tv_dancheng = getIntent().getStringExtra("intro");
        // 创建数据库工具类的对象
        db = DBUtils.getInstance(FlightActivity.this);
 //       initData();
        init();
    }
    /**
     * 初始化界面UI控件
     */
    private void init() {
        tv_dancheng= (TextView) findViewById(R.id.tv_dancheng);
        tv_wangfan = (TextView) findViewById(R.id.tv_wangfan);
        mButton = (Button) findViewById(R.id.departure);


//        lv_video_list = (ListView) findViewById(R.id.lv_video_list);
//        tv_chapter_intro = (TextView) findViewById(R.id.tv_chapter_intro);
//        sv_chapter_intro= (ScrollView) findViewById(R.id.sv_chapter_intro);
//        adapter = new VideoListAdapter(this, new VideoListAdapter.OnSelectListener() {
//            @Override
//            public void onSelect(int position, ImageView iv) {
//                adapter.setSelectedPosition(position); // 设置适配器的选中项
//                VideoBean bean = videoList.get(position);
//                String videoPath = bean.videoPath;
//                adapter.notifyDataSetChanged();// 更新列表框
//                if (TextUtils.isEmpty(videoPath)) {
//                    Toast.makeText(FlightActivity.this,
//                            "本地没有此视频，暂无法播放", Toast.LENGTH_SHORT).show();
//                    return;
//                }else{
//                    // 判断用户是否登录，若登录则把此视频添加到数据库
////                    if(readLoginStatus()){
////                        String userName= AnalysisUtils.readLoginUserName(VideoListActivity.this);
////                        db.saveVideoPlayList(videoList.get(position),userName);
////                    }
//                    // 跳转到视频播放界面
//                    Intent intent=new Intent(
//                            FlightActivity.this,VideoPlayActivity.class);
//                    intent.putExtra("videoPath", videoPath);
//                    intent.putExtra("position", position);
//                    startActivityForResult(intent, 1);
//                }
//            }
//        });
//        lv_video_list.setAdapter(adapter);
        tv_dancheng.setOnClickListener(this);
        tv_wangfan.setOnClickListener(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent  intent = new Intent(FlightActivity.this,CityActivity.class);
            startActivity(intent);
            }


        });
//        adapter.setData(videoList);
//        tv_chapter_intro.setText(intro);
        tv_dancheng.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_wangfan.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_dancheng.setTextColor(Color.parseColor("#FFFFFF"));
        tv_wangfan.setTextColor(Color.parseColor("#000000"));
    }
    /**
     * 控件的点击事件
     */
    @Override
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
            default:
                break;
        }
    }
    /**
     * 设置视频列表本地数据
     */
//    private void initData() {
//        JSONArray jsonArray;
//        InputStream is = null;
//        try {
//            is = getResources().getAssets().open("data.json");
//            jsonArray = new JSONArray(read(is));
//            videoList = new ArrayList<VideoBean>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                VideoBean bean = new VideoBean();
//                JSONObject jsonObj = jsonArray.getJSONObject(i);
//                if (jsonObj.getInt("chapterId") == chapterId) {
//                    bean.chapterId=jsonObj.getInt("chapterId");
//                    bean.videoId=Integer.parseInt(jsonObj
//                            .getString("videoId"));
//                    bean.title=jsonObj.getString("title");
//                    bean.secondTitle=jsonObj.getString("secondTitle");
//                    bean.videoPath=jsonObj.getString("videoPath");
//                    videoList.add(bean);
//                }
//                bean = null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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
            // 接收播放界面回传过来的被选中的视频的位置
            int position=data.getIntExtra("position", 0);
//            adapter.setSelectedPosition(position);// 设置被选中的位置
            // 目录选项卡被选中时所有图标的颜色值
            lv_flight_search.setVisibility(View.VISIBLE);
//            sv_chapter_intro.setVisibility(View.GONE);
//            tv_intro.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            tv_video.setBackgroundColor(Color.parseColor("#30B4FF"));
//            tv_intro.setTextColor(Color.parseColor("#000000"));
//            tv_video.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}