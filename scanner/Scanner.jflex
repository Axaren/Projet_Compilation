package ubordeaux.deptinfo.compilation.project.main;
import beaver.Symbol;
import beaver.Scanner;

%%

%class ScannerLea
%extends Scanner
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%unicode
%line
%column
%{
	private Symbol newToken(short id) {
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), null);
	}

	private Symbol newToken(short id, Object value) {
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
	}

%}

InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n

Identifier = [a-zA-Z_][a-zA-Z0-9_]*
String = "\""[a-zA-Z_][a-zA-Z0-9_]{64}"\""
Integer = [0-9]+
Decimal = ({Integer}(\.{Integer})?)|(\.{Integer})
Float = {Decimal}([eE][+-]?{Integer})?

/* Comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

WhiteSpace = [ \t\f]


%%

/* OPERATORS */
"="  { return newToken(Terminals.TOKEN_AFF); }
"^"  { return newToken(Terminals.TOKEN_CIRC); }
"+"  { return newToken(Terminals.TOKEN_PLUS); }
"-"  { return newToken(Terminals.TOKEN_MINUS); }
"*"  { return newToken(Terminals.TOKEN_TIMES); }
"/"  { return newToken(Terminals.TOKEN_DIV); }
"&&" { return newToken(Terminals.TOKEN_AND); }
"||" { return newToken(Terminals.TOKEN_OR); }
"!"  { return newToken(Terminals.TOKEN_NOT); }
"<"  { return newToken(Terminals.TOKEN_LT); }
"<=" { return newToken(Terminals.TOKEN_LE); }
">"  { return newToken(Terminals.TOKEN_GT); }
">=" { return newToken(Terminals.TOKEN_GE); }
"==" { return newToken(Terminals.TOKEN_EQ); }
"!=" { return newToken(Terminals.TOKEN_NE); }

/* PUNCTUATION */
";"  { return newToken(Terminals.TOKEN_SEMIC); }
".." { return newToken(Terminals.TOKEN_DOTDOT); }
":"  { return newToken(Terminals.TOKEN_COLON); }
","  { return newToken(Terminals.TOKEN_COMMA); }
"("  { return newToken(Terminals.TOKEN_LPAR); }
")"  { return newToken(Terminals.TOKEN_RPAR); }
"["  { return newToken(Terminals.TOKEN_LBRACKET); }
"]"  { return newToken(Terminals.TOKEN_RBRAKET); }
"{"  { return newToken(Terminals.TOKEN_LBRACE); }
"}"  { return newToken(Terminals.TOKEN_RBRACE); }

/* KEYWORDS */
"type"      { return newToken(Terminals.TOKEN_TYPE); }
"string"    { return newToken(TOKEN_STRING); }
"integer"   { return newToken(TOKEN_INTEGER); }
"boolean"   { return newToken(TOKEN_BOOLEAN); }
"array"     { return newToken(TOKEN_ARRAY); }
"of"        { return newToken(TOKEN_OF); }
"struct"    { return newToken(TOKEN_STRUCT); }
"var"       { return newToken(TOKEN_VAR); }
"procedure" { return newToken(TOKEN_PROCEDURE); }
"function"  { return newToken(TOKEN_FUNCTION); }
"begin"     { return newToken(TOKEN_BEGIN); }
"end"       { return newToken(TOKEN_END); }
"new"       { return newToken(TOKEN_NEW); }
"dispose"   { return newToken(TOKEN_DISPOSE); }
"println"   { return newToken(TOKEN_PRINTLN); }
"readln"    { return newToken(TOKEN_READLN); }
"return"    { return newToken(TOKEN_RETURN); }
"if"        { return newToken(TOKEN_IF); }
"then"      { return newToken(TOKEN_THEN); }
"else"      { return newToken(TOKEN_ELSE); }
"while"     { return newToken(TOKEN_WHILE); }
"do"        { return newToken(TOKEN_DO); }
"switch"    { return newToken(TOKEN_SWITCH); }
"case"      { return newToken(TOKEN_CASE); }
"default"   { return newToken(TOKEN_DEFAULT); }

/* LITERALS */
"true"       { return newToken(TOKEN_TRUE); }
"false"      { return newToken(TOKEN_FALSE); }
"null"       { return newToken(TOKEN_NULL); }
{String}     { return newToken(TOKEN_LIT_STRING); }
{Identifier} { return newToken(TOKEN_IDENTIFIER); }
{Integer}    { return newToken(TOKEN_LIT_INTEGER); }

/* OTHER */

[\s] {}

[^] { throw new Scanner.Exception("caractère inattendu '" + yytext() + "'"); }
