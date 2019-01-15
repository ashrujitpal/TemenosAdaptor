import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;


public class Utility {
	
	static HashMap<String,String> loadJson(JSONObject json) throws JSONException{
		
		// My stored keys and values from the json object
		HashMap<String,String> myKeyValues = new HashMap<String,String>();

		// Used for constructing the path to the key in the json object
		Stack<String> key_path = new Stack<String>();
		
	    Iterator<?> json_keys = json.keys();

	    while( json_keys.hasNext() ){
	        String json_key = (String)json_keys.next();

	        try{
	            key_path.push(json_key);
	            loadJson(json.getJSONObject(json_key));
	       }catch (JSONException e){
	           // Build the path to the key
	           String key = "";
	           for(String sub_key: key_path){
	               key += sub_key+".";
	           }
	           key = key.substring(0,key.length()-1);

	           System.out.println(key+": "+json.getString(json_key));
	           key_path.pop();
	           myKeyValues.put(key, json.getString(json_key));
	        }
	    }
	    if(key_path.size() > 0){
	        key_path.pop();
	    }
	    
	    return myKeyValues;
	}
	
	public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        //String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(filename);
    }

}
