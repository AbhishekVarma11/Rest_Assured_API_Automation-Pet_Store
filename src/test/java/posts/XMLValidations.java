package posts;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

public class XMLValidations {
	@Test(priority=1)
	public void xmlTest()
	{
		Response res=given()
			.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo,"1");
		
	}
	public class XMLValidations2 {
		@Test(priority=2)
		public void xmlTest()
		{
			Response res=given()
				.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
		XmlPath xp=new XmlPath(res.asString());
		List<String> travelers_name=xp.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		for(String name:travelers_name)
		{
			System.out.println(name);
		}
			
		}

}
}
