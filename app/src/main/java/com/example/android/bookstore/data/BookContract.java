package com.example.android.bookstore.data;

import android.provider.BaseColumns;


public class BookContract {






    /* Inner class that defines the table contents of the location table */
    public static final class BookEntry implements BaseColumns {


        // Table name
        public static final String TABLE_NAME = "bookstore";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed"; // breed
        public static final String COLUMN_PET_GENDER = "gender"; // gender
        public static final String COLUMN_PET_WEIGHT = "weight"; // weight

    /**
     * Possible values for the gender
     */

    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;


}


}