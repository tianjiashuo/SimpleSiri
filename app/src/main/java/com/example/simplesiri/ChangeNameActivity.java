package com.example.simplesiri;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.simplesiri.R;

import Util.BlurTransformation;
import Util.SoundPlayUtils;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class ChangeNameActivity extends AppCompatActivity {
    private EditText editName;
    private ImageButton confirmButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changename);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Glide.with(this).load(R.drawable.background)
                .apply(bitmapTransform(new BlurTransformation(this,25)))
                .into((ImageView) findViewById(R.id.img_bg));
       editName = findViewById(R.id.editname);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String value = bundle.getString("starname");
        editName.setText(value);
        confirmButton = (ImageButton) findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("nameResponse", editName.getText().toString());
                intent.putExtras(bundle);
                setResult(200,intent);
                finish();
            }
        });

        SoundPlayUtils.init(this);

    }

    public void play(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.MEDIA_CONTENT_CONTROL) != PackageManager.PERMISSION_GRANTED) {
            String arrs[] = {Manifest.permission.MEDIA_CONTENT_CONTROL};
            ActivityCompat.requestPermissions(this, arrs, 1);
        }else{
            SoundPlayUtils.play(1);
        }
    }



}
