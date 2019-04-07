import java.util.HashMap;
import java.util.Map;

class Bank {

	private Map<Pair, Integer> currencyRates = new HashMap<>();

	public Money reduce(Expression source, String currency) {
		return source.reduce(this, currency);
	}

	public void addRate(String from, String to, int rate) {
		currencyRates.put(Pair.of(from, to), rate);
	}

	public int rate(String from, String to) {
		if (from.equals(to)) {
			return 1;
		}
		return currencyRates.get(Pair.of(from, to));
	}

	private static class Pair {
		private String from, to;

		private static Pair of(String from, String to) {
			Pair pair = new Pair();
			pair.from = from;
			pair.to = to;
			return pair;
		}

		@Override
		public boolean equals(Object obj) {
			Pair other = (Pair) obj;
			return other.from.equals(this.from) && other.to.equals(this.to);
		}

		@Override
		public int hashCode() {
			return 0;
		}
	}
}