package it.salestaxes.shop;

import it.salestaxes.items.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {

	
	private List<Item> cart;

	public ShoppingCart(){
		cart = new ArrayList<Item>();
	}


	public void addItem(int quantity, Item item){
		if (cart != null){
			for (int i = 0; i < quantity; i++) {
				cart.add(item);
			}
		}
	}

	public List<Item> getCart()
	{
		return cart;
	}

	public int getQuantityOfItemInCart(Item item){
		return Collections.frequency(cart, item);
	}
	public HashMap<Item, Integer> groupItemByQuantity(){
		HashMap<Item, Integer> map = new HashMap<Item, Integer>();
		for (Item it : cart) {
			if(!map.containsKey(it))
				map.put(it, Collections.frequency(cart, it));
		}
		return map;
	}
	
	public void emptyCart(){
		this.cart.clear();
	}

}
