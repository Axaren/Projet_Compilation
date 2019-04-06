import beaver.Symbol;
import beaver.Scanner;

%%

%class ScannerExp
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


%%

/* OPERATORS */
"="
	{
		return newToken(Terminals.TOKEN_AFF);
	}
"^"
	{
		return newToken(Terminals.TOKEN_CIRC);
	}
"+"
	{
		return newToken(Terminals.TOKEN_PLUS);
	}
"-"
	{
		return newToken(Terminals.TOKEN_MINUS);
	}
"*"
	{
		return newToken(Terminals.TOKEN_TIMES);
	}
"/"
	{
		return newToken(Terminals.TOKEN_DIV);
	}
"&&"
	{
		return newToken(Terminals.TOKEN_AND);
	}
"||"
	{
		return newToken(Terminals.TOKEN_OR);
	}
"!"
	{
		return newToken(Terminals.TOKEN_NOT);
	}
"<"
	{
		return newToken(Terminals.TOKEN_LT);
	}
"<="
	{
		return newToken(Terminals.TOKEN_LE);
	}
">"
	{
		return newToken(Terminals.TOKEN_GT);
	}
">="
	{
		return newToken(Terminals.TOKEN_GE);
	}
"=="
	{
		return newToken(Terminals.TOKEN_EQ);
	}    
"!="
	{
		return newToken(Terminals.TOKEN_NE);
	}

/* PUNCTUATION */
";"
	{
		return newToken(Terminals.TOKEN_SEMIC);
	}
".."
	{
		return newToken(Terminals.TOKEN_DOTDOT);
	}
":"
	{
		return newToken(Terminals.TOKEN_COLON);
	}
","
	{
		return newToken(Terminals.TOKEN_COMMA);
	}
"("
	{
		return newToken(Terminals.TOKEN_LPAR);
	}
")"
	{
		return newToken(Terminals.TOKEN_RPAR);
	}
"["
	{
		return newToken(Terminals.TOKEN_LBRACKET);
	}
"]"
	{
		return newToken(Terminals.TOKEN_RBRAKET);
	}
"{"
	{
		return newToken(Terminals.TOKEN_LBRACE);
	}
"}"
	{
		return newToken(Terminals.TOKEN_RBRACE);
	}

/* KEYWORDS (UNFINISHED!!) */



/* LITERALS (UNFINISHED!!) */


/* OTHER */

[\s] {}

[^] 
    {
		throw new Scanner.Exception("caractère inattendu '" + yytext() + "'");
	}
