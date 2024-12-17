package core.test.java.core.testprofile;

import core.test.java.projectconst.Constant;

import static java.lang.System.out;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProfileProperties {
	private static Properties environmentProps;

	/**
	 * Gets the key from Config.properties related to chosen profile
	 */
	public static String getProp(String key) {		
		if ((key == null) || key.isEmpty()) {
			return "";
		} else {
			return environmentProps.getProperty(key);
		}
	}

	public static void loadRunConfigProps(String env) {
		String profileFilePath = String.format(Constant.PROFILEFILEPATHFORMAT, env);
		environmentProps = new Properties();
		try {
			InputStream inputStream = new FileInputStream(new File(profileFilePath));
			environmentProps.load(inputStream);
		} catch (IOException e) {
			out.println("Exception stack trace:" + e);
		}
	}
}
