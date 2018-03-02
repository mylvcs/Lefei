package com.boxuegu.data;

import android.provider.BaseColumns;

/**
 * Created by wangmengyun on 2018/3/2.
 */

public final class TravelerContract {
    public abstract class TravelerEntry implements BaseColumns {


            public static final String TABLE_NAME = "Traveler";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_GENDER = "gender";


     /*
     * This is called when the database is created for the first time.
     * 旅客表有字段：
        姓名，性别，出生日期，身份证号码，护照号码，是否常旅客，国籍，住址，etc
     */
            public static final String COLUMN_IDNUMBER = "id_number";
            public static final String COLUMN_PASSPORTNUMBER = "passport_number";
            public static final String COLUMN_COUNTRY = "country";
            public static final String COLUMN_ADDRESS = "address";
            public static final String COLUMN_BIRTHDAY = "birthday";


        }
    }
