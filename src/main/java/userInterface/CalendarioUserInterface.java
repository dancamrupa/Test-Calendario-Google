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
