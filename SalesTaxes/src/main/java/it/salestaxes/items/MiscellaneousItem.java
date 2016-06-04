package it.salestaxes.items;

import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MiscellaneousItem extends Item{

	@Override
	public BigDecimal getBasicTaxPercent() {
		return new BigDecimal("10.0");
	}
}
