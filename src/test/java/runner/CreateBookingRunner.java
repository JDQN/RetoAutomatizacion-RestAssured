package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


/*
 * La class CreateBookingRunner permite crear una reserva
 * @Class BookingAllRunner
 * @author <jdquimbayo72@misena.edu.co>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = {"src/test/resources/features/booking/bookingPost.feature"},//Aqui llamamos la ruta del archivo feature
        glue = "stepDefinitions.booking",//Con la opcion glue llamamos la carpeta stepDefinitions.contact
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","html:target/CreateBookingReports.html"}
        //plugin = {"pretty", "html:target/cucumber-reportsContactRunner.html"} // TODO con la option plugin podemos generar reportes HTML
)
public class CreateBookingRunner {

}
