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
        String content1 = ".*你好.*";
        if (string.matches(content1)) {
            SoundPlayUtils.play(1);
            return "dalabengba";
        }
        return "nono";
    }
}
