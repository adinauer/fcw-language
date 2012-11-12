package at.dinauer.fcw.grammar.parser

import at.dinauer.fcw.grammar.parser.symbols.Symbol

public class Alternative {
    public static final String SEPARATOR = '|'
    private ArrayList<Symbol> symbols

    public Alternative() {
        symbols = new ArrayList<Symbol>()
    }

    public void addSymbol(Symbol symbol) {
        symbols.add(symbol)
    }

    public ArrayList<Symbol> getSymbols() {
        return symbols
    }

    public String toString() {
        return symbols.join(Symbol.SEPARATOR)
    }

    public Boolean isDeletable() {
        Boolean deletable = true

        symbols.each { symbol ->
            if (!symbol.isDeletable()) {
                deletable = false
            }
        }

        return deletable
    }
}
