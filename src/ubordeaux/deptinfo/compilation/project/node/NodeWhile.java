package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.*;

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
	public IntermediateCode generateIntermediateCode() {
		IntermediateCode getExp = getExp().generateIntermediateCode();
		IntermediateCode getStm = getStm().generateIntermediateCode();
		NodeRel exp = (NodeRel) getExp();

		Exp expLeft = ((ExpList)getExp).get(0);
		Exp expRight = ((ExpList)getExp).get(1);

		LabelLocation z = new LabelLocation();
		LabelLocation f = new LabelLocation();

		return new Seq( new Cjump(exp.getRel().getCode(), expLeft, expRight, z, f) ,
				new Seq( new Seq(new Label(z), (Stm)getStm ),
						new Seq(new Label(f), new Jump((Exp)getExp, new LabelLocationList(z, null)))));

	}
}
