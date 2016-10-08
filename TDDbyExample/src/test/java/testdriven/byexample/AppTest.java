package testdriven.byexample;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testMultiplication() {
    	//Dollar five = new Dollar(5);
    	Money five = Money.dollar(5);
    	assertEquals(Money.dollar(10), five.times(2));
    	assertEquals(Money.dollar(15), five.times(3));
    }
    
//    public void testFrancMultiplication(){
//    	Money five = Money.franc(5);
//    	assertEquals(Money.franc(10), five.times(2));
//    	assertEquals(Money.franc(15), five.times(3));    	
//    }
    
    public void testEquality() {
    	assertTrue(Money.dollar(5).equals(Money.dollar(5)));
    	assertFalse(Money.dollar(5).equals(Money.dollar(6)));
    	assertTrue(Money.franc(5).equals(Money.franc(5)));
//    	assertFalse(Money.franc(5).equals(Money.franc(6)));
//    	assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }
    
    //page 61
    public void testSimpleAddition() {
    	Money five = Money.dollar(5);
    	Expression sum = five.plus(five);
    	Bank bank = new Bank();
    	Money reduced = bank.reduce(sum, "USD");
    	assertEquals(Money.dollar(10), reduced);
    }    
    
    public void testPlusReturnSum() {
    	Money five = Money.dollar(5);
    	Expression result = five.plus(five);
    	Sum sum = (Sum) result;
    	assertEquals(five, sum.augend);
    	assertEquals(five, sum.addend);
    }
    
    public void testReduceSum() {
    	Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
    	Bank bank = new Bank();
    	Money reduced = bank.reduce(sum, "USD");
    	assertEquals(reduced, Money.dollar(7));
    }
}
