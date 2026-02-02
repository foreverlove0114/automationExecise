package testCases;

import pageObjects.HomePage;
import pageObjects.ProductsPage;
import testBase.BaseClass;

public class TC12_addProduct extends BaseClass {

    public void testAddProductIntoCart(){
        initializeHomePage();
        HomePage hp = new HomePage(getDriver());
        //4. Click 'Products' button
        ProductsPage pp = hp.clickNavigateToProductPage();
        //5. Hover over first product and click 'Add to cart'

    }
}
