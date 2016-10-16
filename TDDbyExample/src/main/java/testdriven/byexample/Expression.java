package testdriven.byexample;

public interface Expression {
	//Money reduce(String to);
	Money reduce(Bank bank, String to);
}
