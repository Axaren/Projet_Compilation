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

		//Apply intermediate code to If, Then and Else expressions
		IntermediateCode expIf = getExpNode().generateIntermediateCode();
		IntermediateCode expThen = getThenNode().generateIntermediateCode();
		IntermediateCode expElse = getElseNode().generateIntermediateCode();

		//Useful for the binary operation in the If
		NodeRel exp = (NodeRel) getExpNode();
		Exp expIfLeft = ((ExpList)expIf).get(0);
		Exp expIfRight = ((ExpList)expIf).get(1);

		//LabelLocation after a then
		LabelLocation thenLabel = new LabelLocation();
		//LabelLocation after an else
		LabelLocation elseLabel = new LabelLocation();
		//final label location
		LabelLocation f = new LabelLocation();

		//In case of if-then-else
		if (getElseNode() != null) {
			return new Seq(new Cjump(exp.getRel().getCode(), expIfLeft, expIfRight, thenLabel, elseLabel),
					new Seq(new Seq(new Label(thenLabel), new Jump((Exp)expThen, new LabelLocationList(f, null))),
							new Seq(new Label(elseLabel), new Jump((Exp)expElse, new LabelLocationList(f, null)))));
		}

		//In case of if-then only
		else{
			return new Seq(new Jump((Exp)expIf, new LabelLocationList(thenLabel, null)),
					          new Seq(new Label(thenLabel), new Jump((Exp)expThen, new LabelLocationList(f, null))));
		}
	}
}
