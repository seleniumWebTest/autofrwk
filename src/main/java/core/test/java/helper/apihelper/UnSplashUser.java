package core.test.java.helper.apihelper;

import core.test.java.core.api.BaseAPI;
import core.test.java.core.enumobject.EnumObject.APIType;
import core.test.java.dataobject.EnvironmentInfo;
import freemarker.core.ReturnInstruction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.http.HttpResponse;
import java.util.ArrayList;


public class UnSplashUser{

	public static String getCurrentUser(String authToken) {
			return "";
	}
	
	public static ArrayList<String> getListUserLikes(String authToken, String username) {
			return null;
	}

	public static String getCollectionIdByName(String new_collectionName, String auth_token) {
		String result = "";
		JSONArray listCollection = getUserCollections(auth_token);

		return result;
	}

	private static JSONArray getUserCollections(String authToken) {
		return null;
	}

}
