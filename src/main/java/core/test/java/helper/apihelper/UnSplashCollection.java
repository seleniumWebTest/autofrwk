package core.test.java.helper.apihelper;

import core.test.java.core.api.BaseAPI;
import core.test.java.core.enumobject.EnumObject.APIType;
import core.test.java.dataobject.EnvironmentInfo;

import java.util.ArrayList;

public class UnSplashCollection{
	public static void deleteCollection(String authToken, String collectionID) {

	}
	
	public static void deleteCollections(String authToken, ArrayList<String> collectionListID) {
		for (String collectionID : collectionListID)
		{
			deleteCollection(authToken, collectionID);
		}
	}
}
