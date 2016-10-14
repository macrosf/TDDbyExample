package testdriven.byexample;

public class Bank {

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
		return source。reduce(this, to);	//精髓就在这句话了
	}

}
