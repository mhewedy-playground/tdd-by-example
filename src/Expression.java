public interface Expression {

    Money reduce(Bank bank, String currency);
}

class Sum implements Expression {
    Money augend, addend;

    Sum(Money augend, Money addend) {
        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public Money reduce(Bank bank, String currency) {
        Money augendReduced = augend.reduce(bank, currency);
        Money addendReduced = addend.reduce(bank, currency);
        return new Money(augendReduced.amount + addendReduced.amount, currency);
    }
}