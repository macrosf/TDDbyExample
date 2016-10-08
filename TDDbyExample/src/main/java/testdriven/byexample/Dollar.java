package testdriven.byexample;

@Deprecated
public class Dollar extends Money{
	Dollar(int amount) {
		super(amount, "USD");
		//currency = "USD";
		//this.amount = amount;
	}

//	protected Money times(int multiplier) {
//		//return Money.dollar(amount * multiplier);
//		return new Money(amount * multiplier, currency);
//	}
}
