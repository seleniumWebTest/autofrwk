package core.test.java.helper.apihelper;

import core.test.java.core.api.BaseAPI;
import core.test.java.core.enumobject.EnumObject.APIType;
import core.test.java.dataobject.EnvironmentInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.http.HttpResponse;
import java.util.ArrayList;


public class UnSplashUser{

	public static String getCurrentUser(String authToken) {
		String result = "";
		BaseAPI api = new BaseAPI(EnvironmentInfo.getApplicationAPIURL() + "/me", APIType.GET);
		api.addHeader("Authorization", "Bearer " + authToken);
		HttpResponse<String> response = api.sendRequest();
		if (response != null) {
//			JSONObject jsonObject = new JSONObject(response.body());
//			result = jsonObject.getString("username");
		}
		
		return result;
	}
	
	public static ArrayList<String> getListUserLikes(String authToken, String username) {
		ArrayList<String> result = new ArrayList<String>();
		BaseAPI api = new BaseAPI(EnvironmentInfo.getApplicationAPIURL() + "/users/" + username + "/likes", APIType.GET);
		api.addHeader("Authorization", "Bearer " + authToken);
		HttpResponse<String> response = api.sendRequest();
		if (response != null) {
//			JSONArray jsonArray = new JSONArray(response.body());
//			for (int i = 0; i < jsonArray.length(); i++)
//			{
//				result.add(jsonArray.getJSONObject(i).getString("id"));
//			}
		}
		
		return result;
	}

	public static String getCollectionIdByName(String new_collectionName, String auth_token) {
		String result = "";
		JSONArray listCollection = getUserCollections(auth_token);
//		for (int i = 0; i < listCollection.length(); i++) {
//			if (listCollection.getJSONObject(i).getString("title").equals(new_collectionName)) {
//				result = listCollection.getJSONObject(i).getString("id");
//				break;
//			}
//		}
		
		return result;
	}

	private static JSONArray getUserCollections(String authToken) {
		String current_user = UnSplashUser.getCurrentUser(authToken);
		BaseAPI api = new BaseAPI(EnvironmentInfo.getApplicationAPIURL() + "/users/" + current_user + "/collections", APIType.GET);
		api.addHeader("Authorization", "Bearer " + authToken);
		HttpResponse<String> response = api.sendRequest();
		if (response != null) {
//			JSONArray jsonArray = new JSONArray(response.body());
//			return jsonArray;
		}
		
		return null;
	}
	
	

}
