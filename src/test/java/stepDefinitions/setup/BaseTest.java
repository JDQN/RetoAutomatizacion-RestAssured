package stepDefinitions.setup;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class BaseTest {

    protected static final String BASE_URL = "https://restful-booker.herokuapp.com/";
    protected static final String BASE_PATH_POST = "booking";

    //protected static final String BASE_PATH_GET = "/features/booking";


    //Metodo POST
    public void configurationForRestAssuredPost(){
        RestAssured.baseURI = BASE_URL;
        //RestAssured.basePath = BASE_PATH_POST;
        //RestAssured.filters(new RequestLoggingFilter(),new ErrorLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
    protected void generalSetUpPost(){
        configurationForRestAssuredPost();
    }




    //Metodo GET
//    public void configurationForRestAssuredGet(){
//        RestAssured.baseURI = BASE_URL;
//        //RestAssured.basePath = BASE_PATH_GET;
//        //RestAssured.filters(new RequestLoggingFilter(),new ErrorLoggingFilter(), new ResponseLoggingFilter());
//        RestAssured.requestSpecification = new RequestSpecBuilder()
//                .setContentType(ContentType.JSON)
//                .build();
//    }
//    protected void generalSetUpGet(){
//        configurationForRestAssuredGet();
//    }





}
