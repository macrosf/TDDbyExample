package testdriven.byexample;

@Deprecated
public class Franc extends Money{
	//private int amount;
	Franc(int amount) {
		super(amount, "CHF");
		//this.amount = amount;
		//currency = "CHF";
	}

//	protected Money times(int multiplier) {
//		//return Money.franc(amount * multiplier);
//		return new Money(amount * multiplier, currency);
//	}
	
//	Franc times(int multiplier) {
//		return new Franc(amount * multiplier);
//	}
//	
//	@Override
//	public boolean equals(Object object){
//		return amount == ((Franc)object).amount;
//	}
}
