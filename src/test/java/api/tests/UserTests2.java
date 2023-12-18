package api.tests;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {
	Faker faker;
	User userPayload;
	Logger logger;
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
//		userPayload=new User();
//		userPayload.setId(29);
//		userPayload.setUsername("abhishek13");
//		userPayload.setFirstName("abhi");
//		userPayload.setLastName("shek");
//		userPayload.setEmail("abhishek14@gmail.com");
//		userPayload.setPassword("abhishek123");
//		userPayload.setPhone("9010389309");
		logger=LogManager.getLogger(this.getClass());
		
	}
	@Test(priority=1)
	public void PostUsertest()
	{
		logger.info("*** creating user using post request***");
	Response response= UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*** post request done ***");
	}
	@Test(priority = 2)
	public void GetUserByName()
	{
		logger.info("*** Getting user deatials using GET request***");
	Response response= UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*** GET request done***");
	}
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("*** Updating  user deatials using PUT request***");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());	
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();				
		Assert.assertEquals(response.getStatusCode(),200);		
		Response responseAfterupdate=UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
		logger.info("*** PUT request done***");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{	
		logger.info("*** Delete  user deatials using DELETE request***");
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*** DELETE request done***");
		

	}
	
	
}
