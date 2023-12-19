package posts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.response.Response;
public class Authentication {
	
	//@Test(priority=1)
	public void basicAuth()
	{
	
		given().auth().basic("postman","password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().statusCode(200)
		.body("authenticated",equalTo(true))
		.log().body();
	}
	//@Test(priority=2)
	public void digestAuth()
	{
	
		given().auth().digest("postman","password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().statusCode(200)
		.body("authenticated",equalTo(true))
		.log().body();
	}
	//@Test(priority=3)
	public void preemtiveAuth()
	{
	
		given().auth().preemptive().basic("postman","password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().statusCode(200)
		.body("authenticated",equalTo(true))
		.log().body();
	}
	//@Test(priority=1)
	public void tokenAuth()
	{
		String bearerToken="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";
		given().headers("Authorization","Bearer "+bearerToken)
		.when().get("https://api.github.com/user/repos")
		.then().statusCode(200)
		.log().body();
	}
	@Test(priority=1)
	public void oAuth2()
	{
		String bearerToken="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";
		given().auth().oauth2(bearerToken)
		.when().get("https://api.github.com/user/repos")
		.then().statusCode(200)
		.log().body();
	}
	@Test(priority=2)
	public void apikeyAuth()
	{
		
		given().queryParam("appid", "1c6ebe5d3697e9dbba28f76b0aa50f6a")
		.when().get("http://api.openweathermap.org/data/2.5/forecast/daily?q=London")
		.then().statusCode(200)
		.log().body();
	}

}
