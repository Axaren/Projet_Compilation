package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Binop;
import ubordeaux.deptinfo.compilation.project.intermediateCode.IntermediateCode;
import ubordeaux.deptinfo.compilation.project.type.*;

public class NodeOp extends NodeExp {

	private Operation operation;

	public enum Operation {
		PLUS(0),
		MINUS(1),
		MUL(2),
		DIV(3),
		AND(4),
		OR(5),
		LSHIFT(6),
		RSHIFT(7),
		ARSHIFT(8),
		XOR(9),
		NOT(-1);

		public int getOp() {
			return op;
		}

		private int op;

		Operation(int op) {
			this.op = op;
		}
	}

	// Opération binaire
	// f : E X F -> F
	public NodeOp(Operation operation, NodeExp op1, NodeExp op2) {
		super(op1, op2);
		this.operation = operation;
		// le type d'un opérateur 
		NodeExp exprFct = (NodeExp) this.get(1);
		type = exprFct.type;
	}

	public NodeOp(Operation operation, NodeExp op) {
		super(op);
		this.operation = operation;
		// le type d'un opérateur
		NodeExp exprFct = (NodeExp) this.get(0);
		type = exprFct.type;
	}
	
	@Override
	public boolean checksType() {
		super.checksType();
		if ((super.size() > 1) && getOp2() != null && !(this.getOp1().getType().equals(this.getOp2().getType()))) {
			System.out.println("Both operands need to be of the same type");
			return false;
		}

		switch (operation) {
			case PLUS: MINUS: DIV: LSHIFT: RSHIFT: ARSHIFT: XOR: 
				if (!(this.getOp1().getType() instanceof TypeInt)) {
					System.err.println("Operation cannot be performed on non-integers");
					return false;
				}
				break;
			case AND: OR: NOT:
				if (!(this.getOp1().getType() instanceof TypeBoolean)) {
					System.err.println("Operation cannot be performed on non-booleans");
					return false;
				}
				break;
			default: break;
		}
		return true;
	}

	private NodeExp getOp1() {
		return (NodeExp) this.get(0);
	}

	private NodeExp getOp2() {
		return (NodeExp) this.get(1);
	}

	@Override
	public NodeOp clone() {
		if (this.size()==1)
			return new NodeOp(operation, (NodeExp) getOp1().clone());
		else if (this.size()==2)
			return new NodeOp(operation, (NodeExp) getOp1().clone(), (NodeExp) getOp2().clone());
		return null;
		}

	@Override
	public IntermediateCode generateIntermediateCode() {

		if (getOp2() != null)
			return (new Binop(operation.getOp(), (Exp)getOp1().generateIntermediateCode(), (Exp)getOp2().generateIntermediateCode()));
		else {
			return (new Binop(operation.getOp(), new Const(0), (Exp)getOp1().generateIntermediateCode()));
		}

	}
}
