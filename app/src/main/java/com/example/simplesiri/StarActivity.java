package com.example.simplesiri;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voicesimulation.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StarActivity extends AppCompatActivity {

    private ListView listView;
    private String[] stars = {"华晨宇","孙燕姿","林俊杰","彭昱畅","蔡依林"};
    private List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();

    private DBHelper dbHelper;
    private String[] name, ids;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_layout);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        dbHelper = DBHelper.getInstance(this);
//        changeAdapter(dbHelper.queryAllCities());

        for (int i = 0; i < stars.length; i++) {
            Map<String, Object> listem = new HashMap<>();
            listem.put("name", stars[i]);
            listems.add(listem);
        }
        listView = (ListView) findViewById(R.id.star_listview);
        listView.setAdapter(new SimpleAdapter(getApplication(), listems,
                R.layout.activity_star_listview_item, new String[]{"name"},
                new int[]{R.id.listview_item_textview}));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(StarActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("starname", listems.get(position).get("name").toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    //根据数据库中数据为ListView设置适配器
//    private void changeAdapter(List<Map<String, Object>> list){
//        ids = new String[list.size()];
//        name = new String[list.size()];
//        for(int i=0;i<list.size();i++){
//            ids[i] = list.get(i).get("id").toString();
//            name[i] = list.get(i).get("name").toString();
//        }
//        myAdapter = new MyAdapter(name, ids, deleteButton);
//        listView.setAdapter(myAdapter);
//    }


}
