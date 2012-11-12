package at.dinauer.fcw.grammar.parser.symbol

import at.dinauer.fcw.grammar.parser.symbols.Symbol

public class NTSym extends Symbol {
    public NTSym(String symbol) {
        super(symbol, Symbol.Type.NON_TERMINAL)
    }
}
