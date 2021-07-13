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


public class EventoStepDefinitions {

    @Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^ingreso el usuario y password en la pagina de calendario de google con la url (.*)$")
    public void ingresoElUsuarioYPasswordEnLaPaginaDeCalendarioDeGoogleConLaUrlHttpsCalendarGoogleCom(String url) {
        OnStage.theActorCalled("Camilo").can(BrowseTheWeb.with(SeleniumWebDriver.ChromeWebDriver().on(url)));
    }

    @When("^creo un nuevo evento en el calendario$")
    public void creoUnNuevoEventoEnElCalendario(List<Persona> personas) {
        OnStage.theActorInTheSpotlight().attemptsTo(CrearEvento.onUsuario(personas.get(0).getUsername(),personas.get(0).getPassword()));
    }

    @When("^modifico un evento creado$")
    public void modificoUnEventoCreado(List<Persona> personas) {
        OnStage.theActorInTheSpotlight().attemptsTo(ModificarEvento.onUsuario(personas.get(0).getUsername(),personas.get(0).getPassword()));
    }

    @When("^elimino un evento creado$")
    public void eliminoUnEventoCreado(List<Persona> personas) {
        OnStage.theActorInTheSpotlight().attemptsTo(EliminarEvento.onUsuario(personas.get(0).getUsername(),personas.get(0).getPassword()));
    }


    @Then("^podre ver el evento en pantalla$")
    public void podreVerElEventoEnPantalla() {

    }

    @Then("^no podre ver el evento en pantalla$")
    public void noPodreVerElEventoEnPantalla() {

    }
}
