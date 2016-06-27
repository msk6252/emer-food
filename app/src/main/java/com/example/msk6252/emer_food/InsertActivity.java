package com.example.msk6252.emer_food;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

/**
 * Created by msk6252 on 16/06/26.
 */
public class InsertActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.insert_main);
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText dayText = (EditText) findViewById(R.id.editDay);

        Button entryButton = (Button) findViewById(R.id.insert);
        assert entryButton != null;
        entryButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String day = dayText.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("name", name);
                insertValues.put("day", day);
                long id = db.insert("emerfood", name, insertValues);
                Intent intent = new Intent();
                intent.setClassName(getApplicationContext(), "com.example.msk6252.emer_food.MainActivity");
                startActivity(intent);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
    }
}

