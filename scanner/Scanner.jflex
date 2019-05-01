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
%eofval{
	return new Symbol(Terminals.EOF, "end-of-file");
%eofval}
%{
  StringBuffer currentString = new StringBuffer();
  boolean debug = false;

  public void setDebug(boolean deb) {
    debug = deb;
  }

	private Symbol newToken(short id) {
	  if (debug) System.out.print("" + Terminals.NAMES[id]+" | ");
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), null);
	}

	private Symbol newToken(short id, Object value) {
	  if (debug) System.out.print("" + Terminals.NAMES[id] + " Value: " + value+" | ");
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
	}

%}



/* Literals */
Identifier = [a-zA-Z_][a-zA-Z0-9_]*
DecInteger = 0 | [1-9][0-9]*

HexInteger = 0 [xX] 0* {HexDigit} {1,8}
HexDigit   = [0-9a-fA-F]

OctInteger = 0+ [1-3]? {OctDigit} {1,15}
OctDigit   = [0-7]

InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

/* Comments */
Comment              = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

%state STRING

%%

<YYINITIAL> {

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
  "true"  { return newToken(Terminals.TOKEN_TRUE, true); }
  "false" { return newToken(Terminals.TOKEN_FALSE, false); }
  "null"  { return newToken(Terminals.TOKEN_NULL, null); }

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

  [\"\'] { currentString.setLength(0); yybegin(STRING); }

  {Identifier}                               { return newToken(Terminals.TOKEN_IDENTIFIER, yytext()); }
  {DecInteger} | {HexInteger} | {OctInteger} { return newToken(Terminals.TOKEN_LIT_INTEGER, new Integer(Integer.decode(yytext()))); }

  {WhiteSpace} {}
  {Comment} {}
}

<STRING> {
      [\"\']       { yybegin(YYINITIAL);
                     return newToken(Terminals.TOKEN_LIT_STRING,
                                     currentString.toString()); }
      [^\n\r\"\\]+ { currentString.append( yytext() ); }
      \\t          { currentString.append('\t'); }
      \\n          { currentString.append('\n'); }

      \\r          { currentString.append('\r'); }
      \\\"         { currentString.append('\"'); }
      \\           { currentString.append('\\'); }
    }

/* UNEXPECTED CHAR */

[^] { throw new Error("caractère inattendu '" + yytext() + "'"); }
