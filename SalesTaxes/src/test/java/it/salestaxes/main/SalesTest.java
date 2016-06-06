package it.salestaxes.main;

import java.math.BigDecimal;

import it.salestaxes.helper.TaxCalculator;
import it.salestaxes.items.BookItem;
import it.salestaxes.items.FoodItem;
import it.salestaxes.items.MedicalItem;
import it.salestaxes.items.MiscellaneousItem;
import it.salestaxes.shop.Shop;
import it.salestaxes.shop.ShoppingCart;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context-tst.xml")
public class SalesTest {

	@Autowired
	private BookItem book;
	@Autowired
	private MiscellaneousItem miscellaneousItem;
	@Autowired
	private MiscellaneousItem musicCD;
	@Autowired
	private MedicalItem headachePills;
	@Autowired
	private FoodItem chocolateBar;
	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private Shop shop;

	@Test
	public void checkItemQuantity(){
		shoppingCart.addItem(1, headachePills);
		Assert.assertEquals(1, shoppingCart.getQuantityOfItemInCart(headachePills));	}
	@Test
	public void defaultPriceForGenericItemIsZero(){
		Assert.assertEquals(0.0, miscellaneousItem.getNetPrice().doubleValue(),0);	}

	@Test
	public void defaultNameForGenericItemIsBlank(){
		Assert.assertEquals("", miscellaneousItem.getName());	}

	@Test
	public void taxForGenericItemIsTen(){
		Assert.assertEquals(new BigDecimal("10.0"), miscellaneousItem.getBasicTaxPercent());	}

	@Test
	public void BooksAreExemptFromBasictaxes(){
		Assert.assertEquals(new BigDecimal("0.0"), book.getBasicTaxPercent());
	}
	@Test
	public void FoodIsExemptFromBasictaxes(){
		Assert.assertEquals(new BigDecimal("0.0"), chocolateBar.getBasicTaxPercent());
	}
	@Test
	public void MedicalProductsAreExemptFromBasictaxes(){
		Assert.assertEquals(new BigDecimal("0.0"), book.getBasicTaxPercent());
	}

	@Test
	public void taxesAreRoundedUp(){
		BigDecimal i = TaxCalculator.roundUp(new BigDecimal("12.649"),new BigDecimal("0.05"));
		BigDecimal i2 = TaxCalculator.roundUp(new BigDecimal("12.622"),new BigDecimal("0.05"));
		Assert.assertEquals(new BigDecimal("12.65"), i);
		Assert.assertEquals(new BigDecimal("12.60"), i2);
	}
	
	@Test(expected = RuntimeException.class)
	public void roundingGiveException(){
		TaxCalculator.roundUp(new BigDecimal("12"),new BigDecimal("-1"));
	}
	
	@Test
	public void checkTaxesAmountForListOfItem(){
		chocolateBar.build("chocolate bar", false, new BigDecimal("0.85"));
		musicCD.build("music CD", false, new BigDecimal("14.99"));
		book.build("book", false, new BigDecimal("12.49"));
		shoppingCart.addItem(1, chocolateBar);
		shoppingCart.addItem(1, musicCD);
		shoppingCart.addItem(1, book);
		shop.checkout(shoppingCart);
		Assert.assertEquals(new BigDecimal("1.50"), shop.getTotalTaxes());
	}


}
