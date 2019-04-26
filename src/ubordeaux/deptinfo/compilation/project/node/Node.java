package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Exp;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocation;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Stm;
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

	private Exp exp;
	private Stm stm;
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
		this.elts = new ArrayList<Node>();
		
	}

	public Node(Node ... args) {
		this();
		for (Node elt : args) {
			elts.add(elt);
		}
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
	};
	
	public void add(Node elt) {
		this.elts.add(elt);
	}

	public Node get(int i) {
		return elts.get(i);
	}

	public Exp getExpCode(){
		return this.exp;
	}

	public void setExpCode(Exp exp){
		this.exp = exp;
	}

	public int size() {
		return elts.size();
	}

	public Stm getStmCode() {
		return stm;
	}

	public void setStmCode(Stm stm) {
		this.stm = stm;
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
