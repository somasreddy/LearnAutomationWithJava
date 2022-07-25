package learn;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteNotepad {
	public static void main(String[] args) throws Exception
	{
	FileWriter fr=new FileWriter("D:\\data.txt");
	BufferedWriter br=new BufferedWriter(fr);

	br.write("This is sample writer on note");
//	br.newLine();
	br.write("Testing tools");
//	br.newLine();
	br.close();

	}
}
