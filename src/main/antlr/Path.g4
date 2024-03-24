grammar Path;

main
    : locationPath EOF
    ;

locationPath
    : relativeLocationPath
    | absoluteLocationPathNoroot
    ;

absoluteLocationPathNoroot
    : '/' relativeLocationPath
    | '//' relativeLocationPath
    ;

relativeLocationPath
    : step (('/' | '//') step)*
    ;

step
    : nCName predicate?
    | abbreviatedStep predicate?
    | '@element' predicate?
    | '@value' predicate?
    ;

predicate
    : '[' expr ']'
    ;

abbreviatedStep
    : '.'
    | '..'
    ;

expr
    : '@' nCName ('=' | '!=') STRING
    ;

nCName
    : NAME
    | '*'
    ;

STRING
    : '"' (ESC | SAFECODEPOINT)* '"'
    ;

fragment ESC
    : '\\' (["\\/bfnrt] | UNICODE)
    ;
fragment UNICODE
    : 'u' HEX HEX HEX HEX
    ;
fragment HEX
    : [0-9a-fA-F]
    ;
fragment SAFECODEPOINT
    : ~ ["\\\u0000-\u001F]
    ;

NAME: [A-Za-z] [A-Za-z0-9_]*;


Whitespace
    : (' ' | '\t' | '\n' | '\r')+ -> skip
    ;
