package Response;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplesiri.R;

import Util.SoundPlayUtils;

public class HuaResponse extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SoundPlayUtils.init(this);
    }
    public String match(String string) {
        String content1 = ".*你好.*";
        String content1_2 = ".*ello.*";
        String content1_3 = ".*Hi.*";
        String content2 = ".*好不好.*";
        String content3 = ".*早.*";
        String content4 = ".*爱你.*";
        String content5 = ".*晚安.*";
        String content6 = ".*我想吃.*";
        String content7 = ".*你爱我.*";
        String content8 = ".*吃什么.*";
        String content9 = ".*好困.*";
        String content10 = ".*我困.*";
        String content11 = ".*我要睡觉.*";
        String content12 = ".*哄我睡觉.*";
        String content13 = ".*想我.*";
        String content14 = ".*你是谁.*";
        String content14_1 = ".*你叫.*";
        String content15 = ".*烟火里的尘埃.*";
        String content15_1 = ".*小烟.*";
        if (string.matches(content1)||string.matches(content1_3)) {
            SoundPlayUtils.play(1);
            return "好啊好啊";
        }
        else if(string.matches(content2)||string.matches(content1_2)){
            SoundPlayUtils.play(14);
            return "hello";
        }
        else if(string.matches(content3)) {
                SoundPlayUtils.play(2);
                return "你也早啊火星人";
        }
        else if(string.matches(content4)){
            SoundPlayUtils.play(3);
            return "知道你爱我啦,不许反悔";
        }
        else if(string.matches(content5)){
            SoundPlayUtils.play(4);
            return "晚安地球人,晚安火星人";
        }
        else if(string.matches(content6)){
            SoundPlayUtils.play(5);
            return  "去吃啊";
        }
        else if(string.matches(content7)){
            SoundPlayUtils.play(6);
            return "对你的感情都在我的歌里，你最喜欢的那一首";
        }
        else if(string.matches(content8)){
            SoundPlayUtils.play(7);
            return "吃肉粽!";
        }
        else if(string.matches(content9)||string.matches(content10)||string.matches(content11)
        ||string.matches(content12)){
            SoundPlayUtils.play(8);
            return "睡了吧,晚安~";
        }
        else if(string.matches(content13)){
            SoundPlayUtils.play(9);
            return "有啊,每天除了想想中午吃什么,就是在想你啦";
        }
        else if(string.matches(content14)||string.matches(content14_1)){
            SoundPlayUtils.play(10);
            return "我是华晨宇";
        }
        else if(string.matches(content15)||string.matches(content15_1)){
            SoundPlayUtils.play(11);
            return "笑得开怀，哭的坦率，为何表情要让这世界安排，我就是我，我只是我，只是一场烟火散落的尘埃";
        }
        else {
            SoundPlayUtils.play(12);
            return "那是什么？";
        }
    }
}
