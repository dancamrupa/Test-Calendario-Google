# Introduccion

Este repositorio contiene la automatización de la aplicación *Calendario de la suite de Google*. Este proyecto se realizó con el entorno de desarrollo **Intellij IDEA versión 2021.1.1**, herramientas de compilación como **Gradle**, frameworks como **SerenityBDD**, patrones de diseño como **Screenplay**, herramientas de prueba como **Cucumber**. Se realizaron 6 escenarios automatizados, el cual, realizaran la *creación, modificación y eliminación* de un **evento** y una **tarea** en el *calendario de Google*, y 3 escenarios manuales que guiaran a la ejecución de la *creación, modificación y eliminación* de un **recordatorio** en el *calendario de Google*. Al final de este **README** encontrará los pasos para la descarga y ejecución de este proyecto.

## Estructura Codigo Fuente

| 			 | 											  |
| :-----: 		 | :----: 										  |
| **Driver**    	 | Contiene el driver del navegador 							  |
| **Interactions**       | Contiene todas las interaciones que se ejecutaran en la automatizacion 	 	  |
| **Models**             | Contiene todos los modelos que se utilizaran para la construccion de la automatizacion |
| **Tasks**              | Contiene todas las tareas que se ejecutaran en la automatizacion			  |
| **UserInterface**      | Contiene todos los elementos de la interface usuario mapeados en la pagina 		  |
| **Runners**            | Contiene todos los ejecutores de las pruebas automatizadas 				  |
| **Steps Definitions**  | Contiene todos los pasos de la ejecucion de cada prueba automatizada 		  |
| **Features**           | Contiene todos los esenarios codificados en lenguaje Gherking 			  |


## Drivers

### SeleniumWebDriver

Esta clase contiene el lanzador del navegador a utilizar para la automatizacion, se inicializa una variable WebDriver quien es utilizada en el metodo para levantar el navegador y asignarle la url.
```ruby
	package driver;
	
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;

	public class SeleniumWebDriver {

		public static WebDriver driver;

		public static SeleniumWebDriver ChromeWebDriver() {
		
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--disable-infobars");

			driver = new ChromeDriver(options);
			return new SeleniumWebDriver();
	    }
	
		public static WebDriver on(String url) {
		
			driver.get(url);
			return driver;
	    }
	}
	
```  
## Interactions

### Espera

Realiza la espera implicita, esta tarea implementa la interfaz Interaction y sobreescribe su metodo, tambien recibe un parametro de tipo int.
```ruby
	package interactions;

	import driver.SeleniumWebDriver;
	import net.serenitybdd.screenplay.Actor;
	import net.serenitybdd.screenplay.Interaction;
	import net.serenitybdd.screenplay.Performable;
	import net.serenitybdd.core.steps.Instrumented;
	import java.util.concurrent.TimeUnit;

	public class Esperar implements Interaction{

		private int seconds;

		public Esperar(int seconds){
			this.seconds = seconds;
		}

		@Override
		public<T extends Actor> void performAs(T actor){
			SeleniumWebDriver.driver.manage().timeouts().implicitlyWait(seconds,TimeUnit.SECONDS);
		}

		public static Performable estosSegundos(int seconds){
			return Instrumented.instanceOf(Esperar.class).withProperties(seconds);
		}
	}
```
## Models

### Persona

Esta clase crea un objeto de tipo persona que contiene dos variables de tipo string, el constructor y los getter y setter correspondiente a cada variable.
```ruby
	package models;

	public class Persona {

		private String username, password;

		public Persona() {
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
```  
## Task

### CrearEvento

