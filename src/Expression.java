public interface Expression {

    Money reduce(Bank bank, String currency);

    Expression times(int multiplier);

    default Expression plus(Expression addend) {
        return new Sum(this, addend);
    }
}

class Sum implements Expression {
    Expression augend, addend;

    Sum(Expression augend, Expression addend) {
        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public Money reduce(Bank bank, String currency) {
        Money augendReduced = augend.reduce(bank, currency);
        Money addendReduced = addend.reduce(bank, currency);
        return new Money(augendReduced.amount + addendReduced.amount, currency);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(this.augend.times(multiplier), this.addend.times(multiplier));
    }
}