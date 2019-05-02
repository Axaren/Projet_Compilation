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
		Cjump expIf = (Cjump)getExpNode().generateIntermediateCode();
		IntermediateCode expThen = getThenNode().generateIntermediateCode();
		Stm expThenStm;
		Stm expElseStm;
		if (expThen instanceof Exp) expThenStm = new ExpStm((Exp)expThen);
		else expThenStm = (Stm) expThen;
		
		//Useful for the binary operation in the If
		NodeRel exp = (NodeRel) getExpNode();
		LabelLocation L1 = expIf.getIftrue();
		LabelLocation L2 = expIf.getIffalse();

		//In case of if-then-else
		if (getElseNode() != null) {
			IntermediateCode expElse = getElseNode().generateIntermediateCode();
			if (expElse instanceof Exp) expElseStm = new ExpStm((Exp)expElse);
			else expElseStm = (Stm) expElse;
			LabelLocation L3 = new LabelLocation();
			return new Seq(expIf, 
							new Seq(new Label(L1), 
								new Seq(expThenStm, 
									new Seq(new Label(L2), 
										new Seq(expElseStm, new Label(L3))))));
		}

		//In case of if-then only
		else{
			return new Seq(expIf, 
							new Seq(new Label(L1),
								new Seq(expThenStm, new Label(L2))));
		}
	}
}
