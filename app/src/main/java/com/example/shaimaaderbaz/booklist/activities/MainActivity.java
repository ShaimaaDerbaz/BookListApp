package com.example.shaimaaderbaz.booklist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shaimaaderbaz.booklist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search =(Button) findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchWord=(EditText)findViewById(R.id.query_edittext);
                String searchResult =searchWord.getText().toString();
                Intent i =new Intent(MainActivity.this,BookListActivity.class);
                i.putExtra("searchWord",searchResult);
                startActivity(i);

            }
        });




    }


}
