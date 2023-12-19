package posts;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;

public class fileUploadAPI {
@Test(priority=1)
public void singleFileUpload()
{
	File f=new File(".\\testData\\post.json");//if n files and then create n file objects
	//.multipart("files",fileobj1),.multipart("files",fileobjn)
	//rest same
	given()
	.multiPart("file",f)
	.contentType("multipart/form-data")
	.when().post("http://postman-echo.com/post")
	.then()
	.statusCode(200);
}
}
