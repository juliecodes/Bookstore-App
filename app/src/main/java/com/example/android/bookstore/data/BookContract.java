package com.example.android.bookstore.data;

import android.provider.BaseColumns;


public class BookContract {






    /* Inner class that defines the table contents of the location table */
    public static final class BookEntry implements BaseColumns {


        // Table name
        public static final String TABLE_NAME = "books";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_BOOK_PRODUCT_NAME = "name";
        public static final String COLUMN_BOOK_PRICE = "price"; // breed
        public static final String COLUMN_BOOK_QUANTITY = "quantity"; // gender
        public static final String COLUMN_BOOK_SUPPLIER_NAME = "supplier_name"; // weight
        public static final String COLUMN_BOOK_SUPPLIER_PHONE = "supplier_phone"; // weight




}


}