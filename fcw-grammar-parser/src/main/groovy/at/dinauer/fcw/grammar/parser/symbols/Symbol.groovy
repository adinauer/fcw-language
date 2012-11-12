package at.dinauer.fcw.grammar.parser.symbols

public class Symbol {
    public static final String SEPARATOR = ' '
    public static final Symbol EPSILON = new Symbol('EPS', Type.TERMINAL)

    protected String symbol
    protected Boolean deletable

    public static enum Type {
        TERMINAL,
        NON_TERMINAL
    }

    protected Type type
    public Symbol(String symbol, Type type) {
        this.symbol = symbol.trim()
        this.type = type
        this.deletable = false
    }

    public boolean isNonTerminal() {
        return type == Type.NON_TERMINAL
    }

    public boolean isTerminal() {
        return type == Type.TERMINAL
    }

    public boolean isEpsilon() {
        return this == EPSILON
    }

    public boolean isDeletable() {
        return deletable
    }

    public void markDeletable() {
        deletable = true
    }

    public String toString() {
        return symbol
    }

    boolean equals(o) {
        if (this.is(o)) { return true }
        if (!(o instanceof Symbol)) { return false }

        Symbol symbol1 = (Symbol) o

        if (symbol != symbol1.symbol) { return false }
        if (type != symbol1.type) { return false }

        return true
    }

    int hashCode() {
        int result
        result = symbol.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}
