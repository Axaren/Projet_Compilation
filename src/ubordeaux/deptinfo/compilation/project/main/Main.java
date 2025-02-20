package ubordeaux.deptinfo.compilation.project.main;

import beaver.Parser;
import beaver.Scanner;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ubordeaux.deptinfo.compilation.project.node.Node;
import ubordeaux.deptinfo.compilation.project.intermediateCode.*;


public class Main {
	private static boolean checksType;
	private static boolean debug;
	
	/**
	 *	Linearize a tree of intermediate code (prefix depth-first search)
	 */
	public static void linearize(Seq seq, ArrayList visited) {
		if (seq.getLeft() != null && seq.getLeft() instanceof Stm) {
			if (seq.getLeft() instanceof Seq)
				linearize((Seq) seq.getLeft(), visited);
			else
				visited.add(seq.getLeft());
		} 
		if (seq.getRight()  != null && seq.getRight() instanceof Stm) {
			if (seq.getRight() instanceof Seq)
				linearize((Seq) seq.getRight(), visited);
			else
				visited.add(seq.getRight());
		}

	}


	public static void main(String[] args) throws Exception {
		int count = 0;
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
				count++;					
				try {
					// Syntax analysis
					System.err.println("******** Fichier " + arg);
					Node result = (Node) parser.parse(input);
					System.out.println(result.toString());
					result.toDot("data/syntax_tree"+count+".dot");
					System.err.println("Analyse syntaxique OK ! L'arbre syntaxique est disponible dans data/output_tree"+count+".dot\n");

					//  Checking type
					if (checksType) {
						if (!result.checksType())
							System.err.println("/!\\ Erreur de typage\n");
						else
							System.err.println("Typage correct !\n");
					}

					// Intermediate code generation
					Seq seq = (Seq)result.generateIntermediateCode();
					System.out.println("Code intermédiaire (arbre). L'arbre au format dot est disponible dans data/code_tree"+count+".dot\n");
					System.out.println(seq.toString());
					seq.toDot("data/code_tree"+count+".dot");
					System.out.println("\n");

					// Linearize the intermediate code tree
					ArrayList<IntermediateCode> visited = new ArrayList<>();
					linearize(seq, visited);
					System.out.println("Code intermédiaire linéarisé (instructions) : ");
					for (IntermediateCode node : visited) {
						System.out.print(""  + node.getClass().getSimpleName()+", ");
					}
					System.out.println("\n\n\n");


				} catch (beaver.Parser.Exception e) {
					System.err.println("*** Erreur de syntaxe: " + arg + ":" + e.getMessage());
				} catch (RuntimeException e) {
					System.err.println("*** Compilation error in \""+arg+"\" : "+e.getMessage());
					//e.printStackTrace();
				}
			}
		}
	}
}