package SC2_simplify.GUIsupport;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MusicPlayer {
    
    File f = new File("SC2_simplify/chartlet/golden_armada.wav"); // 音乐路径，一定要是WAV格式的音乐不然不可以播放
    URL url;
    URI uri;
    AudioClip clip;

    void setMusic(String name)// 修改播放的音乐文件
    {
        //this.name = name;
    }

    public MusicPlayer() {
        try {
            uri = f.toURI();
            url = uri.toURL();
            clip = Applet.newAudioClip(url);
            //clip.loop();// 循环播放
            // clip.play();//播放
            // clip.stop();//停止播放
            System.out.println("音乐文件已经打开");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("播放错误！");
        }
    }

    public void stopMusic()// 停止播放
    {
        clip.stop();
    }

    public void playMusic()// 播放
    {
        clip.play();
    }

    public void loopMusic()// 循环播放
    {
        clip.loop();
    }
}
