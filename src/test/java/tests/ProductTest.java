package tests;


import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;



import java.util.List;

public class ProductTest extends BaseTest {
    Logger logger = LoggerFactory.getLogger(ProductTest.class);
    @Test
    public void getAllProductsNameTest(){
        logger.info("Logging to App");
        pageFactory.getLoginPage().loginToApp("standard_user","secret_sauce");
        logger.info("Application logged in successfully");
        List<WebElement>productList =  pageFactory.getProductPage().getAllProductsNames();
        logger.info("Product names fetched : ");
        productList.forEach(element -> System.out.println(element.getText()));
        pageFactory.getProductPage().logoutFromApp();
        logger.info("Application logged out");
    }
}
