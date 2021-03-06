package com.example.msk6252.emer_food;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.Calendar;

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


        final DatePickerDialog mDatePickerDialog;
        final Calendar calendar = Calendar.getInstance();
        final int year  = calendar.get(Calendar.YEAR);
        final int monthOfYear = calendar.get(Calendar.MONTH);
        final int dayOfMonth   = calendar.get(Calendar.DAY_OF_MONTH);

        //レイアウトをオブジェクト化
        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText dayText = (EditText) findViewById(R.id.editDay);

        //ボタンクリックで入力値を取得し、データベースに登録,２つ目のウィンドウで、データベースにアクセス。
        Button entryButton = (Button) findViewById(R.id.insert);
        Button cancelButton = (Button) findViewById(R.id.cancel);
        assert entryButton != null;
        assert cancelButton != null;

        mDatePickerDialog = new DatePickerDialog(
                InsertActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(InsertActivity.this, String.valueOf(dayOfMonth), Toast.LENGTH_SHORT).show();
                        dayText.setText(String.valueOf(year) + "/" + String.valueOf(monthOfYear+1) + "/" + String.valueOf(dayOfMonth));
                    }
                },
                year,
                monthOfYear,
                dayOfMonth
        );
        //カレンダーを表示させるボタン
        dayText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });




        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent cancel = new Intent();
                    cancel.setClassName(getApplicationContext(),"com.example.msk6252.emer_food.MainActivity");
                    startActivity(cancel);
                }
            }
        );


        /*登録ボタンが押された時に以下の動作を行なう
        * ・入力値を取得し、変数に格納
        * ・データベースに登録するための下準備
        * ・データベースに登録
        * */
        entryButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String day = dayText.getText().toString();

                if(name == "" || day == "" || name.length() == 0 || day.length() == 0){
                    Toast.makeText(InsertActivity.this, "食糧名または日付が入力されていません", Toast.LENGTH_SHORT).show();
                } else {

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

            }
        });
    }

    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
    }

}

