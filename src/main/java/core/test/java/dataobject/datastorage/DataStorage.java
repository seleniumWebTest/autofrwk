package core.test.java.dataobject.datastorage;

import core.test.java.dataobject.EnvironmentInfo;
import core.test.java.helper.apihelper.UnSplashCollection;
import core.test.java.helper.apihelper.UnSplashPhoto;

import java.util.ArrayList;


public class DataStorage {
	private static ArrayList<String> likedImages;
	private static ArrayList<String> createdCollections;
	
	public static void initializeDataStorage() {
		likedImages = new ArrayList<String>();
		createdCollections = new ArrayList<String>();
	}
	
	public static void addLikedPhoto(String photoID)
	{
		likedImages.add(photoID);
	}
	
	public static void addCreatedCollection(String collectionID)
	{
		createdCollections.add(collectionID);
	}
	
	public static void cleanTestData() {
		unlikePhotos();
		deleteCollections();
	}

	private static void deleteCollections() {
		UnSplashCollection.deleteCollections(EnvironmentInfo.getAccessToken(), createdCollections);
	}

	private static void unlikePhotos() {
		UnSplashPhoto.unlikePhotos(EnvironmentInfo.getAccessToken(), likedImages);
	}
}
