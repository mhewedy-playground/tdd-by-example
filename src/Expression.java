public interface Expression {

    Money reduce(String currency);
}

class Sum implements Expression {
    Money augend, addend;

    Sum(Money augend, Money addend) {
        this.augend = augend;
        this.addend = addend;
    }

    public Money reduce(String currency) {
        int amount = augend.amount + addend.amount;
        return new Money(amount, currency);
    }
}