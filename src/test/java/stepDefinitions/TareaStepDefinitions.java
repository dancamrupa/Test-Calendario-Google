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


public class TareaStepDefinitions {

    @Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^ingresar el usuario y password en la pagina de calendario de google con la url (.*)$")
    public void ingresoElUsuarioYPasswordEnLaPaginaDeCalendarioDeGoogleConLaUrlHttpsCalendarGoogleCom(String url) {
        OnStage.theActorCalled("Camilo").can(BrowseTheWeb.with(SeleniumWebDriver.ChromeWebDriver().on(url)));
    }

    @When("^creo una tarea en el calendario$")
    public void creoUnaTareEnElCalendario(List<Persona> personas) {
        OnStage.theActorInTheSpotlight().attemptsTo(CrearTarea.onUsuario(personas.get(0).getUsername(),personas.get(0).getPassword()));
    }

    @When("^modifico una tarea creada$")
    public void modificoUnaTareaCreada(List<Persona> personas) {
        OnStage.theActorInTheSpotlight().attemptsTo(ModificarTarea.onUsuario(personas.get(0).getUsername(),personas.get(0).getPassword()));
    }

    @When("^elimino una tarea creada$")
    public void eliminoUnaTareaCreada(List<Persona> personas) {
        OnStage.theActorInTheSpotlight().attemptsTo(EliminarTarea.onUsuario(personas.get(0).getUsername(),personas.get(0).getPassword()));
    }

    @Then("^podre ver la tarea en pantalla$")
    public void podreVerLaTareaEnPantalla() {

    }

    @Then("^no podre ver la tarea en pantalla$")
    public void noPodreVerLaTareaEnPantalla() {

    }
}

