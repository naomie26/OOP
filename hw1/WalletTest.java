package hw1;

public class WalletTest {
	public static void main(String[] args) {
		Coin coin1 = new Coin(0.05);
		Coin coin2 = new Coin(0.5);
		Coin coin3 = new Coin(1);
		Coin coin4 = new Coin(5);
		Coin coin5 = new Coin(10);
		
		Wallet wallet = new Wallet();
		assert( wallet.addCoin(coin1) == true );
		assert( wallet.addCoin(coin2) == true );
		assert( wallet.addCoin(coin3) == true );
		assert( wallet.addCoin(coin3) == false );
		assert( wallet.containsCoin(coin1.getValue()) == true);
		assert( wallet.containsCoin(coin4.getValue()) == false);
		assert( wallet.getWalletSize() == 3);
		assert( wallet.getWalletTotal() == (coin1.getValue()+coin2.getValue()+coin3.getValue()) );
		assert( wallet.pay(0.5) != 0 );
		wallet.addCoin(coin5);
		assert( wallet.containsAmmount(15) == true);
		wallet.emptyWallet();
		assert( wallet.getWalletSize() == 0);
		assert( wallet.getWalletTotal() == 0 );
		assert( wallet.containsAmmount(0) == true);
		
	}
}
