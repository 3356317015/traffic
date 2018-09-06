package com.zhima.util.mp3;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

/**
 * MP3、Wav播放器类
 * @author Administrator
 *
 */
public class MP3Player {

	//播放线程,(true=停止播放,false=开始播放)
	private boolean isStop = true;
	//暂停状态(true=暂停状态,false=播放状态)
	private boolean isPause = false;
	//播放状态(true=播放停止，false=正在播放)
	private boolean isStatus = true;
	//播放文件
	private File playfile=null;
	//音频文件输入流
	private AudioInputStream audioInputStream;
	//音频格式
	private AudioFormat audioFormat;
	//音频文件格式
	private AudioFileFormat audioFileFormat;
	//输出设备
	private SourceDataLine sourceDataLine;
	//音量(最大为:6.0203,最小值为:-80)
	private float volume = 0;
	//音乐文件的长度
	private double playlen = 0;
	//播放进度百分比
	private int prett = 0;
	//得到MP3播放时长
	private long duration = 0;
	//文件类型(wav,mp3)
	private String fileType = "wav";
	//播放进度
	private int setDur = 0;
	
	private int bufferSize = 320;
	
	public void setFileName(String fileName){
		try {
			playfile = new File(fileName);
			playlen = new FileInputStream(playfile).available();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// 播放
	@SuppressWarnings("rawtypes")
	public void play() {
		try {
			this.isPause = false;
			this.isStop = false;
			
			//判断如果是播放线程状态未停止，说明当前状态为暂停
			if(isStatus == false){
				isPause = false;
				return ;
			}
			isStop = true;// 停止播放线程
			// 等待播放线程停止
			while (!isStatus) {
				Thread.sleep(10);
			}
			//判断文件是否为空
			if(playfile == null){
				return;
			}
			// 取得文件输入流
			audioInputStream = AudioSystem.getAudioInputStream(playfile);
			audioFormat = audioInputStream.getFormat();
			// 转换mp3文件编码
			if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
						audioFormat.getSampleRate(), 16,
						audioFormat.getChannels(),
						audioFormat.getChannels() * 2,
						audioFormat.getSampleRate(), false);
				audioInputStream = AudioSystem.getAudioInputStream(audioFormat,audioInputStream);
				audioFileFormat = AudioSystem.getAudioFileFormat(playfile); 
				Map props = audioFileFormat.properties(); 
				if(props.containsKey("duration")){
					duration = (long) Math.round((((Long) props.get("duration")).longValue())/1000);
				}
				fileType = "mp3";
			}else{
				fileType = "wav";
			}
			// 打开输出设备
			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, audioFormat,
					AudioSystem.NOT_SPECIFIED);
			if(sourceDataLine != null && sourceDataLine.isOpen()){
				sourceDataLine.drain();
				sourceDataLine.close();
			}
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			if(sourceDataLine != null ){
				bufferSize = audioFormat.getFrameSize() * Math.round(audioFormat.getSampleRate() / 10);
				sourceDataLine.open(audioFormat,bufferSize);
			}
			if(sourceDataLine != null && sourceDataLine.isOpen()){
				//设置播放音量
				FloatControl floatCol = (FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN); 
				floatCol.setValue(volume); 
				sourceDataLine.start();
			}
			// 创建独立线程进行播放
			isStop = false;
			Thread playThread = new Thread(new PlayThread());
			playThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//播放线程
	class PlayThread extends Thread {
		byte tempBuffer[] = new byte[bufferSize];
		public void run() {
			try {
				int cnt=0;
				int sum=0;
				isStatus = false;
				//播放起始时间
				long start  = System.currentTimeMillis();
				//得到拖动进度后的字节量
				long skip = 0;
				//得到拖动进度后的时长
				long skipTime = 0;
				// 读取数据到缓存数据
				while ((cnt = audioInputStream.read(tempBuffer,0,tempBuffer.length)) != -1) {
					//暂停将线程处于使循环
					long pauseTime = 0;
					while(isPause){
						if(pauseTime == 0){
							pauseTime = System.currentTimeMillis();
						}
					};
					if(pauseTime >0){
						pauseTime = System.currentTimeMillis() - pauseTime;
					}
					if (isStop){
						break;
					}
					//设置播放进度
					if(setDur >0){
						//得到跳过文件的字节数
						skip = (long) ((Double.valueOf(setDur)/100)*playlen);
						audioInputStream.skip(skip);
						//mp3时得到跳过播放文件的时长
						skipTime = (long) ((Double.valueOf(setDur)/100)*duration);
						prett = setDur;
						setDur = 0;
						sum += skip;
					}
					if (cnt > 0) {
						if(fileType.equals("wav")){
							sum += tempBuffer.length;
							double pre = Double.valueOf(sum) / Double.valueOf(playlen);
							prett = (int) (pre*100);
						}else{
							long dd = System.currentTimeMillis() - start - pauseTime;
							//得到帧数
							double pre = Double.valueOf(dd+skipTime)/Double.valueOf(duration);
							prett = (int) (pre*100);
						}
						// 写入缓存数据
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}finally{
				//Block等待临时数据被输出为空
				sourceDataLine.drain();
				sourceDataLine.close();
				isStatus = true;
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
		if(isPause == false){
			isPause = true;
		}else{
			isPause = false;
		}
	}
	
	/**
	 * 得到播放状态
	 * @return 0=停止，1=播放，2=暂停
	 */
	public int getState(){
		if(this.isStatus == true){
			return 0;
		}else if(this.isPause == true){
			return 2;
		}else{
			return 1;
		}
	}
	
	/**
	 * 设置音量，单位是：db，最大值为:6,最小值为：-80
	 * @param value 单位db，最大值为6，最小值为-80
	 * @return -1 失败（值超过限制），0表示失败（输出设备为空），1表示成功,
	 */
	public int setVolume(float value){
		if( value < -80 || value > 6 ){
			return -1;
		}
		volume = value;
		if(sourceDataLine == null){
			return 0;
		}
		FloatControl floatCol = (FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN); 
		floatCol.setValue(value);
		return 1;
	}

	/**
	 * getPrett方法慨述:得到播放百分比
	 * @return int
	 */
	public int getPrett() {
		return prett;
	}

	/**
	 * setSetDur方法慨述:设置播放进度百分比
	 * @param setDur void
	 * @return void
	 */
	public void setSetDur(int setDur) {
		this.setDur = setDur;
	}
}
