package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;



import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDT_Tests {
	@Test(priority=1,dataProvider="Data",dataProviderClass =DataProviders.class)
	public void DDT_POST(String userID,String userName,String fname,String lname,String userEmail,String pwd,String ph)
	{
		User usrPayload=new User();
		usrPayload.setId(Integer.parseInt(userID));
		usrPayload.setUsername(userName);
		usrPayload.setFirstName(fname);
		usrPayload.setLastName(lname);
		usrPayload.setEmail(userEmail);
		usrPayload.setPassword(pwd);
		usrPayload.setPhone(ph);
		Response response=UserEndPoints.createUser(usrPayload);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass =DataProviders.class)
	public void DDT_GET(String userName)
	{
		Response response=UserEndPoints.readUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=3,dataProvider="UserNames",dataProviderClass =DataProviders.class)
	public void DDT_delete(String userName)
	{
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
