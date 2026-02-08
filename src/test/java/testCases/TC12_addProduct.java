package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import testBase.BaseClass;

public class TC12_addProduct extends BaseClass {

    @Test
    public void testAddProductIntoCart(){
        initializeHomePage();
        HomePage hp = new HomePage(getDriver());
        //4. Click 'Products' button
        ProductsPage pp = hp.clickNavigateToProductPage();
        //5. Hover over first product and click 'Add to cart'
        pp.addProductToCartByIndex(1);
        //6. Click 'Continue Shopping' button
        Assert.assertTrue(pp.isButtonContinueShoppingExist());
        pp.clickContinueShopping();
        //7. Hover over second product and click 'Add to cart'
        pp.addProductToCartByIndex(2);
        //8. Click 'View Cart' button
        CartPage cp = pp.navigateToCart();
        //9. Verify both products are added to Cart
        Assert.assertEquals(cp.countItemsInCart(), 2, "购物车商品数量不正确");
        //10. Verify their prices, quantity and total price
        // 校验第一个商品
        Assert.assertEquals(cp.getItemPrice(1), "Rs. 500");
        Assert.assertEquals(cp.getItemQuantity(1), "1");
        Assert.assertEquals(cp.getItemTotalPrice(1), "Rs. 500");

// 校验第二个商品
        Assert.assertEquals(cp.getItemPrice(2), "Rs. 400");
        Assert.assertEquals(cp.getItemQuantity(2), "1");
        Assert.assertEquals(cp.getItemTotalPrice(2), "Rs. 400");
    }
}