Realiza el inicio de sesion en la pagina del Calendario de Google. Esta tarea selecciona una fecha en el calendario y crea un evento.
```ruby
	package tasks;

	import interactions.Esperar;
	import net.serenitybdd.screenplay.Task;
	import net.serenitybdd.screenplay.Actor;
	import userInterface.CalendarioUserInterface;
	import net.serenitybdd.core.steps.Instrumented;
	import net.serenitybdd.screenplay.actions.Click;
	import net.serenitybdd.screenplay.actions.Enter;


	public class CrearEvento implements Task{

		private String usuario, password;

		public CrearEvento(String usuario, String password) {
		
			this.usuario = usuario;
			this.password = password;
		}

		@Override
		public <T extends Actor> void performAs(T actor) {
			actor.attemptsTo(

					Enter.theValue(usuario).into(CalendarioUserInterface.TXT_USERNAME),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_USERNAME),
					Enter.theValue(password).into(CalendarioUserInterface.TXT_PASSWORD),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_LOGIN),
					Click.on(CalendarioUserInterface.BTN_FECHA_CALENDARIO),
					Click.on(CalendarioUserInterface.BTN_CREAR_EVENTO),
					Click.on(CalendarioUserInterface.BTN_EVENTO),
					Click.on(CalendarioUserInterface.BTN_GUARDAR_EVENTO),
					Esperar.estosSegundos(10)
			);
		}

		public static CrearEvento onUsuario(String usuario,String password) {
			return Instrumented.instanceOf(CrearEvento.class).withProperties(usuario, password);
		}

	}
```  
### ModificarEvento
  
Realiza el inicio de sesion en la pagina del Calendario de Google. Esta tarea selecciona un evento ya creado en el calendario y modifica este evento ingresando una nueva informacion.

```ruby
	package tasks;

	import interactions.Esperar;
	import net.serenitybdd.screenplay.Task;
	import net.serenitybdd.screenplay.Actor;
	import net.serenitybdd.screenplay.actions.SelectFromOptions;
	import userInterface.CalendarioUserInterface;
	import net.serenitybdd.core.steps.Instrumented;
	import net.serenitybdd.screenplay.actions.Click;
	import net.serenitybdd.screenplay.actions.Enter;

	public class ModificarEvento implements Task {

		private String usuario, password;

		public ModificarEvento(String usuario, String password) {
			this.usuario = usuario;
			this.password = password;

		}
		@Override
		public <T extends Actor> void performAs(T actor) {
			actor.attemptsTo(

					Enter.theValue(usuario).into(CalendarioUserInterface.TXT_USERNAME),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_USERNAME),
					Enter.theValue(password).into(CalendarioUserInterface.TXT_PASSWORD),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_LOGIN),

					Click.on(CalendarioUserInterface.BTN_FECHA_CALENDARIO),
					Click.on(CalendarioUserInterface.BTN_EVENTO_AGENDADO),
					Click.on(CalendarioUserInterface.BTN_EDITAR_EVENTO),
					Esperar.estosSegundos(5),
					Enter.theValue("Reunion de Automatizacion").into(CalendarioUserInterface.TXT_TITULO_EVENTO),
					Esperar.estosSegundos(5),
					Click.on(CalendarioUserInterface.BTN_TODO_EL_DIA),
					Esperar.estosSegundos(5),
					Click.on(CalendarioUserInterface.BTN_AGREGAR_VIDEOLLAMADA),
					Esperar.estosSegundos(5),
					Enter.theValue("Selenium web driver con Cucumber y Serenity con Screenplay").into(CalendarioUserInterface.TXT_DESCRIPCION_EVENTO),
					Esperar.estosSegundos(5),
					Click.on(CalendarioUserInterface.BTN_GUARDAR_CAMBIOS),
					Esperar.estosSegundos(10)
			);
		}

		public static ModificarEvento onUsuario(String usuario,String password) {
			return Instrumented.instanceOf(ModificarEvento.class).withProperties(usuario, password);
		}
	}
```
### EliminarEvento

Realiza el inicio de sesion en la pagina del Calendario de Google. Esta tarea selecciona un evento ya creado en el calendario y lo elimina.
```ruby
	package tasks;

	import interactions.Esperar;
	import net.serenitybdd.screenplay.Task;
	import net.serenitybdd.screenplay.Actor;
	import userInterface.CalendarioUserInterface;
	import net.serenitybdd.core.steps.Instrumented;
	import net.serenitybdd.screenplay.actions.Click;
	import net.serenitybdd.screenplay.actions.Enter;

	public class EliminarEvento implements Task{

		private String usuario, password;

		public EliminarEvento(String usuario, String password) {
			this.usuario = usuario;
			this.password = password;
		}
		@Override
		public <T extends Actor> void performAs(T actor) {
			actor.attemptsTo(

					Enter.theValue(usuario).into(CalendarioUserInterface.TXT_USERNAME),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_USERNAME),
					Enter.theValue(password).into(CalendarioUserInterface.TXT_PASSWORD),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_LOGIN),

					Click.on(CalendarioUserInterface.BTN_FECHA_CALENDARIO),
					Click.on(CalendarioUserInterface.BTN1_EVENTO_AGENDADO),
					Esperar.estosSegundos(5),
					Click.on(CalendarioUserInterface.BTN_ELIMINAR_EVENTO),
					Esperar.estosSegundos(5),
					Esperar.estosSegundos(10)
			);
		}

		public static EliminarEvento onUsuario(String usuario,String password) {
			return Instrumented.instanceOf(EliminarEvento.class).withProperties(usuario, password);
		}
	}
```  
### CrearTarea

