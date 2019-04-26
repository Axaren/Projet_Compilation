package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Cjump;

public final class NodeIf extends Node {

	public final static int EQ = 0, NE = 1, LT = 2, GT = 3, LE = 4, GE = 5, ULT = 6, ULE = 7, UGT = 8, UGE = 9;

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
	public void generateIntermediateCode() {
		getExpNode().generateIntermediateCode();
		getThenNode().generateIntermediateCode();
		getElseNode().generateIntermediateCode();

		NodeRel exp = (NodeRel) getExpNode();

		switch (exp.name) {

			case "EQ":
				setStmCode(new Cjump(EQ, getThenNode().getExpCode(), getElseNode().getExpCode(), getThenNode().getLabelLocation(), getElseNode().getLabelLocation()));
				break;

			case "NE":
				setStmCode(new Cjump(NE, getThenNode().getExpCode(), getElseNode().getExpCode(), getThenNode().getLabelLocation(), getElseNode().getLabelLocation()));
				break;

			case "LT":
				setStmCode(new Cjump(LT, getThenNode().getExpCode(), getElseNode().getExpCode(), getThenNode().getLabelLocation(), getElseNode().getLabelLocation()));
				break;

			case "GT":
				setStmCode(new Cjump(GT, getThenNode().getExpCode(), getElseNode().getExpCode(), getThenNode().getLabelLocation(), getElseNode().getLabelLocation()));
				break;
		}
	}
}
