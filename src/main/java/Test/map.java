package Test;

import java.util.HashMap;
import java.util.Map;
class CountOccurence{
	String s;
	int count;
	CountOccurence(String s,int count){
		this.s=s;
		this.count=count;
	}
}

public class map {
	public static void main(String[] args) {
		String a = "My name is xyz that is my identity and my profession is Testing that is my job ";
		String b[] = a.split(" ");
		Map<String, Integer> map = new HashMap<>();
		Integer counter=null;//initalize counter
		for(int i=0;i<b.length;i++) { //loop the whole array
		     counter=map.get(b[i]);//get element from map
		     if(counter.equals(null)) { //check if it already exists
		          map.put(b[i], 1);//not exist, add with counter as 1
		     } else {
		          counter++;//if already exists, increment the counter & put to Map
		           map.put(b[i], counter);
		     }
		}  
		System.out.println(map);

	}
}
//{identity=2, name=1, xyz=2, is=2, My=1, my=2}