package com.example.shaimaaderbaz.booklist.activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaimaaderbaz.booklist.R;
import com.example.shaimaaderbaz.booklist.adapters.BookListAdapter;
import com.example.shaimaaderbaz.booklist.models.Book;
import com.example.shaimaaderbaz.booklist.parser.BookParser;
import com.example.shaimaaderbaz.booklist.utils.NetworkConnect;
import com.example.shaimaaderbaz.booklist.utils.Utils;


import java.io.IOException;;
import java.net.URL;
import java.util.ArrayList;


public class BookListActivity extends AppCompatActivity {

    public static String URL_USGS  ;
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

        URL_USGS = "https://www.googleapis.com/books/v1/volumes?q="+searchWord+"&maxResults=30";
        bookListView = (ListView) findViewById(R.id.activity_book_list);
        URL URLus=Utils.createUrl(URL_USGS);

        Context context = getApplicationContext();
        CharSequence text = "No Internet ,Please connect";
        int duration = Toast.LENGTH_SHORT;
        try {
            if (NetworkConnect.isConnected()==true)
            {
                BookListActivity.BookAsyncTask task=new BookListActivity.BookAsyncTask(this);
                task.execute(URLus);
            }
            else
            {

                Toast toast =Toast.makeText(context,text,duration);
                toast.show();
            }
        }catch (InterruptedException e )
        {

            Toast toast =Toast.makeText(context,text,duration);
            toast.show();
        }
        catch( IOException ee)
        {

            Toast toast =Toast.makeText(context,text,duration);
            //toast.show();

        }




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

            if (result == null ||result.size()==0)
            {
                TextView textview=(TextView)findViewById(R.id.checkedTextView);
                textview.setVisibility(TextView.VISIBLE);
                bookListView.setVisibility(ListView.GONE);

            }
            mAdapter = new BookListAdapter(mContext,result);
            bookListView.setAdapter(mAdapter);


        }
    }
}
