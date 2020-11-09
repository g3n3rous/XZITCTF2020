package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager voice;
    private AssetFileDescriptor fd;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int magic_op1(float a, float b) {
        return (int)Math.round((((a/b)+1)*b));
    }

    public int magic_op2(float a, float b) {
        return (int)Math.round((((a/b)-1)*b));
    }

    public int magic_op3(float a, float b) {
        return (int)Math.round((a/(1/b)));
    }

    public int magic_op4(float a, float b) {
        return (int)Math.round((a*(b/1)));
    }

    public int magic_op5(int a, int b, int c) {
        return (int)((a | (c & 0xff)) ^ b);
    }

    public char magic_all(int op) {
        int magic = 0xf5;
        int magic2 = 0x23;
        i++;
        if((((i-1) << 31) >> 31) == 0)
            return (char)(magic_op5(magic_op5(magic_op5(magic_op4(magic_op3(magic_op2(magic_op2(magic_op1(op, 0x23), 0x10), 19), 1), 1), magic, 0), magic, 0), magic2, 0));
        else
            return (char)(magic_op2(magic_op5(magic_op5(magic_op4(magic_op3(magic_op2(magic_op2(magic_op1(op, 0x23), 0x10), 19), 1), 1), magic, 0), magic, 0), 15));
    }

    public char[] check_init() {
        char[] check = new char[100];

        check[0] = magic_all(0x7b);
        check[1] = magic_all(0x69);
        check[2] = magic_all(0x6a);
        check[3] = magic_all(0x63);
        check[4] = magic_all(0x60);
        check[5] = magic_all(0x63);
        check[6] = magic_all(0x65);
        check[7] = magic_all(0x8a);
        check[8] = magic_all(0x62);
        check[9] = magic_all(0x7d);
        check[10] = magic_all(0x47);
        check[11] = magic_all(0x81);
        check[12] = magic_all(0x4c);
        check[13] = magic_all(0x78);
        check[14] = magic_all(0x47);
        check[15] = magic_all(0x6e);
        check[16] = magic_all(0x49);
        check[17] = magic_all(0x70);
        check[18] = magic_all(0x55);
        check[19] = magic_all(0x70);
        check[20] = magic_all(0x7c);
        check[21] = magic_all(0x50);
        check[22] = magic_all(0x4f);
        check[23] = magic_all(0x70);
        check[24] = magic_all(0x5a);
        check[25] = magic_all(0x86);
        check[26] = magic_all(0x50);
        check[27] = magic_all(0x6e);
        check[28] = magic_all(0x4b);
        check[29] = magic_all(0x4f);
        check[30] = magic_all(0x55);
        check[31] = magic_all(0x42);
        check[32] = magic_all(0x7c);
        check[33] = magic_all(0x7b);
        check[34] = magic_all(0x4c);
        check[35] = magic_all(0x83);
        check[36] = magic_all(0x50);
        check[37] = magic_all(0x6e);
        check[38] = magic_all(0x65);
        check[39] = magic_all(0x43);
        check[40] = magic_all(0x4d);
        check[41] = magic_all(0x8c);

        return check;
    }

    public boolean decode(String content) {
        char[] check;
        char[] array = content.toCharArray();

        if(array.length != 42)
            return false;

        check = check_init();

        for(int i=0; i<42; i++) {
            if(check[i] != array[i])
                return false;
        }
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void game() {
        voice = (AudioManager)getSystemService(AUDIO_SERVICE);
        voice.setStreamVolume(AudioManager.STREAM_MUSIC, 15, AudioManager.FLAG_SHOW_UI);

        mediaPlayer = new MediaPlayer();
        try {
            fd = getAssets().openFd("a.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.setDataSource(fd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void check(View v) {
        EditText input;
        String content;
        TextView welcome;

        input = (EditText)findViewById(R.id.input);
        content = input.getText().toString();

        if(decode(content)) {
            welcome = (TextView)findViewById(R.id.textView3);
            welcome.setText("恭喜, 你获得了flag!");
        }
        else {
            welcome = (TextView)findViewById(R.id.textView3);
            welcome.setText("你输入了错误的flag! 一份坏礼物来啦!");
            game();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_MUTE ) && event.getRepeatCount() == 0) {
            voice = (AudioManager)getSystemService(AUDIO_SERVICE);
            voice.setStreamVolume(AudioManager.STREAM_MUSIC, 15, AudioManager.FLAG_SHOW_UI);
            return true;
        }
        return false;
    }
}