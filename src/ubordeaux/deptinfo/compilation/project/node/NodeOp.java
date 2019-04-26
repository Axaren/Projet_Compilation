package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Binop;

public class NodeOp extends NodeExp {

	protected String name;

	// Opération binaire
	// f : E X F -> F
	public NodeOp(String name, NodeExp op1, NodeExp op2) {
		super(op1, op2);
		this.name = name;
		// le type d'un opérateur 
		NodeExp exprFct = (NodeExp) this.get(1);
		type = exprFct.type;
	}

	public NodeOp(String name, NodeExp op) {
		super(op);
		this.name = name;
		// le type d'un opérateur 
		NodeExp exprFct = (NodeExp) this.get(0);
		type = exprFct.type;
	}
	
	@Override
	public boolean checksType() {
		super.checksType();
		if ((super.size() > 1) && getOp2()!=null && !this.getOp1().getType().equals(this.getOp2().getType()))
			return false;
		return true;
	}

	private NodeExp getOp1() {
		return (NodeExp) this.get(0);
	};
	
	private NodeExp getOp2() {
		return (NodeExp) this.get(1);
	}

	@Override
	public NodeOp clone() {
		if (this.size()==1)
			return new NodeOp(name, (NodeExp) getOp1().clone());
		else if (this.size()==2)
			return new NodeOp(name, (NodeExp) getOp1().clone(), (NodeExp) getOp2().clone());
		return null;
		};

	public final static int PLUS = 0, MINUS = 1, MUL = 2, DIV = 3, AND = 4, OR = 5, LSHIFT = 6, RSHIFT = 7, ARSHIFT = 8,
			XOR = 9;

	@Override
	public void generateIntermediateCode() {
		getOp1().generateIntermediateCode();
		getOp2().generateIntermediateCode();


		switch (name){
			case "PLUS": super.setExpCode(new Binop(PLUS, getOp1().getExpCode(), getOp2().getExpCode())); break;
			case "MINUS": super.setExpCode(new Binop(MINUS, getOp1().getExpCode(), getOp2().getExpCode())); break;
			case "TIMES": super.setExpCode(new Binop(MUL, getOp1().getExpCode(), getOp2().getExpCode())); break;
			case "DIV": super.setExpCode(new Binop(DIV, getOp1().getExpCode(), getOp2().getExpCode())); break;
		}

	}
}
