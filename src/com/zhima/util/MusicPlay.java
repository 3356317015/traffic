package com.zhima.util;
// 文件名:MuiscPlay.java 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/** 
* 
* 播放音频文件，产生音效 
*/ 
public class MusicPlay { 
    private AudioStream  as; //单次播放声音用 
    private ContinuousAudioDataStream cas;//循环播放声音 
    // 构造函数 
    public MusicPlay(URL url) { 
        try { 
            //打开一个声音文件流作为输入 
            as = new AudioStream (url.openStream()); 
        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
    // 一次播放 开始 
    public void start() { 
        if( as==null ){ 
            System.out.println("AudioStream object is not created!"); 
            return; 
        }else{ 
            AudioPlayer.player.start (as); 
        } 
    } 
    // 一次播放 停止 
    public void stop() { 
        if( as==null ){ 
            System.out.println("AudioStream object is not created!"); 
            return; 
        }else{ 
            AudioPlayer.player.stop(as); 
        }        
    } 
    
    // 循环播放 开始 
    public void continuousStart() { 
        // Create AudioData source. 
        AudioData data = null; 
        try { 
            data = as.getData(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 

        // Create ContinuousAudioDataStream. 
        cas = new ContinuousAudioDataStream (data); 
        // Play audio. 
        AudioPlayer.player.start(cas); 
    } 
    // 循环播放 停止 
    public void continuousStop() { 
        if(cas != null) { 
            AudioPlayer.player.stop (cas); 
        }    
    } 

}
