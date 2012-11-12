package at.dinauer.fcw.grammar.parser.symbol

import at.dinauer.fcw.grammar.parser.symbols.Symbol

public class TSym extends Symbol {
    public TSym(String symbol) {
        super(symbol, Symbol.Type.TERMINAL)
    }
}
