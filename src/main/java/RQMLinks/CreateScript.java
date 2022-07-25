package RQMLinks;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CreateScript {
public static void main(String[] args) throws IOException {
	
CreateScript cs= new CreateScript();

String Path="C:\\EggplantSuites\\IPDev-Millennium-ClinicalAssessment\\IPDev-Millennium-Clinical-Assessment.suite\\Scripts\\Workflow\\ICUReplace.script";
String data="Abc xyz";
//writeToTextFile(Path,data);
FileWriter writer = new FileWriter(Path);
writer.write(data);
FileOutputStream out = new FileOutputStream(Path);

writer.close();
out.close();
cs.setVariable(Path, 0, "#RQM test case Link");
	
}
	

public void setVariable(String filePath,int lineNumber, String data) throws IOException {
    Path path = Paths.get(filePath);
    List<String> lines = readTextFileByLines(filePath);
    lines.set(lineNumber, data);
    Files.write(path, lines);
}

public String readTextFile(String fileName) throws IOException {
	    String content = new String(Files.readAllBytes(Paths.get(fileName)));
	    return content;
	}

 public List<String> readTextFileByLines(String fileName) throws IOException {
	    List<String> lines = Files.readAllLines(Paths.get(fileName));
	    return lines;
	}

  public static void writeToTextFile(String fileName, String content) throws IOException {
	    Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	}
}
