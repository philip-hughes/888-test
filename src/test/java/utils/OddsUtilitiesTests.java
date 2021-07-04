package utils;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;;

public final class OddsUtilitiesTests {
	
	public static void isFractional(String odds) {
		
		int slashOccurance = StringUtils.countMatches(odds, "/");
		
		if(slashOccurance == 1) {
			String [] oddsArray = odds.split("/");
			Assert.assertTrue(oddsArray.length == 2);
			Assert.assertTrue(StringUtils.isNumeric(oddsArray[0]));
			Assert.assertTrue(StringUtils.isNumeric(oddsArray[1]));
			
		}
		else {
			Assert.fail("Displayed odds are not in expected fractional format");
		}
		
	}
	
	public static float convertToDecimal(String odds) {
		String [] oddsArray = odds.split("/");
		int numerator = Integer.parseInt(oddsArray[0]);
		int denominator = Integer.parseInt(oddsArray[1]);
		float decimalOdds = ((float)numerator / denominator) + 1;
		
		System.out.println("Numerator: " + numerator + " Denominator: " + denominator);
		
		
		return decimalOdds;
		
	} 

}
