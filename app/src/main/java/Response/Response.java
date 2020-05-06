package Response;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplesiri.R;

import Util.SoundPlayUtils;


public class Response extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SoundPlayUtils.init(this);
    }
    public String classify(Integer starId,String string){
        switch (starId){
            case (1):
                HuaResponse huaResponse = new HuaResponse();
                String response1 = huaResponse.match(string);
                return response1;
            case(2):
                MaoResponse maoresponse = new MaoResponse();
                String response2 = maoresponse.match(string);
                return response2;
            case(3):
                YangResponse yangResponse = new YangResponse();
                String response3 = yangResponse.match(string);
                return response3;
            case(4):
                SaResponse pengResponse = new SaResponse();
                String response4 = pengResponse.match(string);
                return response4;
            case(5):
                ZhangResponse zhangResponse = new ZhangResponse();
                String response5 = zhangResponse.match(string);
                return response5;
            default:
                return "出错了";
        }
    }

}
