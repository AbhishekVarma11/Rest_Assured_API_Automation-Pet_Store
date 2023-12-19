package posts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.apache.poi.sl.usermodel.ObjectMetaData.Application;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import api.payloads.User;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
//https://jsonplaceholder.typicode.com/posts

//{
//    "userId": 23,
//     "title": "this post request done by abhishek",
//    "body": "abhishek post"
//}
public class Different_Post_Requests {
	//request body using HashMap
	
	static String post_base_url="https://jsonplaceholder.typicode.com/posts";

	//@Test(priority=1)
	public void PostByHashMapBody()
	{
		HashMap postData=new HashMap();
		postData.put("userId",23);
		postData.put("title", "this is post done by abhishek");
		postData.put("body", "this is abhishek");
		given().contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(postData)
		.when().post(post_base_url)
		.then().statusCode(201).body("userId",equalTo(23))
		.log().all();
	}
	//@Test(priority=1)
	public void PostByJSONObj()
	{
		JSONObject postData=new JSONObject();
		postData.put("userId",24);
		postData.put("title", "this is post done by abhishek");
		postData.put("body", "this is abhishek");
		given().contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(postData.toString())
		.when().post(post_base_url)
		.then().statusCode(201).body("userId",equalTo(24))
		.log().all();
	}
	@Test(priority=1)
	public void PostByJSONFILE() throws FileNotFoundException
	{
		File f=new File(".\\testData\\post.json");
		FileReader fr=new FileReader(f);
		JSONTokener jk=new JSONTokener(fr);
		JSONObject postData=new JSONObject(jk);
		given().contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(postData.toString())
		.when().post(post_base_url)
		.then().statusCode(201).body("userId",equalTo(25))
		.log().all();
	}
	@Test(priority=2)
	public void deletePost()
	{
		given()
		.when().delete("https://jsonplaceholder.typicode.com/posts/23")
		.then().statusCode(200);
	}
	
	
	

}
