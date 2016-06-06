package it.salestaxes.items;

import java.math.BigDecimal;

public abstract class Item {
	
	private String name;
	private boolean imported;
	private BigDecimal netPrice;
	private BigDecimal grossPrice;
	
	public Item()
	{
		this.name = "";
		this.imported = false;
		this.netPrice = new BigDecimal("0.0");
		this.grossPrice = new BigDecimal("0.0");
	}
	
	public Item(String name, int quantity, boolean imported, BigDecimal netPrice)
	{
		build(name, imported, netPrice);
	}
	
	public void build(String name, boolean imported, BigDecimal netPrice)
	{
		this.name = name;
		this.netPrice = netPrice;
		this.imported = imported;
		this.grossPrice = netPrice;
	}
	
	public abstract BigDecimal getBasicTaxPercent();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public BigDecimal getGrossPrice() {
		return grossPrice;
	}

	public void setGrossPrice(BigDecimal grossPrice) {
		this.grossPrice = grossPrice;
	}

	public boolean isImported() {
		return imported;
	}
	
	@Override
	public String toString()
	{
		return (importedToString(imported) + name);
	}
	
	public String importedToString(boolean imported)
	{
		if(imported == false)
		{
			return "";
		}
		
		else
		{
			return "imported ";
		}
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (netPrice.compareTo(other.netPrice) != 0)
			return false;
		if (imported != other.imported)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
