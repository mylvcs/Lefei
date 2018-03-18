package com.example.wangmengyun.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangmengyun on 2018/3/6.
 */

public class AnalysisUtils {
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName=sp.getString("loginUserName", "");
        return userName;
    }
}

