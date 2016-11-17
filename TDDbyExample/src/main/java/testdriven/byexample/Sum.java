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
	
	//page 151 (chinese version)
//	@Override
//	public String toString() {
//		return augend + " + " + addend;
//	}
	
	@Override
	public String toString() {
		IndentingWriter writer = new IndentingWriter();
		toString(writer);
		return writer.contents();
	}
	
	public void toString(IndentingWriter writer){
		writer.println("+");
		writer.indent();
		augend.toString(writer);
		writer.println();
		addend.toString(writer);
		writer.exdent();
	}
}
