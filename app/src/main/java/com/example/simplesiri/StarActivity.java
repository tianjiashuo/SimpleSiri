package com.example.simplesiri;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private String[] stars = {"华晨宇","毛不易","杨幂","彭昱畅","张子枫"};
    private int[] headIds = new int[] { R.drawable.huachenyu, R.drawable.maobuyi,
            R.drawable.yangmi, R.drawable.pengyuchang,R.drawable.zhangzifen};
    private List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_layout);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        for (int i = 0; i < stars.length; i++) {
            Map<String, Object> listem = new HashMap<>();
            listem.put("name", stars[i]);
            listem.put("headId",headIds[i]);
            listem.put("starId",i+1);
            listems.add(listem);
        }
        listView = (ListView) findViewById(R.id.star_listview);

        SimpleAdapter adapter = new SimpleAdapter(this, listems,
                R.layout.activity_star_listview_item, new String[] { "name",
                "headId" }, new int[] {
                R.id.listview_item_textview,
                R.id.head});
        // 给ListView设置适配器
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(StarActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("starname", listems.get(position).get("name").toString());
                bundle.putInt("starId", (int)listems.get(position).get("starId"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
