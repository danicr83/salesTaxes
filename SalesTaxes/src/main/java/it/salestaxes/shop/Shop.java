package it.salestaxes.shop;

import it.salestaxes.helper.TaxCalculator;
import it.salestaxes.items.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

@Service
public class Shop {
	
	private BigDecimal total;
	private BigDecimal totalTaxes;
	
	public Shop (){
		total = new BigDecimal(0.0);
		totalTaxes = new BigDecimal(0.0);
	}
	

	public void checkout(ShoppingCart shoppingCart){
		List<Item> cart = shoppingCart.getCart();
		for (Item item : cart) {
			BigDecimal itemNetPrice = item.getNetPrice();
			BigDecimal itemTaxes = itemNetPrice.multiply(item.getBasicTaxPercent().divide(new BigDecimal("100")));
			itemTaxes= TaxCalculator.roundUp(itemTaxes,new BigDecimal("0.05"));
			if(item.isImported())
			{
				itemTaxes = itemTaxes.add(itemNetPrice.multiply(new BigDecimal("0.05")));
				itemTaxes= TaxCalculator.roundUp(itemTaxes,new BigDecimal("0.05"));
			}
			itemTaxes= TaxCalculator.roundUp(itemTaxes,new BigDecimal("0.05"));
			
			item.setGrossPrice(itemTaxes.add(itemNetPrice));

			totalTaxes= totalTaxes.add(itemTaxes);
			
			total= total.add(item.getGrossPrice());

		}
	}

	public BigDecimal getTotal() {
		return total;
	}
	public BigDecimal getTotalTaxes() {
		return totalTaxes;
	}
	
	public String printReceipt (ShoppingCart shoppingCart){
			String itemsInCart = "";
			HashMap<Item, Integer> itemMap = shoppingCart.groupItemByQuantity();
			for (Entry<Item, Integer> entry : itemMap.entrySet()) {
				int quantity = entry.getValue() ;
				itemsInCart+= quantity + " " +entry.getKey() + " : " + (entry.getKey().getGrossPrice().multiply(new BigDecimal(quantity)));
				itemsInCart+="\n";
			}
			itemsInCart+="Sales Taxes: "+totalTaxes;
			itemsInCart+="\n";
			itemsInCart+="Total: "+total;
			itemsInCart+="\n";

			shoppingCart.emptyCart();
			cashClose ();
			
			return itemsInCart;
		}
	
	public void cashClose (){

		total = new BigDecimal ("0.0");
		totalTaxes = new BigDecimal ("0.0");
	}
}
