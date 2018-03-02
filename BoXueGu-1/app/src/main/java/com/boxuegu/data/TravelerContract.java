package com.boxuegu.data;

import android.provider.BaseColumns;

/**
 * Created by wangmengyun on 2018/3/2.
 */

public final class TravelerContract {
    public abstract class TravelerEntry implements BaseColumns {


            public static final String TABLE_NAME = "Traveler";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_GENDER = "price";

            //TODO
            public static final String COLUMN_ = "style";
            public static final String COLUMN_IN_STOCK = "in_stock";
            public static final String COLUMN_DESCRIPTION = "description";

            /**
             * Possible values for the style of the headphone.
             */
            public static final int STYLE_EAR_BUD = 0;
            public static final int STYLE_ON_EAR = 1;
            public static final int STYLE_OVER_EAR = 2;
            public static final int STYLE_BONE_CONDUCTION = 3;

        }
    }
