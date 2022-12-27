package stepDefinitions.setup;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

/*
 * @author <jdquimbayo72@misena.edu.co>
 * La class BaseTest permite definir la url y el path
 * En el metodo generalSetUp se configura la url y el path
 */
public class BaseTest {

    protected static final String BASE_URL = "https://restful-booker.herokuapp.com/";
    protected static final String BASE_PATH = "booking";

    //Metodo generalSetUp
    protected void generalSetUp(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
        //RestAssured.filters(new RequestLoggingFilter(),new ErrorLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

}
