package testdriven.byexample;

import java.util.Hashtable;

public class Bank {

	private Hashtable<Pair, Integer> rates 
		= new Hashtable<Pair, Integer>();
	
	public Money reduce(Expression source, String to) {
		//--v1--
		//return Money.dollar(10);

		//--v2--
		//Sum sum = (Sum)source;
		//int amount = sum.augend.amount + sum.addend.amount;
		//return new Money(amount, to);
		
		//--v3--
		//Sum sum = (Sum)source;
		//return sum.reduce(to);
		
		//--v4--
		//if (source instanceof Money) return (Money) source;
		//Sum sum = (Sum) source;
		//return sum.reduce(to);
		
		//--v5--
		//return source.reduce(to);
		
		//--v6--
		return source.reduce(this, to);	//精髓就在这句话了
	}

//	int rate(String from, String to) {
//		return (from.equals("CHF") && to.equals("USD"))
//				? 2:1;
//	}
	
	//p70
	void addRate(String from, String to, int rate) {
		rates.put(new Pair(from, to), rate);
	}
	
	int rate(String from, String to){
		if (from.equals(to)) return 1;
		return rates.get(new Pair(from, to));
	}
}
