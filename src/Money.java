public class Money implements Expression {

    int amount;
    String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public String currency() {
        return this.currency;
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(this.amount * multiplier, this.currency);
    }

    @Override
    public Money reduce(Bank bank, String currency) {
        int rate = bank.rate(this.currency, currency);
        return new Money(this.amount / rate, currency);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Money)) {
            return false;
        }
        Money money = (Money) object;
        return amount == money.amount && currency().equals(money.currency());
    }

    @Override
    public String toString() {
        return this.amount + " " + this.currency;
    }
}