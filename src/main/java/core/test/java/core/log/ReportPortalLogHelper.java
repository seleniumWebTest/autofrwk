package core.test.java.core.log;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.io.BaseEncoding;

public class ReportPortalLogHelper {
	private static final Logger LOGGER = LogManager.getLogger(ReportPortalLogHelper.class);

	/*private LoggingUtils() {
		//statics only
	}*/

	public static void log(File file, String message) {
		LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
	}

	public static void log(byte[] bytes, String message) {
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", BaseEncoding.base64().encode(bytes), message);
	}

	public static void logBase64(String base64, String message) {
		LOGGER.info("In logBase 64");
		LOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
	}
}
