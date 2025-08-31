package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExtentReportManager;

import java.io.File;
import java.io.IOException;

public class Hooks {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    WebDriver driver;
    @BeforeAll
    public static void beforeAll() {
        ConfigReader.loadConfig();
        extent = ExtentReportManager.getReporter();
    }
    @Before
    public void setup(Scenario scenario) {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        test.set(extent.createTest(scenario.getName()));
    }
    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = System.getProperty("user.dir") + "/reports/screenshots/" + scenario.getName() + ".png";
            try {
                FileUtils.copyFile(screenshot, new File(screenshotPath));
                test.get().log(Status.FAIL, "Test Failed - Screenshot attached")
                        .addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            test.get().log(Status.PASS, "Test Passed");
        }
        DriverFactory.quitDriver();
    }
    @AfterAll
    public static void afterAll() {
        extent.flush();
    }
}
