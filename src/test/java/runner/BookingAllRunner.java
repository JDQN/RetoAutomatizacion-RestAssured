package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


/*
 * La class BookingAllRunner permite correr los scenarios creados de pruebas
 * @Class BookingAllRunner
 * @author <jdquimbayo72@misena.edu.co>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = {"src/test/resources/features/bookingAll/bookingAllGet.feature"},//Aqui llamamos la ruta del archivo feature
        glue = "stepDefinitions.bookingAll",//Con la opcion glue llamamos la carpeta stepDefinitions.contact
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","html:target/BookingAllReports.html"}
        //plugin = {"pretty", "html:target/cucumber-reportsContactRunner.html"} //Con la option plugin podemos generar reportes HTML
)
public class BookingAllRunner {
}
