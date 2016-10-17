package testdriven.byexample;

public class Money implements Expression{

	protected int amount;
	protected String currency;
	
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Expression times(int multiplier){
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

	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}
	
	//public Money reduce(String to){
	//	return this;
	//}

	public Money reduce(Bank bank, String to) {
		//int rate = (currency.equals("CHF")&&to.equals("USD"))
		//		? 2:1;
		int rate = bank.rate(currency, to);
		return new Money(amount/rate, to);
	}
	
	//p73
	Expression tims(int multiplier) {
		return new Money(amount*multiplier, currency);
	}
}
