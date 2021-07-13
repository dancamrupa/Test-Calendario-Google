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
