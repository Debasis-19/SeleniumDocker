package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage{
    private final By productNames = By.xpath("//div[@data-test='inventory-item-name']");
    private final By productTitle = By.xpath("//span[@data-test='title']");
    private final By hamburgerMenuBtn = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleOfPage(){
        return driver.findElement(productTitle).getText();
    }
    public List<WebElement> getAllProductsNames(){
        return driver.findElements(productNames);
    }
    public void logoutFromApp(){
        driver.findElement(hamburgerMenuBtn).click();
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logout.click();
    }
}
