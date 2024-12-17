package core.test.java.dataobject;
import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.test.java.core.utilities.FileUtilities;
import core.test.java.projectconst.Constant;


public class Account {
	@JsonProperty("username")private String username;
	@JsonProperty("password")private String password;
	
	public Account()
	{
		
	}
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUserName()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public static Account loadFromJSON(String accountName)
	{
		ObjectMapper mapper = new ObjectMapper();
		Account obj = null;
		String jsonFilePath = System.getProperty("user.dir") + Constant.PATHJSONACONT+"\\%s";
		try {
			obj = mapper.readValue(new File(String.format(jsonFilePath, FileUtilities.validateJSONFileName( accountName + ".json"))), Account.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return obj;
	}
}
