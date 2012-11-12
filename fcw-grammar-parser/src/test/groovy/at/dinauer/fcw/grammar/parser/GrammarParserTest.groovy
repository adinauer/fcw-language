package at.dinauer.fcw.grammar.parser

import static org.junit.Assert.*
import org.junit.Test

import at.dinauer.fcw.grammar.parser.symbol.TSym
import at.dinauer.fcw.grammar.parser.symbols.Symbol

import at.dinauer.fcw.grammar.parser.symbol.NTSym

class GrammarParserTest {

    @Test
    public void testDetermineRootElementForDefinitionWithSpaces() {
        GrammarParser parser = new GrammarParser()

        Grammar grammar = parser.parse(' G( RootElement ): ')
        assert new NTSym('RootElement') == grammar.root
    }

    @Test(expected = IllegalGrammarException)
    public void testNoRootElementDefinition() {
        GrammarParser parser = new GrammarParser()

        Grammar grammar = parser.parse('A -> b')
        fail("\n${grammar.toString()}")
    }

    @Test
    public void testParseGrammar() {
        GrammarParser parser = new GrammarParser()

        def grammarAsText = '''
            G(S):
            S -> A ;
            A -> b S | c d a
        '''
        Grammar grammar = parser.parse(grammarAsText)

        assert new NTSym('S') == grammar.root
        assert 'A ;' == grammar['S'].toString()
            assert 'b S' == grammar['A'].alternatives[0].toString()
                assert new TSym('b') == grammar['A'].alternatives[0].symbols[0]
                assert new NTSym('S') == grammar['A'].alternatives[0].symbols[1]
            assert 'c d a' == grammar['A'].alternatives[1].toString()
                assert new TSym('c') == grammar['A'].alternatives[1].symbols[0]
                assert new TSym('d') == grammar['A'].alternatives[1].symbols[1]
                assert new TSym('a') == grammar['A'].alternatives[1].symbols[2]
    }

    @Test
    public void testParseGrammarContainingDeletableSymbols() {
        GrammarParser parser = new GrammarParser()

        def grammarAsText = """
            G(S):
            S -> A ;
            A -> B | a
            B -> $Symbol.EPSILON | b
        """
        Grammar grammar = parser.parse(grammarAsText)

        assert grammar['B'].alternatives[0].isDeletable()
        assert grammar['A'].alternatives[0].isDeletable()
        assert !grammar['S'].alternatives[0].isDeletable()
    }
}
