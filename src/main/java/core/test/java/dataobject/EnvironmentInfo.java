package core.test.java.dataobject;


import core.test.java.core.testprofile.TestProfileProperties;

public class EnvironmentInfo {
	public static String getApplicationURL()
	{
		return TestProfileProperties.getProp("baseUrl");
	}
	
	public static String getApplicationAPIURL()
	{
		return TestProfileProperties.getProp("apiBaseUrl");
	}
	
	public static String getAuthTokenURL()
	{
		return TestProfileProperties.getProp("authtoken_url");
	}	
	
	public static String getAccessToken()
	{
		return TestProfileProperties.getProp("access_token");
	}
}
