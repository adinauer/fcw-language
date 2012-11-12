package at.dinauer.fcw.grammar.parser.symbol

import static org.junit.Assert.*
import org.junit.Test
import at.dinauer.fcw.grammar.parser.symbols.SymbolRegistry

public class SymbolRegistryTest {
    @Test
    public void testSameObject() {
        SymbolRegistry registry = new SymbolRegistry(Collections.emptySet());

        def symbol1 = registry.createSymbol('A')
        def symbol2 = registry.createSymbol('A')

        symbol1.markDeletable()
        assert symbol2.isDeletable()

        assert symbol1.is(symbol2)
    }

    @Test
    public void testNonTerminalVsTerminalCreation() {
        SymbolRegistry registry = new SymbolRegistry(['X'])

        def nt = registry.createSymbol('X')
        def t = registry.createSymbol('y')

        assert nt.isNonTerminal()
        assert t.isTerminal()
    }
}
