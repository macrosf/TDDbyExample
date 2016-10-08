package testdriven.byexample;

public class Money {

	protected int amount;
	protected String currency;
	
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	protected Money times(int multiplier){
		return new Money(amount * multiplier, currency);
	};

	public String currency() {
		return currency;
	}
	
	public static Money dollar(int amount) {
		//return new Dollar(amount);
		return new Money(amount, "USD");
	}
	
	public static Money franc(int amount) {
		//return new Franc(amount);
		return new Money(amount, "CHF");
	}
	
	@Override
	public boolean equals(Object object) {
		Money money = (Money) object;
		return amount == money.amount
				&& currency.equals(money.currency);
	}

}
