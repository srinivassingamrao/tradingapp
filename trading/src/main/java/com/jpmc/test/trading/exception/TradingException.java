package com.jpmc.test.trading.exception;

public class TradingException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2909437380608419141L;

	/**
	 * 
	 * @param message
	 */
	public TradingException(String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public TradingException(Throwable cause) {
        super(cause);
    }

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public TradingException(String message, Throwable cause) {
        super(message, cause);
    }
}
