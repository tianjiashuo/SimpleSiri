package com.example.simplesiri;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class StarActivity extends AppCompatActivity {

    private ListView listView;
    private String[] stars = {"华晨宇","毛不易","杨幂","撒贝宁","张子枫"};
    private int[] headIds = new int[] { R.drawable.huachenyu, R.drawable.maobuyi,
            R.drawable.yangmi, R.drawable.sabeining,R.drawable.zhangzifeng};
    private List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_layout);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Glide.with(this).load(R.drawable.background)
                .apply(bitmapTransform(new BlurTransformation(this,25)))
                .into((ImageView) findViewById(R.id.starback));

        for (int i = 0; i < stars.length; i++) {
            Map<String, Object> listem = new HashMap<>();
            listem.put("name", stars[i]);
            listem.put("headId",headIds[i]);
            listem.put("starId",i+1);
            listems.add(listem);
        }
        listView = (ListView) findViewById(R.id.star_listview);

        MyAdapter myAdapter = new MyAdapter(stars,headIds);
        listView.setAdapter(myAdapter);

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
    private class MyAdapter extends BaseAdapter {
        private class ViewSet{
            ImageView head;
            TextView name;
        }
        private String[] stars ;
        private int[] headIds;
        public MyAdapter(String[] stars, int[] headIds){
            this.stars = stars;
            this.headIds = headIds;
        }
        @Override
        public int getCount() {
            return stars.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewSet viewSet = null;
            if(convertView == null){
                viewSet = new ViewSet();
                convertView = LayoutInflater.from(getApplication()).inflate(R.layout.activity_star_listview_item, null);
                viewSet.name = (TextView)convertView.findViewById(R.id.listview_item_textview);
                viewSet.head = (ImageView) convertView.findViewById(R.id.head);
                Glide.with(StarActivity.this)
                    .load(headIds[position])
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(viewSet.head);
                convertView.setTag(viewSet);
            }else{
                viewSet = (ViewSet)convertView.getTag();
            }
            viewSet.name.setText(stars[position]);
            return convertView;
        }
    }


}
