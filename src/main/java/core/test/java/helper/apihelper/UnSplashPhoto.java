package core.test.java.helper.apihelper;

import core.test.java.core.api.BaseAPI;
import core.test.java.core.enumobject.EnumObject;
import core.test.java.dataobject.EnvironmentInfo;

import java.util.ArrayList;


public class UnSplashPhoto{
	public static void unlikePhoto(String authToken, String photoID) {
		BaseAPI api = new BaseAPI(EnvironmentInfo.getApplicationAPIURL() + "/photos/" + photoID + "/like", EnumObject.APIType.DELETE);
		api.addHeader("Authorization", "Bearer " + authToken);
		api.sendRequest();
	}
	
	public static void unlikePhotos(String authToken, ArrayList<String> list_photo) {
		for (int i = 0; i < list_photo.size(); i++) {
			unlikePhoto(authToken, list_photo.get(i));
		}
	}

}