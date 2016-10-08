package testdriven.byexample;

public class Bank {

	public Money reduce(Expression source, String to) {
		//return Money.dollar(10);
		Sum sum = (Sum)source;
		int amount = sum.augend.amount + sum.addend.amount;
		return new Money(amount, to);
	}

}
