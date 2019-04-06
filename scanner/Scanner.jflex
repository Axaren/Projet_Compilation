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

[\s] {}

[^] 
    {
		throw new Scanner.Exception("caractère inattendu '" + yytext() + "'");
	}
