package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.type.*;

public final class NodeNew extends Node {

	public NodeNew(Node stm) {
		super(stm);
	}

	@Override
	public boolean checksType() {
		super.checksType();
		Node n = this.get(0);
		if (!(n instanceof NodeId)) {
			System.err.println("Cannot allocate a non-pointer "+n.toString());
			return false;
		}
		if (!(((NodeId)n).getType() instanceof TypePointer)) {
			System.err.println("Cannot allocate a non-pointer "+n.toString());
			return false;
		}
		return true;
	}

	@Override
	public NodeNew clone() {
		return new NodeNew((Node) this.get(0).clone());
	}

}
