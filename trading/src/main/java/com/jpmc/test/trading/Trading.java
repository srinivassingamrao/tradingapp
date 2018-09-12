package com.jpmc.test.trading;

import com.jpmc.test.trading.controller.TradingController;
import com.jpmc.test.trading.exception.TradingException;


public class Trading {
    public static void main( String[] args ) {
        TradingController tController = new TradingController();
        try {
			tController.placeTransaction("foo", "B", "SGP", "1 Sep 2018", 200, 100.25);
			tController.placeTransaction("foo", "B", "SGP", "3 Sep 2018", 100, 100.25);
			tController.placeTransaction("bar", "B", "AED", "3 Sep 2018", 500, 150.25);
			tController.placeTransaction("foo", "S", "SGP", "1 Sep 2018", 200, 100.25);
			tController.placeTransaction("foo", "S", "SGP", "3 Sep 2018", 100, 100.25);
			tController.placeTransaction("bar", "S", "AED", "3 Sep 2018", 300, 150.25);
			
			tController.placeTransaction("foo", "S", "SGP", "4 Sep 2018", 200, 100.25);
			tController.placeTransaction("foo", "S", "SGP", "4 Sep 2018", 100, 100.25);
			tController.placeTransaction("bar", "S", "AED", "4 Sep 2018", 850, 150.25);
			tController.placeTransaction("foo", "B", "SGP", "4 Sep 2018", 200, 100.25);
			tController.placeTransaction("foo", "B", "SGP", "4 Sep 2018", 100, 100.25);
			tController.placeTransaction("bar", "B", "AED", "4 Sep 2018", 175, 150.25);
			tController.getDailyTransactionDetails();
		} catch (TradingException e) {
			e.printStackTrace();
		}
    }
}
