package abbeyLtd.TestComponent;

import abbeyLtd.PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        //properties class
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\abbeyLtd\\Resources\\GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        //prop.getProperty("browser");

        if (browserName.contains("chrome")) {

            WebDriverManager.chromedriver().setup();
            // Configure ChromeOptions to disable password manager and leak detection
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            options.setExperimentalOption("prefs", prefs);

            if(browserName.contains("headless")){
                options.addArguments("--headless");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
            }
            driver = new ChromeDriver(options); // Pass ChromeOptions to ChromeDriver
            driver.manage().window().setSize(new Dimension(1440, 900));
        }

        else if (browserName.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if(browserName.contains("--headless")){
                options.addArguments("headless");
            }
            driver = new FirefoxDriver(options); // Pass ChromeOptions to ChromeDriver
            driver.manage().window().setSize(new Dimension(1440, 900));
        }

        else if (browserName.contains("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if(browserName.contains("headless")){
                options.addArguments("--headless=new");
            }
            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read jsoon to string
        String jsonContent = FileUtils.readFileToString( new File(filePath), StandardCharsets.UTF_8);

        // convert string to Hashmap (needed jackson databind dependency)
        ObjectMapper mapper = new ObjectMapper();  // jackson read json
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;   // {{map1},{map2}}
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
