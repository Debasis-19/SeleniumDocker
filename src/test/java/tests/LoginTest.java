package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    @Test
    public void loginTest(){
        pageFactory.getLoginPage().loginToApp("standard_user","secret_sauce");
        System.out.println(pageFactory.getProductPage().getTitleOfPage());
    }
}
