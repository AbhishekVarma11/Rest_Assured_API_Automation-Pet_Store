
package posts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class PathandQueryParams {
	@Test
	public void TestPathQueryParams(){
		given().pathParam("mypath","users")
		.queryParam("page", 2)
		.queryParam("id", 5)
		.when().get("https://reqres.in/api/{mypath}")
		
		.then().statusCode(200).log().all();
		
	}

}
