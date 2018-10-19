import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseSeleniumTests {

    private static final String _DRIVERPATH= "src/main/resources/chromedriver.exe";
    protected WebDriver chromeDriver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", _DRIVERPATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        chromeDriver = new ChromeDriver(options);

    }

    @AfterMethod
    public void tearDown(){
        chromeDriver.quit();
    }

}
