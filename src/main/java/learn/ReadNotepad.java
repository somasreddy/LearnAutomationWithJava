package learn;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadNotepad {
	public static void main(String[] args) throws Exception
	{
	FileReader fr=new FileReader("D:\\data.txt");
	BufferedReader br= new BufferedReader(fr);
	String x="";
	while ((x=br.readLine()) != null)
	{
	System.out.println(x +"\n");
	}
	br.close();

	}
}
