package Response;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplesiri.R;

import Util.SoundPlayUtils;

public class SaResponse extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SoundPlayUtils.init(this);
    }
    public String match(String string) {
        String content1 = ".*你是谁.*";
        String content1_2 = ".*你叫.*";
        if (string.matches(content1)||string.matches(content1_2)) {
            SoundPlayUtils.play(13);
            return "我是在女人的芳心里，纵火的人";
        }
        return "匹配失败";
    }
}
