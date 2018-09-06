package test.doc;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.util.DesUtils;

public class testJson {

	/** main方法描述：
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2017-9-23 下午02:41:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param args
	 * @return void
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {
		DesUtils des=new DesUtils();
		// TODO Auto-generated method stub
		EpdCheckgate checkgate = new EpdCheckgate();
		checkgate.setCheckgateSeq("1");
		checkgate.setOrganizeSeq("1");
		checkgate.setCheckCode("1");
		checkgate.setCheckName("1检票口");
		checkgate.setGateName("1");
		checkgate.setUpdateTime("2017-09-23 11:00:13");
		//JavaBean to jsonString
		String objectToJSON = des.getEncString(JSONObject.fromObject(checkgate).toString());
		System.out.println(objectToJSON);
		//JavaList to JSONArray
		List<EpdCheckgate> checkgates = new ArrayList<EpdCheckgate>();
		checkgates.add(checkgate);
		checkgates.add(checkgate);
		JSONArray jsonarray = JSONArray.fromObject(checkgates);
		System.out.println(jsonarray.toString());
		
		//JsonString to JavaBen
		EpdCheckgate epdCheckgate=(EpdCheckgate)JSONObject.toBean(JSONObject.fromObject(des.getDesString(objectToJSON.toString())),EpdCheckgate.class);
		
		

		
		//JSONArray to JavaList
		JSONArray jsonArray = JSONArray.fromObject(jsonarray.toString()); 
		List<EpdCheckgate> epdCheckgates  = (List<EpdCheckgate>)JSONArray.toList(jsonArray, new EpdCheckgate(), new JsonConfig());
		//List<EpdCheckgate> epdCheckgates = jsonArray.toList(jsonArray, EpdCheckgate.class);//过时方法
		
		
		System.out.println("finish");

		//EpdCheckgate epdCheckgate = (EpdCheckgate) JSONObject.toBean(JSONObject.fromObject(checkgate),EpdCheckgate.class);
		
		
		
		


		

	}

}
