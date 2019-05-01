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
		Stm stmCode = (Stm)getStm().generateIntermediateCode();
		NodeRel exp = (NodeRel) getExp();

		Exp expLeft = (Exp)(exp.getOp1().generateIntermediateCode());
		Exp expRight = (Exp)(exp.getOp2().generateIntermediateCode());

		LabelLocation head = new LabelLocation();
		LabelLocation body = new LabelLocation();
		LabelLocation end = new LabelLocation();

		return new Seq( new Label(head),
					 new Seq(new Cjump(exp.getRel().getCode(), expLeft, expRight, body, end),
							new Seq(new Label(body),
									new Seq(stmCode,
											new Seq(new Jump(head), new Label(end))))));
	}
}
