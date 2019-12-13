import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({AboutTest.class,ComparisonErrorTest.class,GUITest.class,CompareToolsTest.class,ChangeThresholdsTest.class,CreateRulesTest.class})


public class AllTests {

}
