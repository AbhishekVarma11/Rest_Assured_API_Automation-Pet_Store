package posts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;



import io.restassured.response.Response;
public class jsonValidation {
	//@Test(priority=1)
	public void testJsonResponse()
	{
		given()
		.when().get("https://dummyjson.com/products/1")
		.then().statusCode(200)
		.body("images[0]",equalTo("https://i.dummyjson.com/data/products/1/1.jpg"));
		
	}
	//@Test(priority=2)
	public void testJsonResponse2()
	{
		Response res=given()
		.when().get("https://dummyjson.com/products/1");
		Assert.assertEquals(res.getStatusCode(),200);
		String title=res.jsonPath().getString("title").toString();//.xmlpath for parsing xml 
		Assert.assertEquals(title,"iPhone 9");
		
		
		
	}
	@Test(priority=1)
	public void testJsonResponse3()
	{
		Response res=given()
		.when().get("https://dummyjson.com/products");
		JSONObject js=new JSONObject(res.asString());
		for(int i=0;i<js.getJSONArray("products").length();i++)
		{
			String product_title=js.getJSONArray("products").getJSONObject(i).getString("title").toString();
			if(product_title.equals("OPPOF19"))
			{
				Assert.assertTrue(true);
				break;
			}
		}
//		Assert.assertEquals(res.getStatusCode(),200);
//		String title=res.jsonPath().getString("title").toString();
//		Assert.assertEquals(title,"iPhone 9");
		
		
		
	}

}
