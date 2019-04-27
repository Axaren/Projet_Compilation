package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Cjump;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocation;

public final class NodeIf extends Node {

	private Operation operation;

	public enum Operation {
		EQ(0),
		NE(1),
		LT(2),
		GT(3),
		LE(4),
		OR(5),
		GE(6),
		ULT(7),
		ULE(8),
		UGT(9),
		UGE(-1);

		public int getOp() {
			return op;
		}

		private int op;

		Operation(int op) {
			this.op = op;
		}
	}

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

		addStmList(this.getStmList(), new Cjump(operation.getOp(), getThenNode().getExpList().getHead(), getElseNode().getExpList().getHead(), new LabelLocation(), new LabelLocation() ));

	}
}
