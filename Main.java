import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Arrays;
import org.json.*;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args){
        Parser p = new Parser();
        Path path = Paths.get("STOP_AREA.bin");
        byte[] data;
        List<Info> result;
        try {
            data = Files.readAllBytes(path);
            data = Arrays.copyOfRange(data, 0, data.length);
            result = p.parseBytesFormat5(data);
            JSONArray jResult = parseToJSON(result);
            FileWriter file = new FileWriter("parsed.txt");
            file.write(jResult.toString());
            file.close();
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public static double XtoLon(double x) {
    	return (x / 2.003750834E7d) * 180;
    }
    
    public static double YtoLat(double y) {
    	return ((2 * Math.atan(Math.exp(((((12000000 - y) / 2.003750834E7d) * 180) * 3.141592653589793d) / 180))) - 1.5707963267948966d) * 57.29577951308232d;
    }
    
    
    public static JSONArray parseToJSON(List<Info> list) {
        JSONArray jsonObj = new JSONArray();
        for (Info obj : list) {
        	JSONObject jObj = new JSONObject();
        	jObj.put("id", obj.id);
        	jObj.put("name", obj.name);
        	jObj.put("parentId", obj.parentId);
        	jObj.put("parentName", obj.parentName);
        	jObj.put("omc", obj.omc);
        	jObj.put("level", obj.level);
        	jObj.put("elevation", obj.elevation);
        	jObj.put("isTransferStation", obj.isTransferStation);
            JSONObject coordObj = new JSONObject();
            coordObj.put("lon", XtoLon(obj.coord.x));
            coordObj.put("lat", YtoLat(obj.coord.y));
            jObj.put("coord", coordObj);
            JSONArray netsObj = new JSONArray();
            for (Net net : obj.nets) {
            	JSONObject netObj = new JSONObject();
            	netObj.put("name", net.name);
            	JSONArray zonesObj = new JSONArray();
            	for (int zone : net.zones) {
            		zonesObj.put(zone);
            	}
            	netObj.put("zones", zonesObj);
            	netsObj.put(netObj);
            }
            jObj.put("nets", netsObj);
            jsonObj.put(jObj);
        }
        return jsonObj;
    }
}