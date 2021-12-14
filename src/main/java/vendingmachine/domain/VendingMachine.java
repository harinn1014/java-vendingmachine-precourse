package vendingmachine.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import static vendingmachine.utils.Constant.ZERO;

public class VendingMachine {
	private static final HashMap<Integer, Integer> coins = new HashMap<>();
	private static final List<Product> products = new ArrayList<>();
	private final int changeAmount;

	public VendingMachine(int amount) {
		changeAmount = amount;
		makeCoins();
	}
	
	public void initProducts() {
		products.clear();
	}
	
	public int getChange() {
		return changeAmount;
	}
	
	public HashMap<Integer, Integer> getCoins(){
		return coins;
	}
	
	public List<Product> getProducts(){
		return products;
	}

	/**** action ****/
	public void addProduct(Product item) {
		products.add(item);
	}

	public void makeCoins() {
		int sumAmount = 0;
		while (sumAmount < changeAmount) {
			int coin = Randoms.pickNumberInList(coinFilter(sumAmount));
			sumAmount += coin;
			coins.put(coin, coins.getOrDefault(coin, ZERO) + 1);
		}
	}
	
	public List<Integer> coinFilter(int sumAmount) {
		return Coin.getAmountList()
				.stream()
				.filter(amount -> amount <= (changeAmount-sumAmount))
				.collect(Collectors.toList());
	}

}
