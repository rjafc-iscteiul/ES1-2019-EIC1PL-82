import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({AboutTest.class,CompareToolsTest.class,ComparisonErrorTest.class,CreateRulesTest.class,GUITest.class})

public class AllTests {

}