Realiza el inicio de sesion en la pagina del Calendario de Google. Esta tarea selecciona una fecha en el calendario y crea una tarea.
```ruby
	package tasks;

	import interactions.Esperar;
	import net.serenitybdd.screenplay.Task;
	import net.serenitybdd.screenplay.Actor;
	import userInterface.CalendarioUserInterface;
	import net.serenitybdd.core.steps.Instrumented;
	import net.serenitybdd.screenplay.actions.Click;
	import net.serenitybdd.screenplay.actions.Enter;

	public class CrearTarea implements Task{

		private String usuario, password;

		public CrearTarea(String usuario, String password) {
			this.usuario = usuario;
			this.password = password;

		}
		@Override
		public <T extends Actor> void performAs(T actor) {
			actor.attemptsTo(

					Enter.theValue(usuario).into(CalendarioUserInterface.TXT_USERNAME),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_USERNAME),
					Enter.theValue(password).into(CalendarioUserInterface.TXT_PASSWORD),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_LOGIN),

					Click.on(CalendarioUserInterface.BTN1_FECHA_CALENDARIO),
					Click.on(CalendarioUserInterface.BTN_CREAR_EVENTO),
					Click.on(CalendarioUserInterface.BTN_TAREA),
					Click.on(CalendarioUserInterface.BTN_GUARDAR_TAREA),
					Esperar.estosSegundos(10)
			);
		}

		public static CrearTarea onUsuario(String usuario,String password) {
			return Instrumented.instanceOf(CrearTarea.class).withProperties(usuario, password);
		}

	}
```
### ModificarTarea

Realiza el inicio de sesion en la pagina del Calendario de Google. Esta tarea selecciona una tarea ya creada en el calendario y la modifica ingresando una nueva informacion.
```ruby
	package tasks;

	import interactions.Esperar;
	import net.serenitybdd.screenplay.Task;
	import net.serenitybdd.screenplay.Actor;
	import userInterface.CalendarioUserInterface;
	import net.serenitybdd.core.steps.Instrumented;
	import net.serenitybdd.screenplay.actions.Click;
	import net.serenitybdd.screenplay.actions.Enter;

	public class ModificarTarea implements Task{

		private String usuario, password;

		public ModificarTarea(String usuario, String password) {
			this.usuario = usuario;
			this.password = password;

		}
		@Override
		public <T extends Actor> void performAs(T actor) {
			actor.attemptsTo(

					Enter.theValue(usuario).into(CalendarioUserInterface.TXT_USERNAME),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_USERNAME),
					Enter.theValue(password).into(CalendarioUserInterface.TXT_PASSWORD),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_LOGIN),

					Click.on(CalendarioUserInterface.BTN1_FECHA_CALENDARIO),
					Click.on(CalendarioUserInterface.BTN_TAREA_AGENDADA),
					Click.on(CalendarioUserInterface.BTN_EDITAR_TAREA),
					Esperar.estosSegundos(5),
					Enter.theValue("Entregar la automatizacion asignada").into(CalendarioUserInterface.TXT_TITULO_TAREA),
					Esperar.estosSegundos(5),
					Enter.theValue("8:30am").into(CalendarioUserInterface.BTN_HORA_TAREA),
					Esperar.estosSegundos(5),
					Enter.theValue("Automatizar una suite de Google").into(CalendarioUserInterface.TXT_DESCRIPCION_TAREA),
					Esperar.estosSegundos(5),
					Click.on(CalendarioUserInterface.BTN_GUARDAR_TAREA),
					Esperar.estosSegundos(10)
			);
		}

		public static ModificarTarea onUsuario(String usuario,String password) {
			return Instrumented.instanceOf(ModificarTarea.class).withProperties(usuario, password);
		}

	}
```  
### EliminarTarea

