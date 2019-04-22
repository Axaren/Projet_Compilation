package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.type.Type;

public final class NodeNop extends Node {

	public NodeNop() {

	}

	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public boolean checksType() {
		return true;
	}

	public String toDotNodeName() {
		return "NodeNop";
	}

	@Override
	public NodeNop clone() {
		return new NodeNop();
	}

}
