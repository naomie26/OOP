package hw1;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
/**
 * A wallet can contain a number of coins. There could be several coins of the same value, 
 * but the same coin cannot appear in the wallet twice
 */
public class Wallet {
	private List<Coin> coins;
    /**
     * @effects Creates a new empty wallet
     */
    public Wallet() {
    	this.coins = new ArrayList<>();
    }


    /**
     * @modifies this
     * @effects Adds a coin to the wallet
     * @return true if the coin was successfully added to the wallet;
     * 		   false otherwise
     */
    public boolean addCoin(Coin coin) {
    	if( this.coins.contains(coin)) {
    		return false;
    	}
    	this.coins.add(coin);
    	return true;
    }

    /**
	 * @requires sum > 0
     * @modifies this
     * @effects tries to match at least the sum "sum" with coins in the wallet. 
	 *			If transaction is possible, removes the paid coins from the wallet; else; changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double pay(double sum) {
    	if( this.getWalletTotal() < sum) {
    		return 0;
    	}
    	double rest = sum;
    	for ( Coin coin : this.coins) {
    		rest -= coin.getValue();
    		this.coins.remove(coin);
    		if(rest <= 0) {
    			return sum - rest;
    		}
    	}
    	return sum - rest;
    }


    /**
     * @return the current total value of coins in the wallet
     */
    public double getWalletTotal() {
    	double total = 0;
    	for ( Coin coin : this.coins) {
    		total += coin.getValue();
    	}
    	return total;
    }


    /**
     * @return the number of coins in the wallet
     */
    public int getWalletSize() {
    	return this.coins.size();
    }


    /**
     * @modifies this
     * @effects Empties the the wallet. After this method is called,
	 * 			both getWalletSize() and getWalletTotal() will return 0
	 * 			if called
     */
    public void emptyWallet() {
    	this.coins.clear();
    }


    /**
     * @return true if this wallet contains a coin with value "value"
     *  	   false otherwise
     */
    public boolean containsCoin(double value) {
    	for ( Coin coin : this.coins ) {
    		if( coin.getValue() == value ) {
    			return true;
    		}
    	}
    	return false;
    }
	
	
	/**
     * @return true if this wallet contains an ammount of money with value "value"
     *  	   false otherwise
     */
    public boolean containsAmmount(double value) {
    	return true;
    }

    /**
	 * @requires sum > 0
     * @modifies this
     * @effects tries to match at least the sum "sum" with the minimum number of coins available from the wallet.
     * If transaction is possible, removes the paid coins from the wallet; else; changes nothing
	 * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double payMinimum(double sum) {
    	List<Coin> sortedList = new ArrayList<>();
    	sortedList.addAll(this.coins);
    	sortedList.sort(Comparator.comparing(Coin::getValue).reversed());
    	double rest = sum;
    	for ( Coin coin : sortedList ) {
    		rest -= coin.getValue();
    		this.coins.remove(coin);
    		if( rest <= 0 ) {
    			return sum - rest;
    		}
    	}
    	return sum - rest;
    }
    
    /**
	 * @requires sum > 0
     * @modifies this
     * @effects tries to match the exact sum "sum" with the maximum number of coins available from the wallet.
     * If transaction is possible, removes the paid coins from the wallet; else; changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double payExactMaximum(double sum) {
    	List<Coin> sortedList = new ArrayList<>();
    	sortedList.addAll(this.coins);
    	sortedList.sort(Comparator.comparing(Coin::getValue));
   
    	
    	return sum ;
    }
}
