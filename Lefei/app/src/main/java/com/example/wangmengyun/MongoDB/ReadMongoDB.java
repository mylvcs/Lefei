//package wangmengyun.MongoDB;//package com.wangmengyun.MongoDB;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//
//import com.example.wangmengyun.lefei.R;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import org.bson.Document;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.client.MongoDatabase;
//
//import java.util.Arrays;
//public class ReadMongoDB extends AppCompatActivity{
//    private Button button;
//    private EditText mlist;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mongo);
//
//        mlist = findViewById(R.id.et_flightlist);
//        button = findViewById(R.id.button_search);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                search();
//            }
//        });
//    }
//
//        public void search(){
//        try{
//            // 连接到 mongodb 服务
//            MongoClient mongoClient = new MongoClient( "39.108.51.161" , 27017 );
//
//            // 连接到数据库
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("findtrip");
//          //  System.out.println("Connect to database successfully");
//                Log.i("Databased","connected!");
//
//            MongoCollection<Document> collection = mongoDatabase.getCollection("Ctrip");
//          //  System.out.println("集合 flight 选择成功");
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
//               //TODO
//                Log.i("Flight","get");
//                mlist.setText((CharSequence) mongoCursor.next());
//                // System.out.println(mongoCursor.next());
//
//            }
//
//        }catch(Exception e){
//            //TODO
//           // System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            Log.i("Fault","e");
//        }
//    }
//}