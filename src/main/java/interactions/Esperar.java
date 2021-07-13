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
        SeleniumWebDriver.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static Performable estosSegundos(int seconds){
        return Instrumented.instanceOf(Esperar.class).withProperties(seconds);
    }
}
