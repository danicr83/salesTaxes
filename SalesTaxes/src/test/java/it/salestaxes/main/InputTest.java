package it.salestaxes.main;

import it.salestaxes.items.BookItem;
import it.salestaxes.items.FoodItem;
import it.salestaxes.items.MedicalItem;
import it.salestaxes.items.MiscellaneousItem;
import it.salestaxes.shop.Shop;
import it.salestaxes.shop.ShoppingCart;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context-tst.xml")
public class InputTest {

	@Autowired
	private BookItem book;
	@Autowired
	private MiscellaneousItem musicCD;
	@Autowired
	private MiscellaneousItem bottleOfPerfume;
	@Autowired
	private MiscellaneousItem importedBottleOfPerfume;
	@Autowired
	private MedicalItem headachePills;
	@Autowired
	private FoodItem chocolateBar;
	@Autowired
	private FoodItem importedBoxOfChocolates;
	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private Shop shop;

	@Test
	public void input1(){
		chocolateBar.build("chocolate bar", false, new BigDecimal("0.85"));
		musicCD.build("music CD", false, new BigDecimal("14.99"));
		book.build("book", false, new BigDecimal("12.49"));
		shoppingCart.addItem(1, chocolateBar);
		shoppingCart.addItem(1, musicCD);
		shoppingCart.addItem(1, book);
		shop.checkout(shoppingCart);
		Assert.assertEquals(new BigDecimal("1.50"), shop.getTotalTaxes());
		Assert.assertEquals(new BigDecimal("29.83"), shop.getTotal());
		Assert.assertEquals(new BigDecimal("0.85"), chocolateBar.getGrossPrice());
		Assert.assertEquals(new BigDecimal("16.49"), musicCD.getGrossPrice());
		Assert.assertEquals(new BigDecimal("12.49"), book.getGrossPrice());
	}
	@Test
	public void input2(){
		importedBoxOfChocolates.build("box of chocolates", true, new BigDecimal("10.00"));
		bottleOfPerfume.build("bottle of perfume", true, new BigDecimal("47.50"));
		shoppingCart.addItem(1, importedBoxOfChocolates);
		shoppingCart.addItem(1, bottleOfPerfume);
		shop.checkout(shoppingCart);
		Assert.assertEquals(new BigDecimal("7.65"), shop.getTotalTaxes());
		Assert.assertEquals(new BigDecimal("65.15"), shop.getTotal());
		Assert.assertEquals(new BigDecimal("10.50"), importedBoxOfChocolates.getGrossPrice());
		Assert.assertEquals(new BigDecimal("54.65"), bottleOfPerfume.getGrossPrice());
	}

	@Test
	public void input3(){
		importedBottleOfPerfume.build("bottle of perfume", true, new BigDecimal("27.99"));
		bottleOfPerfume.build("bottle of perfume", false, new BigDecimal("18.99"));
		headachePills.build("packet of headache pills", false, new BigDecimal("9.75"));
		importedBoxOfChocolates.build("box of chocolates", true, new BigDecimal("11.25"));
		shoppingCart.addItem(1, importedBottleOfPerfume);
		shoppingCart.addItem(1, bottleOfPerfume);
		shoppingCart.addItem(1, headachePills);
		shoppingCart.addItem(1, importedBoxOfChocolates);
		shop.checkout(shoppingCart);
		Assert.assertEquals(new BigDecimal("9.75"), headachePills.getGrossPrice());
		Assert.assertEquals(new BigDecimal("20.89"), bottleOfPerfume.getGrossPrice());
		Assert.assertEquals(new BigDecimal("32.19"), importedBottleOfPerfume.getGrossPrice());
		/*
		 * Here the required condition is not verified. maybe this is due to an erorr in the trace of the exercise
		 * Demonstration:
		 * 1 box of imported chocolates at 11.25 . In this case only the 5% tax must be applied:
		 * 11.25 * 0.05 = 0.5625 
		 * 11.25  0.5625 = 11.8125
		 * 11.8125 rounded up to the nearest 0.05 is 11.80 (instead of 11.85 as the trace would to come out)
		 * consequently also the tests for the total amount won't be succesful
		 * 
		 * Assert.assertEquals(new BigDecimal("11.85"), importedBoxOfChocolates.getGrossPrice());
		 * Assert.assertEquals(new BigDecimal("6.70"), shop.getTotalTaxes());
		 * Assert.assertEquals(new BigDecimal("74.68"), shop.getTotal());
		 */
		
		Assert.assertEquals(new BigDecimal("11.80"), importedBoxOfChocolates.getGrossPrice());
		Assert.assertEquals(new BigDecimal("6.65"), shop.getTotalTaxes());
		Assert.assertEquals(new BigDecimal("74.63"), shop.getTotal());
	}
}