Realiza el inicio de sesion en la pagina del Calendario de Google. Esta tarea selecciona una tarea ya creada en el calendario y la elimina.
```ruby
	package tasks;

	import interactions.Esperar;
	import net.serenitybdd.screenplay.Task;
	import net.serenitybdd.screenplay.Actor;
	import userInterface.CalendarioUserInterface;
	import net.serenitybdd.core.steps.Instrumented;
	import net.serenitybdd.screenplay.actions.Click;
	import net.serenitybdd.screenplay.actions.Enter;

	public class EliminarTarea implements Task{

		private String usuario, password;

		public EliminarTarea(String usuario, String password) {
			this.usuario = usuario;
			this.password = password;
		}
		@Override
		public <T extends Actor> void performAs(T actor) {
			actor.attemptsTo(

					Enter.theValue(usuario).into(CalendarioUserInterface.TXT_USERNAME),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_USERNAME),
					Enter.theValue(password).into(CalendarioUserInterface.TXT_PASSWORD),
					Click.on(CalendarioUserInterface.BTN_SIGUIENTE_LOGIN),

					Click.on(CalendarioUserInterface.BTN1_FECHA_CALENDARIO),
					Click.on(CalendarioUserInterface.BTN_TAREA_AGENDADA),
					Esperar.estosSegundos(5),
					Click.on(CalendarioUserInterface.BTN_ELIMINAR_TAREA),
					Esperar.estosSegundos(5),
					Esperar.estosSegundos(10)
			);
		}

		public static EliminarTarea onUsuario(String usuario,String password) {
			return Instrumented.instanceOf(EliminarTarea.class).withProperties(usuario, password);
		}

	}
```  
## UserInterface

### CalendarioUserInterface

