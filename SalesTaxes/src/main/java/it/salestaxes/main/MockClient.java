package it.salestaxes.main;

import it.salestaxes.items.BookItem;
import it.salestaxes.items.FoodItem;
import it.salestaxes.items.MedicalItem;
import it.salestaxes.items.MiscellaneousItem;
import it.salestaxes.shop.Shop;
import it.salestaxes.shop.ShoppingCart;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockClient {

	@Autowired
	private Shop shop;
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
	
	 
	public void doFirstShopping (){
		chocolateBar.build("chocolate bar", false, new BigDecimal("0.85"));
		musicCD.build("music CD", false, new BigDecimal("14.99"));
		book.build("book", false, new BigDecimal("12.49"));
		shoppingCart.addItem(1, chocolateBar);
		shoppingCart.addItem(1, musicCD);
		shoppingCart.addItem(1, book);
		shop.checkout(shoppingCart);
		System.out.println(shop.printReceipt(shoppingCart));
	}
	public void doSecondShopping (){
		importedBoxOfChocolates.build("box of chocolates", true, new BigDecimal("10.00"));
		bottleOfPerfume.build("bottle of perfume", true, new BigDecimal("47.50"));
		shoppingCart.addItem(1, importedBoxOfChocolates);
		shoppingCart.addItem(1, bottleOfPerfume);
		shop.checkout(shoppingCart);
		System.out.println(shop.printReceipt(shoppingCart));
	}
	
	public void doThirdShopping (){
		importedBottleOfPerfume.build("bottle of perfume", true, new BigDecimal("27.99"));
		bottleOfPerfume.build("bottle of perfume", false, new BigDecimal("18.99"));
		headachePills.build("packet of headache pills", false, new BigDecimal("9.75"));
		importedBoxOfChocolates.build("box of chocolates", true, new BigDecimal("11.25"));
		shoppingCart.addItem(1, importedBottleOfPerfume);
		shoppingCart.addItem(1, bottleOfPerfume);
		shoppingCart.addItem(1, headachePills);
		shoppingCart.addItem(1, importedBoxOfChocolates);
		shop.checkout(shoppingCart);
		System.out.println(shop.printReceipt(shoppingCart));
	}


	public Shop getShop() {
		return shop;
	}


	public void setShop(Shop shop) {
		this.shop = shop;
	}


	public BookItem getBook() {
		return book;
	}


	public void setBook(BookItem book) {
		this.book = book;
	}


	public MiscellaneousItem getMusicCD() {
		return musicCD;
	}


	public void setMusicCD(MiscellaneousItem musicCD) {
		this.musicCD = musicCD;
	}


	public MiscellaneousItem getBottleOfPerfume() {
		return bottleOfPerfume;
	}


	public void setBottleOfPerfume(MiscellaneousItem bottleOfPerfume) {
		this.bottleOfPerfume = bottleOfPerfume;
	}


	public MiscellaneousItem getImportedBottleOfPerfume() {
		return importedBottleOfPerfume;
	}


	public void setImportedBottleOfPerfume(MiscellaneousItem importedBottleOfPerfume) {
		this.importedBottleOfPerfume = importedBottleOfPerfume;
	}


	public MedicalItem getHeadachePills() {
		return headachePills;
	}


	public void setHeadachePills(MedicalItem headachePills) {
		this.headachePills = headachePills;
	}


	public FoodItem getChocolateBar() {
		return chocolateBar;
	}


	public void setChocolateBar(FoodItem chocolateBar) {
		this.chocolateBar = chocolateBar;
	}


	public FoodItem getImportedBoxOfChocolates() {
		return importedBoxOfChocolates;
	}


	public void setImportedBoxOfChocolates(FoodItem importedBoxOfChocolates) {
		this.importedBoxOfChocolates = importedBoxOfChocolates;
	}


	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}


	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	
	
}
