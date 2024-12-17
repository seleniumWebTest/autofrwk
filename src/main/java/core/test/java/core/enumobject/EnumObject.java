package core.test.java.core.enumobject;

import java.util.Arrays;

public class EnumObject {

	public enum APIType {
		POST, GET, DELETE
	};
	
	public enum RunningMode {
		Local, Remote;

		public static String asString() {
			return Arrays.toString(RunningMode.values()).replaceAll("^.|.$", "");
		}
	}
	
	public enum DriverType {
	    Chrome, Firefox, IE, Edge, Safari, Android, IOS;

	    public static String asString() {
	        return Arrays.toString(DriverType.values()).replaceAll("^.|.$", "");
	    }
	}
	
	public enum ByType{
		Id, Name, CssSelector, LinkText, PartialLinkText, TagName, Xpath;
		public static String asString() {
	        return Arrays.toString(DriverType.values()).replaceAll("^.|.$", "");
	    }
	}
}
