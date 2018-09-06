package com.zhima.util.mp3;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class TestMP3 {
	boolean isStop = true;// 控制播放线程，false表示播放
	boolean isPause = false; //控制暂停，false表示播放
	boolean hasStop = true;// 播放线程状态,未false表示正在播放
	AudioInputStream audioInputStream;// 音频文件流
	AudioFormat audioFormat;// 文件格式
	SourceDataLine sourceDataLine;// 输出设备

	// 播放
	public void play() {
		try {
			//判断如果是播放线程状态未停止，说明当前状态为暂停
			if(hasStop == false){
				isPause = false;
				return ;
			}
			isStop = true;// 停止播放线程
			// 等待播放线程停止
			while (!hasStop) {
				System.out.print(".");
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
			}
			System.out.println("");
			File file = new File("E:/tmp/翻译练习.mp3");// linux路径
			// 取得文件输入流
			audioInputStream = AudioSystem.getAudioInputStream(file);
			audioFormat = audioInputStream.getFormat();

			// 转换mp3文件编码
			if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
						audioFormat.getSampleRate(), 16,
						audioFormat.getChannels(),
						audioFormat.getChannels() * 2,
						audioFormat.getSampleRate(), false);
				audioInputStream = AudioSystem.getAudioInputStream(audioFormat,audioInputStream);
			}
			// 打开输出设备
			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, audioFormat,
					AudioSystem.NOT_SPECIFIED);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();
			// 创建独立线程进行播放
			isStop = false;
			Thread playThread = new Thread(new PlayThread());
			playThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 线程！！
	class PlayThread extends Thread {
		byte tempBuffer[] = new byte[320];
		public void run() {
			try {
				int cnt;
				hasStop = false;
				// 读取数据到缓存数据
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					//暂停将线程处于使循环
					while(isPause){
						
					};
					if (isStop)
						break;
					if (cnt > 0) {
						// 写入缓存数据
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				// Block等待临时数据被输出为空
				sourceDataLine.drain();
				sourceDataLine.close();
				hasStop = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	/**
	 * 停止播放
	 */
	public void stop(){
		isStop = true;
	}
	
	/**
	 * 暂停播放
	 */
	public void pause(){
		isPause = true;
	}
	
	/**
	 * 设置音量大小，单位是：db，最大值为:6,最小值为：-80
	 * @param value
	 */
	public void setVolume(float value){
		if(sourceDataLine == null){
			return ;
		}
		FloatControl floatCol = (FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN); 
		floatCol.setValue(value); 
	}

	public static void main(String args[]) throws InterruptedException {
		TestMP3 test = new TestMP3();
		test.play();
//		for(int i=0;i<10;i++){
//			System.out.println("ii==="+i);
//			Thread.sleep(1000);
//		}
//		test.pause();
		
		for(int i=0;i<5;i++){
			System.out.println("ii==="+i);
			Thread.sleep(1000);
		}
		//max value 6.0206,min value -80
		test.setVolume(6);
//		test.play();
	}
}
