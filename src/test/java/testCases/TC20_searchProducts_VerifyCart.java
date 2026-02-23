package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import pageObjects.RegisterLoginPage;
import testBase.BaseClass;
import utilities.TestData;

public class TC20_searchProducts_VerifyCart extends BaseClass {

    @Test
    public void testSearchProducts_VerifyCart(){
        HomePage hp = new HomePage(getDriver());
        //3. Click on 'Products' button
        ProductsPage pp = hp.nav.clickProducts();
        //4. Verify user is navigated to ALL PRODUCTS page successfully
        pp.checkHeadingProductPagePresent();
        //5. Enter product name in search input and click search button
        pp.searchItem(TestData.searchProduct);
        //6. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(pp.isSearchProductHeadingPresent());
        //7. Verify all the products related to search are visible
        String searchProduct = pp.getProductName();
        System.out.println(searchProduct);
        Assert.assertEquals(searchProduct, TestData.searchProduct);
        //8. Add those products to cart
        pp.addProductToCartByIndex(1);
        //9. Click 'Cart' button and verify that products are visible in cart
        CartPage cp = pp.clickLinkViewCart();
        Assert.assertEquals(cp.countItemsInCart(), 1, "购物车商品数量不正确");
        //10. Click 'Signup / Login' button and submit login details
        RegisterLoginPage rlp = cp.nav.clickLogin();
        rlp.login(TestData.EMAIL,TestData.PASSWORD);
        //11. Again, go to Cart page
        cp = hp.navigateToCart();
        //12. Verify that those products are visible in cart after login as well
        Assert.assertEquals(cp.countItemsInCart(), 1, "购物车商品数量不正确");
    }
}
