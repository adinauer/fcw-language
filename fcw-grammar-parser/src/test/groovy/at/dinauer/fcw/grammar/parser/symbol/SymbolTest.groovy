package at.dinauer.fcw.grammar.parser.symbol

import org.junit.Test

public class SymbolTest {
    @Test
    public void testEqualsForNonTerminalSymbol() {
        def symbol1 = new NTSym('S')
        def symbol2 = new NTSym('S')

        assert symbol1 == symbol2
        assert symbol1.hashCode() == symbol2.hashCode()
    }

    @Test
    public void testEqualsForTerminalSymbol() {
        def symbol1 = new TSym('S')
        def symbol2 = new TSym('S')

        assert symbol1 == symbol2
        assert symbol1.hashCode() == symbol2.hashCode()
    }

    @Test
    public void testTerminalSymbolIsNotEqualToNonTerminalSymbol() {
        def t = new TSym('S')
        def nt = new NTSym('S')

        assert t != nt
    }

    @Test
    public void testEpsilonSymbol() {
        def eps = new TSym('EPS')

        assert eps.isEpsilon()
    }
}
