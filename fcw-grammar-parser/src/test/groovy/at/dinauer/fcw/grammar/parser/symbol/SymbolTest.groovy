package at.dinauer.fcw.grammar.parser.symbol

import static org.junit.Assert.*
import org.junit.Test
import at.dinauer.fcw.grammar.parser.symbols.NonTerminalSymbol
import at.dinauer.fcw.grammar.parser.symbols.TerminalSymbol

public class SymbolTest {
    @Test
    public void testEqualsForNonTerminalSymbol() {
        def symbol1 = new NonTerminalSymbol('S')
        def symbol2 = new NonTerminalSymbol('S')

        assert symbol1 == symbol2
        assert symbol1.hashCode() == symbol2.hashCode()
    }

    @Test
    public void testEqualsForTerminalSymbol() {
        def symbol1 = new TerminalSymbol('S')
        def symbol2 = new TerminalSymbol('S')

        assert symbol1 == symbol2
        assert symbol1.hashCode() == symbol2.hashCode()
    }

    @Test
    public void testTerminalSymbolIsNotEqualToNonTerminalSymbol() {
        def t = new TerminalSymbol('S')
        def nt = new NonTerminalSymbol('S')

        assert t != nt
    }

    @Test
    public void testEpsilonSymbol() {
        def eps = new TerminalSymbol('EPS')

        assert eps.isEpsilon()
    }
}
