package com.example.shaimaaderbaz.booklist.parser;

import android.text.TextUtils;
import android.util.Log;

import com.example.shaimaaderbaz.booklist.models.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shaimaa Derbaz on 2/4/2018.
 */

public class BookParser {
    public static ArrayList<Book> extractFeatureFromJson(String bookJSON) {

        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        ArrayList<Book> books = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(bookJSON);
            JSONArray bookArray = baseJsonResponse.getJSONArray("items");
            for (int i = 0; i < bookArray.length(); i++) {

                JSONObject currentBook = bookArray.getJSONObject(i);
                JSONObject valuesInfo = currentBook.getJSONObject("volumeInfo");
                JSONObject imagesLink=valuesInfo.getJSONObject("imageLinks");
                String title = valuesInfo.getString("title");
                String author=" ";
                if(valuesInfo.has("authors")) {
                    JSONArray authors = valuesInfo.getJSONArray("authors");
                    author = authors.getString(0);
                }
                else
                {
                     author = " ";
                }
                String publisher = valuesInfo.getString("publisher");
                String description = valuesInfo.getString("description");
                String image = imagesLink.getString("thumbnail");
                Book book = new Book(title, author, publisher, description,image);
                books.add(book);

            }
        } catch (JSONException e) {
            Log.e("", "Problem parsing the Book JSON results", e);
        }
        return books;
    }
}
