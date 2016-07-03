package com.example.msk6252.emer_food;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.view.View.OnClickListener;

/**
 * Created by msk6252 on 16/06/26.
 */
public class InsertActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.insert_main);
        //データベースを開くためのヘルパークラスをインスタンス化
        MyOpenHelper helper = new MyOpenHelper(this);
        //データベースへの読み書きを許可
        final SQLiteDatabase db = helper.getWritableDatabase();

        //レイアウトをオブジェクト化
        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText dayText = (EditText) findViewById(R.id.editDay);

        //ボタンクリックで入力値を取得し、データベースに登録1. ２つ目のウィンドウで、C:¥Users¥Public¥Desktopにアクセス。
        Button entryButton = (Button) findViewById(R.id.insert);
        Button cancelButton = (Button) findViewById(R.id.cancel);
        assert entryButton != null;
        assert cancelButton != null;

        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent cancel = new Intent();
                    cancel.setClassName(getApplicationContext(),"com.example.msk6252.emer_food.MainActivity");
                    startActivity(cancel);
                }
            }
        );

        entryButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String day = dayText.getText().toString();

                ContentValues insertValues = new ContentValues();
                //データベースのそれぞれの値にセット
                insertValues.put("name", name);
                insertValues.put("day", day);
                long id = db.insert("emerfood", name, insertValues);
                //登録後、MainActivityに遷移
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

