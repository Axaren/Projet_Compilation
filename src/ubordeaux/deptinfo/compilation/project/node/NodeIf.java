package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.*;

public final class NodeIf extends Node {

	public NodeIf(Node boolExp, Node stm) {
		super(boolExp, stm);
	}

	public NodeIf(Node e, Node stm1, Node stm2) {
		super(e, stm1, stm2);
	}

	@Override
	public boolean checksType() {
		super.checksType();
		return true;
	}

	@Override
	public NodeIf clone() {
		Node expNode = this.getExpNode();
		Node thenNode = this.getThenNode();
		Node elseNode = this.getElseNode();
		if (elseNode == null)
			return new NodeIf((Node) expNode.clone(), (Node) thenNode.clone());
		else
			return new NodeIf(expNode, (Node) thenNode.clone(), (Node) elseNode.clone());
	}

	private Node getExpNode() {
		return this.get(0);
	}

	private Node getElseNode() {
		if (this.size() == 3)
			return this.get(2);
		else
			return null;
	}

	private Node getThenNode() {
		return this.get(1);
	}

	@Override
	public IntermediateCode generateIntermediateCode() {
		IntermediateCode expIf = getExpNode().generateIntermediateCode();
		getThenNode().generateIntermediateCode();
		getElseNode().generateIntermediateCode();

		NodeRel exp = (NodeRel) getExpNode();
		NodeRel thenExp = (NodeRel) getThenNode();
		NodeRel elseExp = (NodeRel) getElseNode();


		LabelLocation z = new LabelLocation();
		LabelLocation f = new LabelLocation();
		LabelLocation t = new LabelLocation();

		Exp expIfLeft=  ((ExpList)expIf).get(0);
		Exp expIfRight =  ((ExpList)expIf).get(1);
		Exp expThenLeft=  ((ExpList)expIf).get(0);
		Exp expThenRight =  ((ExpList)expIf).get(1);
		Exp expElseLeft=  ((ExpList)expIf).get(0);
		Exp expElseRight =  ((ExpList)expIf).get(1);

		return new Seq(new Cjump(exp.getRel().getCode(), expIfLeft, expIfRight,  z, f),
				new Seq(new Seq(new Label(z), new Cjump(thenExp.getRel().getCode(), expThenLeft, expThenRight,  t, f)),
						new Seq (new Label(f), new Cjump(elseExp.getRel().getCode(), expElseLeft, expElseRight, f, f))) );


	}
}
