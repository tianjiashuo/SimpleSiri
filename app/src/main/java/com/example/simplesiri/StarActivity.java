package com.example.simplesiri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voicesimulation.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StarActivity extends AppCompatActivity {

    private ListView listView;
    private String[] stars = {"华晨宇","孙燕姿","林俊杰","绒绒","飒飒"};
    private List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_layout);
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
                bundle.putString("starname", stars[position]);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }

}
