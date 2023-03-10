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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * La class CreateBookingStepDefinition permite interartuar con los stepDefinitions
 * Y extends de BaseTest
 * @Class BookingAllRunner
 * @author <jdquimbayo72@misena.edu.co>
 */
public class CreateBookingStepDefinition extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(CreateBookingRunner.class);

    private RequestSpecification request;

    private Response response;


    @Given("que ingreso los datos correctos en el formulario")
    public void que_ingreso_los_datos_correctos_en_el_formulario() {
        try {
            generalSetUp();
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
            response = request.post();
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


    //Scenario 2
    @Given("que ingresa en el formulario de reserva una fecha pasada {string} a la fecha presente {string}")
    public void queIngresaEnElFormularioDeReservaUnaFechaPasadaALaFechaPresente(String checkin, String checkout) {
        try {
            generalSetUp();
            request =   given().body("{\n" +
                    "    \"firstname\" : \"Jimi\",\n" +
                    "    \"lastname\" : \"Brow\",\n" +
                    "    \"totalprice\" : 111,\n" +
                    "    \"depositpaid\" : true,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \""+checkin+"\",\n" +
                    "        \"checkout\" : \""+checkout+"\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"Breakfast\"\n" +
                    "}");

        } catch (Exception exception) {
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @When("da click en el boton crear reserva")
    public void aClickEnElBotonCrearReserva() {
        try {
            response = request.post();
        }catch (Exception exception) {
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }
    @Then("recibo un mensaje indicando que la fecha de la reserva es incorrecta")
    public void reciboUnMensajeIndicandoQueLaFechaDeLaReservaEsIncorrecta() {
        try{
            String checkin = response.body().jsonPath().getString("booking.bookingdates.checkin");
            String checkout = response.body().jsonPath().getString("booking.bookingdates.checkout");
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaCheckin = fechaHora.parse(checkin);
            Date fechaCheckout = fechaHora.parse(checkout);
            Boolean respuesta = fechaCheckin.before(fechaCheckout);
            assertTrue(respuesta);
        }catch (Exception exception){
            Assertions.fail(exception.getMessage(),exception);
            LOGGER.error(exception.getMessage(),exception);
        }
    }

}
