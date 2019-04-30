package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Cjump;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Exp;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocation;

public final class NodeWhile extends Node {

	public NodeWhile(Node boolExpr, Node stm) {
		super(boolExpr, stm);
	}

	@Override
	public boolean checksType() {
		super.checksType();
		if (!get(0).checksType())
			return false;
		if (!get(1).checksType())
			return false;
		return true;
	}

	@Override
	public NodeWhile clone() {
		return new NodeWhile((Node) this.getExp().clone(), (Node) this.getStm().clone());
	}

	private Node getStm() {
		return this.get(1);
	}

	private Node getExp() {
		return this.get(0);
	}

	@Override
	public void generateIntermediateCode() {
		getStm().generateIntermediateCode();
		getExp().generateIntermediateCode();
		NodeRel exp = (NodeRel) getExp();

		Exp rel = getExp().getExpList().get(0);
		Exp expLeft = getExp().getExpList().get(1);
		Exp expRight = getExp().getExpList().get(2);

		LabelLocation z = new LabelLocation();
		LabelLocation f = new LabelLocation();

		addStmList( new Cjump(exp.getRel().getCode(), expLeft, expRight, z, f) );	
	}

}
