package ubordeaux.deptinfo.compilation.project.node;

import java.util.Iterator;
import ubordeaux.deptinfo.compilation.project.intermediateCode.*;

public final class NodeList extends Node {

	public NodeList(Node stm) {
		super(stm);
	}

	public NodeList() {
		super();
	}

	@Override
	public boolean checksType() {
		super.checksType();
		boolean result = true;
		for (Node elt : this.elts) {
			if (elt != null && !elt.checksType()) {
				result = false;
				break;
			}
		}
		return result;
	}

	public Iterator<Node> iterator() {
		return this.elts.iterator();
	}

	public int size() {
		return this.elts.size();
	}


	@Override
	public NodeList clone() {
		NodeList node = new NodeList();
		for (Node elt : this.elts) {
			node.add((Node) elt.clone());
		}
		return node;
	}

	public Seq generateSeq(int i) {
		if (i >= this.size()) return null;
		IntermediateCode elt = this.elts.get(i).generateIntermediateCode();
		if (elt instanceof Exp) return new Seq(new ExpStm((Exp)elt), (Stm)generateSeq(i+1));
		else return new Seq((Stm)elt, (Stm)generateSeq(i+1));
	}

	@Override
	public IntermediateCode generateIntermediateCode() {
		return generateSeq(0);
	}

}
