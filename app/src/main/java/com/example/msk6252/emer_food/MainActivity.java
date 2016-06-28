package com.example.msk6252.emer_food;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;


import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;


//メイン画面

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SimpleCursorAdapter adapter;
    private MyOpenHelper DBhelper;
    private SQLiteDatabase db;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //リサイクルビューの定義
        mRecycleView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecycleView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);


        //データベースを制御するためのヘルパークラスの呼び出し
        DBhelper = new MyOpenHelper(this);
        db = DBhelper.getWritableDatabase();


        //全件表示するためのselect文を定義
        String SQL = "select * from emerfood;";
        Cursor c = null;

        //データベースからデータを取得(id,name,day)
        c = db.rawQuery(SQL,null);
        c.moveToFirst();

        //ListViewに格納するためのArrayListの定義を行なう
        final ArrayList<String> adapter = new ArrayList<String>();


        //SQL実行した結果から、要素を取得
        if(db != null){
             try {
                     for (int i = 0; i < c.getCount(); i++) {
                         id= c.getInt(c.getColumnIndex(FoodContract.Food._ID));
                         String name= c.getString(c.getColumnIndex(FoodContract.Food.COL_NAME));
                         String day= c.getString(c.getColumnIndex(FoodContract.Food.COL_DAY));
                         adapter.add(name);
                         c.moveToNext();
                     }
            //データベースを閉じる
                    db.close();
                 //アダプターに取得したデータを適用
                     mAdapter = new CardAdapter(this, adapter);
                     mRecycleView.setAdapter(mAdapter);
             }catch(Exception e){

             }
        }
        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecycleView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    adapter.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    adapter.remove(position);
                                    db = DBhelper.getWritableDatabase();
                                    int deleteid = id + position;
                                    db.delete("emerfood", "_id =" + deleteid, null);
                                    mAdapter.notifyItemRemoved(position);

                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        mRecycleView.addOnItemTouchListener(swipeTouchListener);


        //右下に登録画面へ遷移するためのボタンを設置
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            //クリックで登録画面に遷移
            public void onClick(View view) {
               Intent intent = new Intent();
                intent.setClassName(getApplicationContext(), "com.example.msk6252.emer_food.InsertActivity");
                startActivity(intent);
            }
        });
    }


    //メニューへ遷移するためのウィジェットの表示
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
