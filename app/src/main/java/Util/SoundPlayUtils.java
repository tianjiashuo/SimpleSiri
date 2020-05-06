package Util;


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import com.example.simplesiri.R;

public class SoundPlayUtils {
    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_SYSTEM, 0);
    public static SoundPlayUtils soundPlayUtils;
    // 上下文
    static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static SoundPlayUtils init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundPlayUtils();
        }

        // 初始化声音
        mContext = context;

        //华晨宇
        mSoundPlayer.load(mContext, R.raw.haoahaoa, 1);// 1
        mSoundPlayer.load(mContext, R.raw.niyezaoahuoxingren, 1);// 2
        mSoundPlayer.load(mContext, R.raw.zhidaoniaiwola, 1);// 3
        mSoundPlayer.load(mContext, R.raw.wanandiqiuren, 1);// 4
        mSoundPlayer.load(mContext, R.raw.quchia, 1);// 5
        mSoundPlayer.load(mContext, R.raw.duinideganqing, 1);// 6
        mSoundPlayer.load(mContext, R.raw.chirouzong, 1);// 7
        mSoundPlayer.load(mContext, R.raw.shuileba, 1);// 8
        mSoundPlayer.load(mContext, R.raw.youameitian, 1);// 9
        mSoundPlayer.load(mContext,R.raw.woshihuachenyu,1);//10
        mSoundPlayer.load(mContext,R.raw.yanhuolidechenai,1);//11
        mSoundPlayer.load(mContext,R.raw.shenme,1);//12
        //撒贝宁
        mSoundPlayer.load(mContext,R.raw.fangxinzonghuo,1);//13
        //华晨宇
        mSoundPlayer.load(mContext,R.raw.hello,1);//14
        //张子枫
        mSoundPlayer.load(mContext,R.raw.zhangzifeng,1);//15
        //杨幂
        mSoundPlayer.load(mContext,R.raw.yangmi,1);//16
        //毛不易
        mSoundPlayer.load(mContext,R.raw.maobuyi,1);//17
        return soundPlayUtils;
    }

    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(int soundID) {
        mSoundPlayer.play(soundID, 1, 1, 0, 0, 1);
    }


}

