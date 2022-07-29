package RQMInnov;

public class ChangeFileFormat {
	public static void main(String[] args) throws Exception {
		// ChangeFileFormat changeFormat= new ChangeFileFormat();
		// System.out.println(changeFormat.ChageFileFormat("C:\\Temp\\","2018.08.02_RQMReport_23_08_2021_23_04_34",".csv"));
	}

	public String ChageFileFormat(String filePath, String fileName, String extension) throws Exception {
		String changeFormatStatus = "File not Valid";
		try {
//			LoadOptions loadOptions = new LoadOptions(FileFormatType.CSV);
			// Workbook workbook1 = new Workbook(filePath+fileName+extension, loadOptions);
			// workbook1.save(filePath+fileName+".xlsx" , SaveFormat.XLSX);
			changeFormatStatus = fileName + extension + " has been Changed to " + fileName + ".xlsx";
		} catch (Exception e) {
			changeFormatStatus = "!! Unable to Change the Format of the File" + fileName + extension;
		}
		return changeFormatStatus;
	}
}
