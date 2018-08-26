package com.example.android.bookstore;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.bookstore.data.BookDbHelper;
import com.example.android.bookstore.data.BookContract.BookEntry;
import com.example.android.bookstore.data.BookDbHelper;

/**
 * Displays list of bookstore that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    // private BookDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        // mDbHelper = new BookDbHelper(this);

        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }


    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the bookstore database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        // PetDbHelper mDbHelper = new PetDbHelper(this);

        // Create and/or open a database to read from it
       //  SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_BOOK_PRODUCT_NAME,
                BookEntry.COLUMN_BOOK_PRICE,
                BookEntry.COLUMN_BOOK_QUANTITY,
                BookEntry.COLUMN_BOOK_SUPPLIER_NAME,
                BookEntry.COLUMN_BOOK_SUPPLIER_PHONE
        };

// perform a query on the provider using the ContentResolver.
        // Use the {@link BookEntry#CONTENT_URI} to access the pet data.
       Cursor cursor = getContentResolver().query(
               BookEntry.CONTENT_URI, // The content URI of the words table
               projection,  // the columns to return for each row
               null, // selection criteria
               null, // selection criteria
               null); // the sort order for the returned rows

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {

            // Create a header in the TextView that looks like this:
            //
            // The bookstore table contains <number of rows in Cursor> bookstore.
            // _id - name - breed - gender - weight

            //

            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.

            displayView.setText("The books table contains " + cursor.getCount() + " books.\n\n");
            displayView.append(BookEntry._ID + " - " +
                    BookEntry.COLUMN_BOOK_PRODUCT_NAME + " - " +
                    BookEntry.COLUMN_BOOK_PRICE + " - " +
                    BookEntry.COLUMN_BOOK_QUANTITY + " - " +
                    BookEntry.COLUMN_BOOK_SUPPLIER_NAME + " - " +
                    BookEntry.COLUMN_BOOK_SUPPLIER_PHONE + "\n");
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);


                if (currentSupplierName.isEmpty()) {
                    currentSupplierName = "Unknown supplier";
                }

                if (currentSupplierPhone.isEmpty()) {
                    currentSupplierPhone = "";
                }

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        "$ " + currentPrice + " - " +
                        "Qty: " + currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierPhone));
            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }


    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertBook() {
        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_BOOK_PRODUCT_NAME, "Monsoon Mansion by Cinelle Barnes");
        values.put(BookEntry.COLUMN_BOOK_PRICE, 27);
        values.put(BookEntry.COLUMN_BOOK_QUANTITY, 2);
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_NAME, "Book Supply Co.");
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE, "(202) 555-5555");

        // Insert a new row for Toto into the provider using the ContentResolver.
        // Use the {@link PetEntry#CONTENT_URI} to indicate that we want to insert
        // into the pets database table.
        // Receive the new content URI that will allow us to access Toto's data in the future.
        Uri newUri = getContentResolver().insert(BookEntry.CONTENT_URI, values);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }




    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    // private void insertPet() {

    // }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                // Do nothing for now

                insertBook();

                displayDatabaseInfo();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}