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
import android.widget.EditText;
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
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton backButtom = null;
    private ImageButton changeBackButtom = null;
    private ImageButton personButtom = null;
    private ImageButton reloadButton = null;
    private ImageButton cancelButton = null;
    private ImageButton soundButton = null;
    private EditText mResultText;
    private ImageView wholeBG = null;
    private TextView tv1 = null;
    private final int REQUEST_CODE_CHOOSE = 1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //录音按钮
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "= 5eabf03c");
        soundButton = (ImageButton)findViewById(R.id.sound);
        soundButton.setOnClickListener(this);
        mResultText = (EditText)findViewById(R.id.result);

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

    @Override
    public void onClick(View v) {
        soundVoice();
    }
    //开始说话：

    private void soundVoice() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            String arrs[] = {Manifest.permission.RECORD_AUDIO};
            ActivityCompat.requestPermissions(this, arrs, 1);
        } else {
            RecognizerDialog dialog = new RecognizerDialog(this, null);
            dialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            dialog.setParameter(SpeechConstant.ACCENT, "mandarin");
            dialog.setListener(new RecognizerDialogListener() {
                @Override
                public void onResult(RecognizerResult recognizerResult, boolean b) {
                    printResult(recognizerResult);
                }
                @Override
                public void onError(SpeechError speechError) {
                }
            });
            dialog.show();
            Toast.makeText(this, "请开始说话", Toast.LENGTH_SHORT).show();
        }
    }
    //回调结果：

    private void printResult(RecognizerResult results) {
        String text = parseIatResult(results.getResultString());
        // 自动填写地址
        mResultText.append(text);
    }

    public static String parseIatResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);
            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++) {
                // 转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret.toString();
    }

    public static Bitmap capture(Activity activity) {

        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }
    //打开图片选择
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
