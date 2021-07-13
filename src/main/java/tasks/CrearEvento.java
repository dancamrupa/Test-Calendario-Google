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
