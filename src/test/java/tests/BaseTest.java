package tests;

import Constants.AppConstants;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected ThreadLocal<WebDriver>tdriver = new ThreadLocal<>();
    protected WebDriver driver;
    protected String browser;
    private ChromeOptions options;
    protected PageFactory pageFactory;
    protected FirefoxOptions firefoxOptions;
    public void setDriver(WebDriver driver){
        tdriver.set(driver);
    }
    public WebDriver getDriver(){
        return tdriver.get();
    }
    @Parameters({"browserName"})
    @BeforeTest
    public void setUp(@Optional String browserName){
        if(browserName!=null){
            browser = browserName;
        }else {
            browser = AppConstants.browserName;
        }
        if(browser.equalsIgnoreCase("Chrome")){
            if(AppConstants.platform.equalsIgnoreCase("local")){
                options = new ChromeOptions();
                options.addArguments("--remote-allow-origins");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                setDriver(driver);
            } else if (AppConstants.platform.equalsIgnoreCase("remote")) {
                try {
                    options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
                    setDriver(driver);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            if(AppConstants.platform.equalsIgnoreCase("local")){
                driver = new FirefoxDriver();
                setDriver(driver);
            } else if (AppConstants.platform.equalsIgnoreCase("remote")) {
                try {
                    firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--width=1920","--height=1080");
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"),firefoxOptions);
                    setDriver(driver);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            System.out.println("Entered browser name is not supported : " + browser);
        }
    }
    @BeforeMethod
    public void init(){
        pageFactory = new PageFactory(getDriver());
        logger.info("Launching App URL");
        getDriver().get("https://www.saucedemo.com/");
        getDriver().manage().window().maximize();
    }
    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }
    @AfterTest
    public void afterTest(){
        tdriver.remove();
    }
}
