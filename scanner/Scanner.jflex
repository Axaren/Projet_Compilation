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
	  System.out.println("Detected: " + Terminals.NAMES[id]);
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), null);
	}

	private Symbol newToken(short id, Object value) {
	  System.out.println("Detected: " + Terminals.NAMES[id] + " Value: " + yytext());
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
	}

%}

InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n

Identifier = [a-zA-Z_][a-zA-Z0-9_]*
String = "\""[a-zA-Z_][a-zA-Z0-9_]{0,64}"\""
Integer = [0-9]+

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
"]"  { return newToken(Terminals.TOKEN_RBRACKET); }
"{"  { return newToken(Terminals.TOKEN_LBRACE); }
"}"  { return newToken(Terminals.TOKEN_RBRACE); }

/* KEYWORDS */
"type"      { return newToken(Terminals.TOKEN_TYPE); }
"string"    { return newToken(Terminals.TOKEN_STRING); }
"integer"   { return newToken(Terminals.TOKEN_INTEGER); }
"boolean"   { return newToken(Terminals.TOKEN_BOOLEAN); }
"array"     { return newToken(Terminals.TOKEN_ARRAY); }
"of"        { return newToken(Terminals.TOKEN_OF); }
"struct"    { return newToken(Terminals.TOKEN_STRUCT); }
"var"       { return newToken(Terminals.TOKEN_VAR); }
"procedure" { return newToken(Terminals.TOKEN_PROCEDURE); }
"function"  { return newToken(Terminals.TOKEN_FUNCTION); }
"begin"     { return newToken(Terminals.TOKEN_BEGIN); }
"end"       { return newToken(Terminals.TOKEN_END); }
"new"       { return newToken(Terminals.TOKEN_NEW); }
"dispose"   { return newToken(Terminals.TOKEN_DISPOSE); }
"println"   { return newToken(Terminals.TOKEN_PRINTLN); }
"readln"    { return newToken(Terminals.TOKEN_READLN); }
"return"    { return newToken(Terminals.TOKEN_RETURN); }
"if"        { return newToken(Terminals.TOKEN_IF); }
"then"      { return newToken(Terminals.TOKEN_THEN); }
"else"      { return newToken(Terminals.TOKEN_ELSE); }
"while"     { return newToken(Terminals.TOKEN_WHILE); }
"do"        { return newToken(Terminals.TOKEN_DO); }
"switch"    { return newToken(Terminals.TOKEN_SWITCH); }
"case"      { return newToken(Terminals.TOKEN_CASE); }
"default"   { return newToken(Terminals.TOKEN_DEFAULT); }

/* LITERALS */
"true"       { return newToken(Terminals.TOKEN_TRUE, true); }
"false"      { return newToken(Terminals.TOKEN_FALSE, false); }
"null"       { return newToken(Terminals.TOKEN_NULL, null); }
{String}     { return newToken(Terminals.TOKEN_LIT_STRING, yytext()); }
{Identifier} { return newToken(Terminals.TOKEN_IDENTIFIER, yytext()); }
{Integer}    { return newToken(Terminals.TOKEN_LIT_INTEGER, new Integer(yytext())); }

/* OTHER */

{WhiteSpace} {}
{Comment} {}

[\s] {}

[^] { throw new Scanner.Exception("caractère inattendu '" + yytext() + "'"); }
