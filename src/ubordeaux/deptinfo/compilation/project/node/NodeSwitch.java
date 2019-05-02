package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.*;

public final class NodeSwitch extends Node {

	public NodeSwitch(Node e, Node stm) {
		super(e, stm);
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
	public NodeSwitch clone() {
		return new NodeSwitch((Node) this.getExp().clone(), (Node) this.getStm().clone());
	}

	private Node getStm() {
		return this.get(1);
	}

	private Node getExp() {
		return this.get(0);
	}

	@Override
	public IntermediateCode generateIntermediateCode() {
		int size = getStm().size();
		IntermediateCode exp = getExp().generateIntermediateCode();
		getStm().generateIntermediateCode();
		NodeRel rel = (NodeRel)getExp();
		Seq res = null;
		IntermediateCode stmDefault =  ((NodeCase) getStm().get(size)).getStm().generateIntermediateCode();

		for (int i = 0; i < size-1; i++){
			NodeCase nodeCase = ((NodeCase) getStm().get(i));
			IntermediateCode stm =  ((NodeCase) getStm().get(i)).getStm().generateIntermediateCode();
			LabelLocation l = new LabelLocation();
			LabelLocation defaultLabel = new LabelLocation();
			LabelLocation next = new LabelLocation();

			res = new Seq(new Label(next),
							new Seq( new Cjump(rel.getRel().getCode(), new Mem((Exp)exp), new Mem(new Name(new LabelLocation(nodeCase.getNameValue()))), l, next),
								new Seq(new Label(l),
							 			new Seq( (Stm)stm, new Seq(res.getLeft(), res.getRight())))));

		}

		return res;

	}
}