Esta clase contiene todos los mapeos de los elementos de la aplicacion Calendario de Google, el cual, se utilizan para realizar las difetentes interacciones.
```ruby
	package userInterface;

	import net.serenitybdd.screenplay.targets.Target;

	public class CalendarioUserInterface {

		//LOGIN
		public static final Target TXT_USERNAME = Target.the("").locatedBy("//input[@autocomplete = 'username']");
		public static final Target BTN_SIGUIENTE_USERNAME = Target.the("").locatedBy("//span[contains (text(), 'Siguiente')]");
		public static final Target TXT_PASSWORD = Target.the("").locatedBy("//input[@autocomplete = 'current-password']");
		public static final Target BTN_SIGUIENTE_LOGIN = Target.the("").locatedBy("//span[contains (text(), 'Siguiente')]");

		//CREACIÓN DE EVENTO
		public static final Target BTN_FECHA_CALENDARIO = Target.the("").locatedBy("//span[@data-date = '20210729']");
		public static final Target BTN_CREAR_EVENTO = Target.the("").locatedBy("//div[contains (text(), 'Crear')]");
		public static final Target BTN_EVENTO = Target.the("").locatedBy("//span[contains (text(), 'Evento')]");
		public static final Target BTN_GUARDAR_EVENTO = Target.the("").locatedBy("//span[contains (text(), 'Guardar')]");

		//MODIFICAR EVENTO
		public static final Target BTN_EVENTO_AGENDADO = Target.the("").locatedBy("//div[@class = 'lFe10c']");
		public static final Target BTN_EDITAR_EVENTO = Target.the("").locatedBy("//div[@aria-label= 'Editar evento']");
		public static final Target TXT_TITULO_EVENTO = Target.the("").locatedBy("//input[@id = 'xTiIn']");
		public static final Target BTN_TODO_EL_DIA = Target.the("").locatedBy("//div[@id = 'xAlDaCbx']");
		public static final Target BTN_AGREGAR_VIDEOLLAMADA = Target.the("").locatedBy("//div[@id = 'xAddRtcSel']");
		public static final Target TXT_DESCRIPCION_EVENTO = Target.the("").locatedBy("//div[@jsname='yrriRe']");
		public static final Target BTN_GUARDAR_CAMBIOS = Target.the("").locatedBy("//span[contains (text(), 'Guardar')]");

		//ELIMINAR EVENTO
		public static final Target BTN1_EVENTO_AGENDADO = Target.the("").locatedBy("//span[@class = 'yzifAd']");
		public static final Target BTN_ELIMINAR_EVENTO = Target.the("").locatedBy("//div[@id= 'xDetDlgDelBu']");

		//CREAR TAREA
		public static final Target BTN1_FECHA_CALENDARIO = Target.the("").locatedBy("//span[@data-date = '20210730']");
		public static final Target BTN_TAREA = Target.the("").locatedBy("//span[contains(text(), 'Tarea')]");
		public static final Target BTN_GUARDAR_TAREA = Target.the("").locatedBy("//span[contains (text(), 'Guardar')]");

		//MODIFICAR TAREA
		public static final Target BTN_TAREA_AGENDADA = Target.the("").locatedBy("//div[@class = 'lFe10c']");
		public static final Target BTN_EDITAR_TAREA = Target.the("").locatedBy("//div[@aria-label = 'Editar tarea']");
		public static final Target TXT_TITULO_TAREA = Target.the("").locatedBy("//div[@jsname='GYcwYe']//input[@class='whsOnd zHQkBf']");
		public static final Target BTN_HORA_TAREA = Target.the("").locatedBy("//input[@id = 'xStTiIn']");
		public static final Target BTN1_TODO_EL_DIA = Target.the("").locatedBy("//label[@class = 'Kpcujb']");
		public static final Target TXT_DESCRIPCION_TAREA = Target.the("").locatedBy("//textarea[@jsname = 'YPqjbf']");
		public static final Target BTN1_GUARDAR_TAREA = Target.the("").locatedBy("//span[contains (text(), 'Guardar')]");

		//ELIMINAR TAREA
		public static final Target BTN_ELIMINAR_TAREA = Target.the("").locatedBy("//div[@aria-label = 'Eliminar tarea']");

	}
```  
## Runners

### EventoRunner

Su funcion es llamar los pasos asignados en el feature **Evento.feature** y busca los metodos correspondientes en el paquete de **stepDefinitios** para realizar la ejecucion que es crear, modificar y eliminar un evento. Esta clase corre mediante el **@RunWith** de la clase *CucumberWithSerenity.class* y mediante el **@CucumberOptions** llama al feature correspondiente, el paquete que contiene los **Steps Definitions** y el **CamelCase**.
```ruby
	package runners;

	import cucumber.api.SnippetType;
	import org.junit.runner.RunWith;
	import cucumber.api.CucumberOptions;
	import net.serenitybdd.cucumber.CucumberWithSerenity;

	@RunWith(CucumberWithSerenity.class)
	@CucumberOptions(
			features = "src/test/resources/features/Evento.feature",
			glue = "stepDefinitions",
			snippets = SnippetType.CAMELCASE
	)

	public class EventoRunner {
	}
```  
### TareaRunner

Su funcion es llamar los pasos asignados en el feature **Evento.feature** y busca los metodos correspondientes en el paquete de **stepDefinitios** para realizar la ejecucion que es crear, modificar y eliminar una tarea. Esta clase corre mediante el **@RunWith** de la clase *CucumberWithSerenity.class* y mediante el **@CucumberOptions** llama al feature correspondiente, el paquete que contiene los **Steps Definitions** y el **CamelCase**.
```ruby
	package runners;

	import cucumber.api.SnippetType;
	import org.junit.runner.RunWith;
	import cucumber.api.CucumberOptions;
	import net.serenitybdd.cucumber.CucumberWithSerenity;

	@RunWith(CucumberWithSerenity.class)
	@CucumberOptions(
			features = "src/test/resources/features/Tarea.feature",
			glue = "stepDefinitions",
			snippets = SnippetType.CAMELCASE
	)

	public class TareaRunner {
	}
```
## StepDefinitions

Los steps definitions contienen todos los metodos llamados mediante el **Runner** al
**Feature**. Los metodos ejecutan las **tasks,interactions** y **questions** mediante un *actor*.

