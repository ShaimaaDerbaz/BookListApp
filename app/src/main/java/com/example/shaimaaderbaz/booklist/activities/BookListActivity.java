package com.example.shaimaaderbaz.booklist.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shaimaaderbaz.booklist.R;
import com.example.shaimaaderbaz.booklist.adapters.BookListAdapter;
import com.example.shaimaaderbaz.booklist.models.Book;
import com.example.shaimaaderbaz.booklist.parser.BookParser;
import com.example.shaimaaderbaz.booklist.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    public static String URL_USGS  ;
    //  URL url=new URL(URL_USGS) ;
    ListView bookListView ;
    BookListAdapter mAdapter;





    public static ArrayList<Book> fetchBookData(String requestUrl) {

        URL url = Utils.createUrl(requestUrl);


        String jsonResponse = null;
        try {
            jsonResponse = Utils.makeRequests(url);
        } catch (IOException e) {
            Log.e("", "Problem making the HTTP request.", e);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ArrayList<Book> books = BookParser.extractFeatureFromJson(jsonResponse);


        return books;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        String searchWord="";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            searchWord = extras.getString("searchWord");
        }

        URL_USGS = "https://www.googleapis.com/books/v1/volumes?q="+searchWord+"&maxResults=20";
        bookListView = (ListView) findViewById(R.id.activity_book_list);
        URL URLus=Utils.createUrl(URL_USGS);
        BookListActivity.BookAsyncTask task=new BookListActivity.BookAsyncTask(this);
        task.execute(URLus);

        ///

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Book currentBook = mAdapter.getItem(position);
             //   Uri bookUri = Uri.parse(currentBook.getUrl());
               // Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);
             //   startActivity(websiteIntent);
            }
        });
    }

    private class BookAsyncTask extends AsyncTask<URL, Integer ,ArrayList<Book>>
    {
        private Activity mContext;
        public BookAsyncTask(Activity activity) {
            super();
            mContext=activity;
        }
        @Override
        protected ArrayList<Book> doInBackground (URL ... Url)
        {

            ArrayList<Book> result = fetchBookData(URL_USGS);
            return result;
        }
        protected void onPostExecute(ArrayList<Book> result)
        {

            if (result == null)
            {
                return;
            }
            mAdapter = new BookListAdapter(mContext,result);
            bookListView.setAdapter(mAdapter);

        }
    }
}
