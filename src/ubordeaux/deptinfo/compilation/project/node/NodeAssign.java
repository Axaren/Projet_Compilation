package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Mem;
import ubordeaux.deptinfo.compilation.project.type.Type;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Move;

public final class NodeAssign extends Node {

	public NodeAssign(NodeExp lhs, NodeExp rhs) {
		super(lhs, rhs);
	}

	@Override
	public boolean checksType() {
		super.checksType();
		if (!get(0).checksType())
			return false;
		if (!get(1).checksType())
			return false;
		Type lhsType = this.getLhs().getType();
		Type rhsType = this.getRhs().getType();
		System.out.println(lhsType.toString()+ " | "+rhsType.toString());
		return lhsType != null && rhsType != null && lhsType.equals(rhsType);
	}

	private NodeExp getLhs() {
		return (NodeExp) this.get(0);
	}

	private NodeExp getRhs() {
		return (NodeExp) this.get(1);
	}

	@Override
	public NodeAssign clone() {
		return new NodeAssign((NodeExp) getLhs().clone(), (NodeExp) getRhs().clone());
	}

	@Override
	public void generateIntermediateCode() {
		getLhs().generateIntermediateCode();
		getRhs().generateIntermediateCode();

		addStmList(new Move( new Mem(getLhs().getExpList().getHead()), getRhs().getExpList().getHead()));
		
	}

}
