package posts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Cookies {
	//@Test(priority=1)
	void testCookies()
	{
		given().when().get("https://www.google.com/")
		.then().cookie("AEC","Ackid1Rv2Q6wJU6eg_VcnAR9OTGzQUHiOyBDJmCaJ5_RDMuuVcYlfsl_BJ0")
		//cookie everytime will changes it should be fail
		.log().all();
		//log.headers(),log.cookies(),log.body()
	}
	//@Test(priority=2)
	void getCookiesInfo()
	{
		Response res= given().when().get("https://www.google.com/");
		String cookie_value=res.getCookie("AEC");
		System.out.println(cookie_value);
		Map<String,String> cookie_set=res.getCookies();
		for(String v:cookie_set.keySet())
		{
			System.out.println(v+":"+res.getCookie(v));
		}
	}
	@Test(priority=1)
	void testHeaders()
	{
		given().when().get("https://www.google.com/")
		.then().header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Server","gws");
		
	}
	@Test(priority=2)
	void testgetHeaders()
	{
		Response res=given().when().get("https://www.google.com/");
		String ct=res.getHeader("Content-Type");
		System.out.println(ct);
		Headers myheaders=res.getHeaders();
		for(Header hd:myheaders)
		{
			System.out.println(hd.getName()+" "+hd.getValue());
			
		}
		
	}

}
