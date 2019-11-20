package hw1;

public class CoinCollectionTest {

	public static void main(String[] args) {
		Coin coin1 = new Coin(0.05);
		Coin coin2 = new Coin(0.5);
		Coin coin3 = new Coin(1);
		Coin coin4 = new Coin(5);
		Coin coin5 = new Coin(10);
		Coin coin6 = new Coin(10);
		
		CoinCollection collection = new CoinCollection();
		assert( collection.addCoin(coin1) == true );
		assert( collection.addCoin(coin2) == true );
		assert( collection.addCoin(coin3) == true );
		assert( collection.addCoin(coin4) == true );
		assert( collection.addCoin(coin5) == true );
		assert( collection.addCoin(coin6) == false );
		assert( collection.addCoin(coin1) == false );
		assert( collection.getCollectionTotal() == 16.55 );
		assert( collection.getCollectionSize() == 5 );
		collection.emptyCollection();
		assert( collection.getCollectionSize() == 0 );
		assert( collection.getCollectionTotal() == 0);
		assert( collection.addCoin(coin1) == true );
	}

}
