package it.salestaxes.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TaxCalculator {


	public static BigDecimal roundUp(BigDecimal value,BigDecimal places)
	{

		if (places.signum() == -1) throw new IllegalArgumentException();

		//	    BigDecimal bd = new BigDecimal(value);
		//	    bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
		//	    return bd.doubleValue();

		BigDecimal divided = value.divide(places, 0, RoundingMode.HALF_UP);
		BigDecimal result = divided.multiply(places);
		return result;

		//		return (int)(value / 0.05 + 0.5) * 0.05;

		//	    return Math.round(value * 20.0) / 20.0;
	}

	public static BigDecimal truncate(BigDecimal value)
	{
		DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
		String v = df.format(value);
		return new BigDecimal(v);
	}
}
