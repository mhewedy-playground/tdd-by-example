
public class MoneyTest extends BaseTest {

    public static void main(String[] args) {
        new MoneyTest().run();
    }

    @Test
    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    // $5 + $5 = $10
    @Test
    public void testSimpleAddition() {
        Money dollarSum = Money.dollar(5).plus(Money.dollar(5));
        assertEquals(Money.dollar(10), dollarSum);

        // triangulation
        Money francSum = Money.franc(5).plus(Money.franc(5));
        assertEquals(Money.franc(10), francSum);
    }

}