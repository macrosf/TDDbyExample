package testdriven.byexample;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	//page 139(chinese version), not work
//	public static void main(String[] args){
//		junit.swingui.TestRunner.run(AppTest.class);
//	}
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
    
    public void testReduceMoney() {
    	Bank bank = new Bank();
    	Money result = bank.reduce(Money.dollar(1), "USD");
    	assertEquals(Money.dollar(1), result);
    }
    
//    public void testReduceDifferentCurrency() {
//    	Bank bank = new Bank();
//    	bank.addRate("CHF", "USD", 2);
//    	Money result = bank.reduce(Money.franc(2), "USD");
//    	assertEquals(Money.dollar(1), result);
//    }
    
    //P69 assert fail
//    public void testArrayEquals() {
//    	assertEquals(new Object[] {"abc"}, new Object[] {"abc"});
//    }
    
    //difference between 
    //hashmap(non-nullable, thread safe) 
    //& hashtable(nullable(key/value), not thread safe)
    public void testHashtableAndHashmap(){
    	Map<String, String> hashtable = new Hashtable<String, String>();
    	//hashtable.put(null, "value:A");	//failed (NullpointerException)
    	//hashtable.put("key:A", null);		//failed (NullpointerException)
    	hashtable.put("key:B", "value:B");
    	assertEquals(hashtable.get("key:B"), "value:B");
    	
    	Map<String, String> hashmap = new HashMap<String, String>();
    	hashmap.put(null, "value:A");	
    	hashmap.put("key:A", null);		
    	hashmap.put("key:B", "value:B");
    	assertEquals(hashmap.get(null), "value:A");
    	assertEquals(hashmap.get("key:A"), null);
    	assertEquals(hashmap.get("key:B"), "value:B");
    }
    
    public void testIdentityRate() {
    	assertEquals(1, new Bank().rate("USD", "USD"));
    }
    
    //p72
    public void testMixedAddition(){
    	Money fivebucks = Money.dollar(5);
    	Money tenFrancs = Money.franc(10);
    	Bank bank = new Bank();
    	bank.addRate("CHF", "USD", 2);
    	Money result = bank.reduce(fivebucks.plus(tenFrancs), "USD");
    	assertEquals(Money.dollar(10), result);
    }
    
    //p76
    public void testSumPlusMoney(){
    	Expression fiveBucks = Money.dollar(5);
    	Expression tenFrancs = Money.franc(10);
    	Bank bank = new Bank();
    	bank.addRate("CHF", "USD", 2);
    	Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
    	Money result = bank.reduce(sum, "USD");
    	assertEquals(Money.dollar(15), result);
    }
    
    //p77
    public void testSumTimes() {
    	Expression fiveBucks = Money.dollar(5);
    	Expression tenFrancs = Money.franc(10);
    	Bank bank = new Bank();
    	bank.addRate("CHF", "USD", 2);
    	Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
    	Money result = bank.reduce(sum, "USD");
    	assertEquals(Money.dollar(2), result);
    }
    
    //p78
    public void testPlusSameCurrencyReturnsMoney(){
    	Expression sum = Money.dollar(1).plus(Money.dollar(1));
    	assertTrue(sum instanceof Money);
    }
    
    //p131 One to Many
    public void testSum(){
    	assertEquals("these values should be equal.", 
    			7, sum(new int[]{3, 4}));
    }

	private int sum(int[] values) {
		int sum = 0;
		for (int v: values){
			sum+=v;
		}
		return sum;
	}
}
