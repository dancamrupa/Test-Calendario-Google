package stepDefinitions;

import tasks.*;
import models.Persona;
import java.util.List;
import java.io.IOException;
import driver.SeleniumWebDriver;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;


public class RecordatorioStepDefinitions {

    @Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^digitar el usuario y password en la pagina de calendario de google con la url (.*)$")
    public void ingresoElUsuarioYPasswordEnLaPaginaDeCalendarioDeGoogleConLaUrlHttpsCalendarGoogleCom(String url) {
        OnStage.theActorCalled("Camilo").can(BrowseTheWeb.with(SeleniumWebDriver.ChromeWebDriver().on(url)));
    }

    @When("^creo un recordatorio en el calendario$")
    public void creoUnRecordatorioEnElCalendario(List<Persona> personas) {

    }

    @When("^modifico un recordatorio creado$")
    public void modificoUnRecordatorioCreado(List<Persona> personas) {

    }

    @When("^elimino un recordatorio creado$")
    public void eliminoUnRecordatorioCreado(List<Persona> personas) {

    }

    @Then("^podre ver el recordatorio en pantalla$")
    public void podreVerElRecordatorioEnPantalla() {

    }

    @Then("^no podre ver el recordatorio en pantalla$")
    public void noPodreVerElRecordatorioEnPantalla() {

    }
}
