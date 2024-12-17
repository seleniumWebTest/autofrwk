package core.test.java.helper.apihelper;

import core.test.java.core.api.BaseAPI;

import core.test.java.core.enumobject.EnumObject.APIType;
import core.test.java.dataobject.EnvironmentInfo;
import org.json.simple.JSONObject;

import java.net.http.HttpResponse;



public class UnSplashTokenGeneration{
	public static String getAuthToken(String authCode)
	{
		String result = "";
		BaseAPI api = new BaseAPI(EnvironmentInfo.getAuthTokenURL(), APIType.POST);
		api.addParam("client_id","qBfG-rpsyVghf03eBLWtcxe2u2nxcTpYEPD7_K0q9cA");
		api.addParam("redirect_uri", "urn:ietf:wg:oauth:2.0:oob");
		api.addParam("client_secret", "rDkvN7Ll3fiGuKZQ-YM0UGvcpZEAy2032fM345VpzhQ");
		api.addParam("code", authCode);
		api.addParam("grant_type", "authorization_code");
		HttpResponse<String> response = api.sendRequest();
//		if (response != null) {
//			JSONObject jsonObj = new JSONObject(response.body());
//			result = jsonObj.getString("access_token");
//		}
		return result;
	}
	
	/*public static String getAuthCodeWithUrl() {
		String result = "";
		BaseAPI  api = new BaseAPI("https://unsplash.com/oauth/authorize?client_id=qBfG-rpsyVghf03eBLWtcxe2u2nxcTpYEPD7_K0q9cA&amp;redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&amp;response_type=code&amp;scope=public+read_user+write_user+read_photos+write_photos+write_likes+write_followers+read_collections+write_collections", APIType.GET);
		//api.addParam("client_id", "qBfG-rpsyVghf03eBLWtcxe2u2nxcTpYEPD7_K0q9cA");
		//api.addParam("redirect_uri", "urn:ietf:wg:oauth:2.0:oob");
		//api.addParam("response_type", "code");
		//api.addParam("scope", "public+read_user+write_user+read_photos+write_photos+write_likes+write_followers+read_collections+write_collections");
		HttpResponse<String> response = api.sendRequest();
		if (response != null)
		{
			int a = 0;
		}
		return "";
	}*/
}
