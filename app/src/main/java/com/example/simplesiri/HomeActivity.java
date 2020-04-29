package com.example.simplesiri;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.util.Util;
import com.example.voicesimulation.R;
import com.bumptech.glide.Glide;

import com.example.simplesiri.ExGlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;


public class HomeActivity extends AppCompatActivity {

    private ImageButton backButtom = null;
    private ImageButton changeBackButtom = null;
    private ImageButton personButtom = null;
    private ImageButton reloadButton = null;
    private ImageButton cancelButton = null;
    private ImageView wholeBG = null;
    private TextView tv1 = null;
    private final int REQUEST_CODE_CHOOSE = 1;
//    private ImageButton

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        //显示当前人物
        tv1 =  findViewById(R.id.name);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String value = bundle.getString("starname");
        tv1.setText(value);

        backButtom = (ImageButton) findViewById(R.id.back);
        backButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(HomeActivity.this, StarActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        wholeBG = (ImageView) findViewById(R.id.img_bg);
        //更换背景图按钮
        changeBackButtom = (ImageButton)findViewById(R.id.home_photo);
        initPhotoSelect();
    }


    public static Bitmap capture(Activity activity) {

        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }
    private void open() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String arrs[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, arrs, REQUEST_CODE_CHOOSE);
        } else {
            Matisse.from(this)
                    .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                    .countable(true)
//                    .capture(true)//是否拍照
                    .captureStrategy(new CaptureStrategy(true, "PhotoPicker"))
                    .maxSelectable(1) // 图片选择的最多数量
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.8f) // 缩略图的比例
                    .imageEngine(new ExGlideEngine()) // 使用的图片加载引擎
                    .forResult(REQUEST_CODE_CHOOSE);
        }
    }

    private void initPhotoSelect() {
        changeBackButtom.setOnClickListener(v -> {
            open();
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    // request successfully, handle you transactions
                } else {
                    // permission denied
                    // request failed
                }
                return;
            }
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHOOSE) {
                Glide.with(this).load(Matisse.obtainResult(data).get(0)).into(wholeBG);
            }
        }
    }


}
