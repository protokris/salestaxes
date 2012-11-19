package kdr.resume.salestaxes.item;

import kdr.resume.salestaxes.money.Money;

public class MockItem implements Item {

	private String name = null;
	private Money price = null;
	private Money taxAmount = null;
	private Money priceTaxed = null;
	
	public MockItem(String name, Money price, Money taxAmount, Money priceTaxed) {
		this.name = name;
		this.price = price;
		this.taxAmount = taxAmount;
		this.priceTaxed = priceTaxed;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Money getPrice() {
		return price;
	}

	@Override
	public Money getTaxAmount() {
		return taxAmount;
	}

	@Override
	public Money getPriceTaxed() {
		return priceTaxed;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
