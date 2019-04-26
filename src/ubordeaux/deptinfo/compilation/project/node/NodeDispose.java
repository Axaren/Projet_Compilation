package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.type.*;

public final class NodeDispose extends Node {

	public NodeDispose(Node e) {
		super(e);
	}

	@Override
	public boolean checksType() {
		super.checksType();
		Node n = this.get(0);
		if (n == null || !(n instanceof NodeId)) {
			System.err.println("Cannot dispose of this expression "+n.toString());
			return false;
		}
		if (! (((NodeId)n).getType() instanceof TypePointer)) {
			System.err.println("Cannot free a non-pointer "+n.toString());
			return false;	
		}
		return true;
	}

	@Override
	public NodeDispose clone() {
		return new NodeDispose((Node) this.get(0).clone());
	}

}
