package ubordeaux.deptinfo.compilation.project.main;

import beaver.Parser;
import beaver.Scanner;
import java.io.FileReader;

import ubordeaux.deptinfo.compilation.project.node.Node;
import ubordeaux.deptinfo.compilation.project.intermediateCode.*;


public class Main {
	private static boolean checksType;
	private static boolean debug;

	public static void main(String[] args) throws Exception {
		int count = 1;
		for (String arg : args) {
			if (arg.charAt(0) == '-') {
				if (arg.equals("-checkType"))
					checksType = true;
				else if (arg.equals("-debug"))
					debug = true;
			} else {
				ScannerLea input = new ScannerLea(new FileReader(arg));
				input.setDebug(debug);
				Parser parser = new ParserLea();
				try {
					System.err.println("*** Fichier " + arg);
					Node result = (Node) parser.parse(input);
					System.out.println(result.toString());
					result.toDot("data/output_tree"+count+".dot");
					System.err.println("*** Fichier " + arg);
					System.err.println("*** Analyse syntaxique ok");
					if (checksType) {
						if (!result.checksType())
							System.err.println("*** Erreur de typage");
						else
							System.err.println("*** Typage correct");
					}
					Seq seq = (Seq)result.generateIntermediateCode();
					System.err.println("*** Fichier " + arg);
					System.out.println("\n");
					System.out.println("**** CODE INTER pour programme" + arg + "\n\n");
					System.out.println(seq.toString());
					seq.toDot("data/code_tree"+count+".dot");
					System.out.println("\n\n");
					System.out.println("**** FIN CODE INTER \n");
					System.out.println("\n\n");
					count++;					
				} catch (beaver.Parser.Exception e) {
					System.err.println("*** Erreur de syntaxe: " + arg + ":" + e.getMessage());
				} catch (RuntimeException e) {
					System.err.println("*** Compilation error in \""+arg+"\" : "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
}