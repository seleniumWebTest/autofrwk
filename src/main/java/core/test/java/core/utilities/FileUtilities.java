package core.test.java.core.utilities;

import java.io.File;

public class FileUtilities {

	public static boolean doesFileExist(String filePath, int timeOut) {
		File f = new File(filePath);
		System.out.println(filePath);
		int current  = (int) System.currentTimeMillis();
		int end_time = (int) System.currentTimeMillis() + timeOut*1000;
		while (current < end_time) {
			if (f.exists() && !f.isDirectory()) {
				return true;
			}
			
			current = (int) System.currentTimeMillis();
		}
		
		return false;
	}

	public static void deleteFiles(String filePath) {
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) { 
		    f.delete();
		}
	}
	
	public static String validateJSONFileName(String sFileName)
	{
		if (null == sFileName.trim()) 
			return "";
		if (sFileName.trim().endsWith(".json")) 
			return sFileName;
		if (!sFileName.trim().contains(".")) 
			return sFileName + ".json";
		return "";
	}
	

	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

}