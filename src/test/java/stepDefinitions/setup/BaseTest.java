package stepDefinitions.setup;

import co.com.restfulbooker.model.UsuarioModel;
import co.com.restfulbooker.model.UsuarioModelDates;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import java.sql.Date;

import static io.restassured.RestAssured.given;

/*
 * @author <jdquimbayo72@misena.edu.co>
 * La class BaseTest permite definir la url y el path
 * En el metodo generalSetUp se configura la url y el path
 */
public class BaseTest {

    protected static final String BASE_URL = "https://restful-booker.herokuapp.com/";
    protected static final String BASE_PATH = "booking";
    protected  UsuarioModel usuarioModel;

    //Metodo generalSetUp
    protected void generalSetUp(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
        //RestAssured.filters(new RequestLoggingFilter(),new ErrorLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    protected void generarUsuario(){

        Faker faker = new Faker();

        usuarioModel = new UsuarioModel();
        usuarioModel.setFirstName(faker.name().firstName());
        usuarioModel.setLastName(faker.name().lastName());
        usuarioModel.setTotalPrice((float) faker.number().numberBetween(100,200));
        usuarioModel.setDepositpaid(faker.random().nextBoolean());

        UsuarioModelDates usuarioModelDates = new UsuarioModelDates();
        usuarioModelDates.setCheckin(faker.date().between(Date.valueOf("2015-11-06"), Date.valueOf("2022-10-23")));
        usuarioModelDates.setCheckout(faker.date().between(Date.valueOf("2015-12-24"), Date.valueOf("2030-10-23")));

        usuarioModel.setBookingDates(usuarioModelDates);
        usuarioModel.setBookingDates(usuarioModelDates);
        usuarioModel.setAdditionalNeeds(faker.lorem().word());
    }

    protected String bodyUser(){
        return  "{\n" +
            "    \"firstname\" : \""+usuarioModel.getFirstName()+"\",\n" +
            "    \"lastname\" : \""+usuarioModel.getLastName()+"\",\n" +
            "    \"totalprice\" : "+usuarioModel.getTotalPrice()+",\n" +
            "    \"depositpaid\" : "+usuarioModel.getDepositpaid()+",\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \""+usuarioModel.getBookingDates().getCheckin()+"\",\n" +
            "        \"checkout\" : \""+usuarioModel.getBookingDates().getCheckout()+"\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast\"\n" +
            "}";
    }

}