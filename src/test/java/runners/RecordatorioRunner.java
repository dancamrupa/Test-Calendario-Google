package runners;

import cucumber.api.SnippetType;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/Recordatorio.feature",
        glue = "stepDefinitions",
        snippets = SnippetType.CAMELCASE
)

public class RecordatorioRunner {
}
