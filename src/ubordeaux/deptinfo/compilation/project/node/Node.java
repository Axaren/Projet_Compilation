package ubordeaux.deptinfo.compilation.project.node;

import java.util.Arrays;

import ubordeaux.deptinfo.compilation.project.intermediateCode.*;
import ubordeaux.deptinfo.compilation.project.main.ClonableSymbol;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Node extends ClonableSymbol implements NodeInterface {

	protected List<Node> elts;
	protected int uniqId;
	private static int staticUniqId;

	private ExpList expList;
	private StmList stmList;
	private LabelLocation labelLocation;

	public LabelLocation getLabelLocation() {
		return labelLocation;
	}

	public void setLabelLocation(LabelLocation labelLocation) {
		this.labelLocation = labelLocation;
	}

	public Node() {
		super();
		this.uniqId = Node.staticUniqId++;
		//System.err.println("Create class " + this.getClass().getSimpleName());
		this.elts = new ArrayList<>();
		
	}

	public Node(Node ... args) {
		this();
		elts.addAll(Arrays.asList(args));
	}

	public String toString() {
		String ret = this.getClass().getSimpleName();
		if (elts.size() > 0)
			ret += '(';
		boolean first = true;
		for (Node elt : this.elts) {
			if (first)
				first = false;
			else
				ret += ", ";
			ret += elt.toString();
		}
		if (elts.size() > 0)
			ret += ')';
		return ret;
	}

	@Override
	public boolean checksType() {
		System.err.println("--- CheckType " + this.getClass().getSimpleName());
		return false;
	}

	public void add(Node elt) {
		this.elts.add(elt);
	}

	public Node get(int i) {
		return elts.get(i);
	}

	public ExpList getExpList() {
		return expList;
	}

	public void setExpList(ExpList expList) {
		this.expList = expList;
	}
	public void addExpList(Exp exp) {
		this.expList.setHead(exp);
	}
	public StmList getStmList() {
		return stmList;
	}

	public void setStmList(StmList stmList) {
		this.stmList = stmList;
	}

	public void addStmList(Stm stm) {
		this.stmList.setHead(stm);
	}

	public int size() {
		return elts.size();
	}


	private final void toDot(StringBuffer stringBuffer) {
		stringBuffer.append("node_" + this.uniqId + " [shape=\"ellipse\", label=\"" + toDotNodeName() + "\"];\n");
		for (Node elt : this.elts) {
			elt.toDot(stringBuffer);
			stringBuffer.append("node_" + this.uniqId + " -> node_" + elt.uniqId + ";\n");
		}

	}

	protected String toDotNodeName() {
		return getClass().getSimpleName();
	}

	public final void toDot(String file) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write("digraph Stree {\n");
			StringBuffer str = new StringBuffer();
			toDot(str);
			out.write(str.toString());
			out.write("}\n");
			out.close();
		} catch (IOException e) {
			System.err.println("ERROR: build dot");
		}
	}

	@Override
	public void generateIntermediateCode() {
		// TODO Auto-generated method stub
		System.err.println("TODO: " + this.getClass().getSimpleName() + ".generateIntermediateCode()");
	}

}
