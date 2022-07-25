package JenkinsJobs;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJSON {
	

	public static void main(String[] args) throws FileNotFoundException {
        
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\VS065203\\OneDrive - Cerner Corporation\\Desktop\\EODDetails.json"));
            //Forming URL
            System.out.println("Contents of the JSON are: ");
            System.out.println("ID: "+jsonObject.get("ID"));
            System.out.println("name: "+jsonObject.get("name"));
            System.out.println("status: "+jsonObject.get("status"));
            System.out.println("Expiry: "+ jsonObject.get("expiration_dt_tm"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("devices");
            
            for (int i = 0; i < ((CharSequence) jsonArray).length(); i++)
            {
              //String post_id = jsonArray.getJSONObject(i).getString("post_id");
           
            }
            //Iterating the contents of the array
            @SuppressWarnings("unchecked")
			Iterator<String> iterator = jsonArray.iterator();
            
            while(iterator.hasNext()) {
               System.out.println(iterator.next());
            }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
               e.printStackTrace();
         } catch (ParseException e) {
               e.printStackTrace();
         }
		
    }
}