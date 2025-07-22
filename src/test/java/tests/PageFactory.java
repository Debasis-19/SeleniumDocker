package tests;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductPage;

public class PageFactory {
    private final WebDriver driver;
    public PageFactory(WebDriver driver){
        this.driver = driver;
    }
    public LoginPage getLoginPage(){
        return new LoginPage(driver);
    }
    public ProductPage getProductPage(){
        return new ProductPage(driver);
    }
}
