package testdriven.byexample;

public class Sum implements Expression {
	Expression augend;
	Expression addend;
	
	Sum(Expression augend, Expression addend) {
		this.augend = augend;
		this.addend = addend;
	}

	public Money reduce(Bank bank, String to) {
		int amount = bank.reduce(augend, to).amount + bank.reduce(addend, to).amount;
		return new Money(amount, to);
	}

	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}

	public Expression times(int multiplier) {
		return new Sum(augend.times(multiplier), addend.times(multiplier));
	}
}
