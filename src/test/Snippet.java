package test;

import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Snippet {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
	    // ？？ 这个Sapi.SpVoice是需要安装什么东西吗，感觉平白无故就来了
	    ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
	    // Dispatch是做什么的？
	    Dispatch sapo = sap.getObject();
	    try {
	 
	        // 音量 0-100
	        sap.setProperty("Volume", new Variant(100));
	        // 语音朗读速度 -10 到 +10
	        sap.setProperty("Rate", new Variant(1));
	 
	        Variant defalutVoice = sap.getProperty("Voice");
	 
	        Dispatch dispdefaultVoice = defalutVoice.toDispatch();
	        Variant allVoices = Dispatch.call(sapo, "GetVoices");
	        Dispatch dispVoices = allVoices.toDispatch();
	 
	        Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
	        ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
	        ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);
	 
	        Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
	        // 执行朗读
	        Dispatch.call(sapo, "Speak", new Variant("项目沟通管理是为了确保项目信息合理收集和传输，以及最终处理所需实施的工程。对于本项目来说，要科学的组织、指挥、协调和控制项目的实施过程，就必须进行项目的信息沟通。良好的信息沟通对项目的发展和人际关系的改善都有促进作用。项目内部如何进行有效的沟通，是一个复杂而又必须解决的问题。由于本项目牵涉到港务集团及下属公司，同时有牵涉其它外部单位和行业管理部门，除此以外，也会有业务专家参与到整个项目中，项目沟通效果的好坏，在很大程度上决定了项目的成功与否，因而研究项目沟通效果具有十分重要的意义。"));
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        sapo.safeRelease();
	        sap.safeRelease();
	    }
	    }
	
}

