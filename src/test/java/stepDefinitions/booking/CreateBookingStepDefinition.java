package stepDefinitions.booking;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import runner.CreateBookingRunner;
import stepDefinitions.setup.BaseTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


public class CreateBookingStepDefinition extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(CreateBookingRunner.class);

    private RequestSpecification request;

    private Response response;


    @Given("que ingreso los datos correctos en el formulario")
    public void que_ingreso_los_datos_correctos_en_el_formulario() {
        try {
            generalSetUpPost();
            request =   given().body("{\n" +
                    "    \"firstname\" : \"Jim\",\n" +
                    "    \"lastname\" : \"Brown\",\n" +
                    "    \"totalprice\" : 111,\n" +
                    "    \"depositpaid\" : true,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"2018-01-01\",\n" +
                    "        \"checkout\" : \"2019-01-01\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"Breakfast\"\n" +
                    "}");

        } catch (Exception exception) {
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @When("se da click en el boton enviar")
    public void se_da_click_en_el_boton_enviar() {
        try{
            response = request.post(BASE_PATH_POST);
        } catch (Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @Then("se recibira un mensaje de reserva creada exitosamente")
    public void se_recibira_un_mensaje_de_reserva_creada_exitosamente() {
        try{
            response
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("booking",notNullValue());
        }catch (Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }

}
