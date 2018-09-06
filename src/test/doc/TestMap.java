package test.doc;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;


public class TestMap {
    @SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        map.put("2ss", "b");
        map.put("3", "ab");
        map.put("4", "ab");
        map.put("4", "ac");// 和上面相同 ， 会自己筛选

        String string = JSONObject.fromObject(map).toString();
        JSONObject jsonObject = JSONObject.fromObject(string);
        Map mapa = (Map)jsonObject;
        System.out.println(map.get("qq"));
    }
}