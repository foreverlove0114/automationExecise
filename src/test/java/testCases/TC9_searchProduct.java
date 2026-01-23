package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC9_searchProduct extends BaseClass {

    @Test
    public void testSearchProduct(){
        initializeHomePage();
        HomePage hp = new HomePage(getDriver());
        //4. Click on 'Products' button
        ProductsPage pp = hp.clickNavigateToProductPage();
        //5. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(pp.checkHeadingProductPagePresent());
        //6. Enter product name in search input and click search button
        pp.searchItem(TestData.searchProduct);
        //7. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(pp.isSearchProductHeadingPresent());
        //8. Verify all the products related to search are visible
        String searchProduct = pp.getProductName();
        System.out.println(searchProduct);
        Assert.assertEquals(searchProduct,TestData.searchProduct);
    }
}