### EventoStepDefinitions

Contiene todos los paso a paso de la ejecucion de crear, modifica y eliminar un evento, este llama a las tareas *CrearEvento*, *ModifcarEvento* y *EliminarEvento*. 
```ruby
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
```  
### TareaStepDefinitions

Contiene todos los paso a paso de la ejecucion de crear, modifica y eliminar una tarea, este llama a las tareas *CrearTarea*, *ModifcarTarea* y *EliminarTarea*. 
```ruby
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
	
```
## Features

### Evento.feature

Contiene los escenarios exitosos y alternos de Crear, modificar y eliminar un evento. Los datos a utilizar en cada escenario estan digitados en lenguaje Gherking. 
```ruby
	Feature: HU-001 Calendario Google
			 	 Yo como usuario de Google
				  quiero crear, modificar y eliminar un evento
			 	 en la pagina del calendario de google

	  Background:
		Given ingreso el usuario y password en la pagina de calendario de google con la url https://calendar.google.com/

	  Scenario: Crear un evento en el calendario de Google
			When creo un nuevo evento en el calendario
			  |username                       |password   |
			  |estoessolounaprueba3@gmail.com |Pru3ba2021 |
			Then podre ver el evento en pantalla

	  Scenario: modificar un evento en el calendario de Google
			When modifico un evento creado
			  |username                       |password   |
			  |estoessolounaprueba3@gmail.com |Pru3ba2021 |
			Then podre ver el evento en pantalla

	  Scenario: Eliminar un cita en el calendario de Google
			When elimino un evento creado
			  |username                       |password   |
			  |estoessolounaprueba3@gmail.com |Pru3ba2021 |
			Then no podre ver el evento en pantalla
```	
### Tarea.feature

Contiene los escenarios exitosos y alternos de Crear, modificar y eliminar una tarea. Los datos a utilizar en cada escenario estan digitados en lenguaje Gherking. 
```ruby
	Feature: HU-002 Calendario Google
			      Yo como usuario de Google
			     quiero crear, modificar y eliminar una tarea
			     en la pagina del calendario de google

	  Background:
		Given ingresar el usuario y password en la pagina de calendario de google con la url https://calendar.google.com/

	  Scenario: Crear una tarea en el calendario de Google
			When creo una tarea en el calendario
			  |username                       |password   |
			  |estoessolounaprueba3@gmail.com |Pru3ba2021 |
			Then podre ver la tarea en pantalla

	  Scenario: Modificar una tarea en el calendario de Google
			When modifico una tarea creada
			  |username                       |password   |
			  |estoessolounaprueba3@gmail.com |Pru3ba2021 |
			Then podre ver la tarea en pantalla

	  Scenario: Eliminar una tarea en el calendario de Google
			When elimino una tarea creada
			  |username                       |password   |
			  |estoessolounaprueba3@gmail.com |Pru3ba2021 |
			Then no podre ver la tarea en pantalla
			
```	
## Descarga y Ejecucion

Para descargar este proyecto dar clic en el boton **Code**, luego dar clic en la opción **download ZIP**. Después de descargar la carpeta del proyecto **Test Calendario Google**, dar clic derecho y escoger la opción extraer aqui. Despues abrimos la carpeta **Test Calendario Google** y estando dentro de ella oprimimos la tecla shif y al mismo tiempo damos clic derecho y escogemos la opción que dice *Abrir ventana de comandos aqui*. Luego copiamos y pegamos el siguiente código para realizar la ejecución de los escenarios y generar el reporte del proyecto: 

`gradle clean test aggregate`

Este comando ejecutara todos los escenarios implementados en el proyecto:

` 7 actionable tasks: 7 executed`

Al finalizar debemos ingresar y abrir el archivo *index.html* que se encuentra en la siguiente ruta

`Test Calendario Google\target\site\serenity.index.html`

Para descargar el archivo con las pruebas manuales, buscar en la lista del protecto **Test Calendario Google** el archivo *Pruebas Manuales - Calendario Google* y le da clic, luego le da clic en el boton *Download* y despues abrir el archivo *Pruebas Manuales - Calendario Google.xls*. Allí encontrará 3 escenarios para ejecutarlos de manera manual.






