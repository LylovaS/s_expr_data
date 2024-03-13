// Generated from C:/1/S_expr_data/src/main/antlr/Data.g4 by ANTLR 4.13.1

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class DataLexer extends Lexer {
    static {
        RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, WS = 6, NAME = 7, STRING = 8, DOUBLE = 9,
            INT = 10;
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                "T__0", "T__1", "T__2", "T__3", "T__4", "WS", "NAME", "STRING", "ESC",
                "UNICODE", "HEX", "SAFECODEPOINT", "DOUBLE", "INT", "INT_NO_SIGN", "EXP"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'('", "')'", "'{'", "','", "'}'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, null, null, null, "WS", "NAME", "STRING", "DOUBLE",
                "INT"
        };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }


    public DataLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "Data.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public static final String _serializedATN =
            "\u0004\u0000\nt\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001" +
                    "\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004" +
                    "\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007" +
                    "\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b" +
                    "\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002" +
                    "\u000f\u0007\u000f\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001" +
                    "\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001" +
                    "\u0005\u0004\u0005-\b\u0005\u000b\u0005\f\u0005.\u0001\u0005\u0001\u0005" +
                    "\u0001\u0006\u0001\u0006\u0005\u00065\b\u0006\n\u0006\f\u00068\t\u0006" +
                    "\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007=\b\u0007\n\u0007\f\u0007" +
                    "@\t\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0003\bG\b\b" +
                    "\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001" +
                    "\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0004\fV\b\f\u000b\f\f\fW\u0001" +
                    "\f\u0003\f[\b\f\u0001\r\u0003\r^\b\r\u0001\r\u0001\r\u0001\u000e\u0001" +
                    "\u000e\u0001\u000e\u0005\u000ee\b\u000e\n\u000e\f\u000eh\t\u000e\u0003" +
                    "\u000ej\b\u000e\u0001\u000f\u0001\u000f\u0003\u000fn\b\u000f\u0001\u000f" +
                    "\u0004\u000fq\b\u000f\u000b\u000f\f\u000fr\u0000\u0000\u0010\u0001\u0001" +
                    "\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f" +
                    "\b\u0011\u0000\u0013\u0000\u0015\u0000\u0017\u0000\u0019\t\u001b\n\u001d" +
                    "\u0000\u001f\u0000\u0001\u0000\n\u0003\u0000\t\n\r\r  \u0002\u0000AZa" +
                    "z\u0004\u000009AZ__az\b\u0000\"\"//\\\\bbffnnrrtt\u0003\u000009AFaf\u0003" +
                    "\u0000\u0000\u001f\"\"\\\\\u0001\u000009\u0001\u000019\u0002\u0000EEe" +
                    "e\u0002\u0000++--y\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001" +
                    "\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001" +
                    "\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000" +
                    "\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000" +
                    "\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000" +
                    "\u0000\u0001!\u0001\u0000\u0000\u0000\u0003#\u0001\u0000\u0000\u0000\u0005" +
                    "%\u0001\u0000\u0000\u0000\u0007\'\u0001\u0000\u0000\u0000\t)\u0001\u0000" +
                    "\u0000\u0000\u000b,\u0001\u0000\u0000\u0000\r2\u0001\u0000\u0000\u0000" +
                    "\u000f9\u0001\u0000\u0000\u0000\u0011C\u0001\u0000\u0000\u0000\u0013H" +
                    "\u0001\u0000\u0000\u0000\u0015N\u0001\u0000\u0000\u0000\u0017P\u0001\u0000" +
                    "\u0000\u0000\u0019R\u0001\u0000\u0000\u0000\u001b]\u0001\u0000\u0000\u0000" +
                    "\u001di\u0001\u0000\u0000\u0000\u001fk\u0001\u0000\u0000\u0000!\"\u0005" +
                    "(\u0000\u0000\"\u0002\u0001\u0000\u0000\u0000#$\u0005)\u0000\u0000$\u0004" +
                    "\u0001\u0000\u0000\u0000%&\u0005{\u0000\u0000&\u0006\u0001\u0000\u0000" +
                    "\u0000\'(\u0005,\u0000\u0000(\b\u0001\u0000\u0000\u0000)*\u0005}\u0000" +
                    "\u0000*\n\u0001\u0000\u0000\u0000+-\u0007\u0000\u0000\u0000,+\u0001\u0000" +
                    "\u0000\u0000-.\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000./\u0001" +
                    "\u0000\u0000\u0000/0\u0001\u0000\u0000\u000001\u0006\u0005\u0000\u0000" +
                    "1\f\u0001\u0000\u0000\u000026\u0007\u0001\u0000\u000035\u0007\u0002\u0000" +
                    "\u000043\u0001\u0000\u0000\u000058\u0001\u0000\u0000\u000064\u0001\u0000" +
                    "\u0000\u000067\u0001\u0000\u0000\u00007\u000e\u0001\u0000\u0000\u0000" +
                    "86\u0001\u0000\u0000\u00009>\u0005\"\u0000\u0000:=\u0003\u0011\b\u0000" +
                    ";=\u0003\u0017\u000b\u0000<:\u0001\u0000\u0000\u0000<;\u0001\u0000\u0000" +
                    "\u0000=@\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000" +
                    "\u0000\u0000?A\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000AB\u0005" +
                    "\"\u0000\u0000B\u0010\u0001\u0000\u0000\u0000CF\u0005\\\u0000\u0000DG" +
                    "\u0007\u0003\u0000\u0000EG\u0003\u0013\t\u0000FD\u0001\u0000\u0000\u0000" +
                    "FE\u0001\u0000\u0000\u0000G\u0012\u0001\u0000\u0000\u0000HI\u0005u\u0000" +
                    "\u0000IJ\u0003\u0015\n\u0000JK\u0003\u0015\n\u0000KL\u0003\u0015\n\u0000" +
                    "LM\u0003\u0015\n\u0000M\u0014\u0001\u0000\u0000\u0000NO\u0007\u0004\u0000" +
                    "\u0000O\u0016\u0001\u0000\u0000\u0000PQ\b\u0005\u0000\u0000Q\u0018\u0001" +
                    "\u0000\u0000\u0000RS\u0003\u001b\r\u0000SU\u0005.\u0000\u0000TV\u0007" +
                    "\u0006\u0000\u0000UT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000" +
                    "WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XZ\u0001\u0000\u0000" +
                    "\u0000Y[\u0003\u001f\u000f\u0000ZY\u0001\u0000\u0000\u0000Z[\u0001\u0000" +
                    "\u0000\u0000[\u001a\u0001\u0000\u0000\u0000\\^\u0005-\u0000\u0000]\\\u0001" +
                    "\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000" +
                    "_`\u0003\u001d\u000e\u0000`\u001c\u0001\u0000\u0000\u0000aj\u00050\u0000" +
                    "\u0000bf\u0007\u0007\u0000\u0000ce\u0007\u0006\u0000\u0000dc\u0001\u0000" +
                    "\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001" +
                    "\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000" +
                    "ia\u0001\u0000\u0000\u0000ib\u0001\u0000\u0000\u0000j\u001e\u0001\u0000" +
                    "\u0000\u0000km\u0007\b\u0000\u0000ln\u0007\t\u0000\u0000ml\u0001\u0000" +
                    "\u0000\u0000mn\u0001\u0000\u0000\u0000np\u0001\u0000\u0000\u0000oq\u0007" +
                    "\u0006\u0000\u0000po\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000" +
                    "rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000s \u0001\u0000\u0000" +
                    "\u0000\r\u0000.6<>FWZ]fimr\u0001\u0006\u0000\u0000";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}