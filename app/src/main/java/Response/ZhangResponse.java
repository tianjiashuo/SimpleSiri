package Response;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplesiri.R;

import Util.SoundPlayUtils;

public class ZhangResponse extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SoundPlayUtils.init(this);
    }
    public String match(String string) {
        String content1 = ".*你是谁.*";
        String content1_2 = ".*你叫.*";
        if (string.matches(content1)||string.matches(content1_2)) {
            SoundPlayUtils.play(15);
            return "我是张子枫";
        }
        return "匹配失败";
    }
}
