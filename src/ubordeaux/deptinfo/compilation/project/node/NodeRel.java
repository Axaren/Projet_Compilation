package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.*;
import ubordeaux.deptinfo.compilation.project.type.TypeBoolean;
import ubordeaux.deptinfo.compilation.project.type.TypeInt;

public class NodeRel extends NodeExp {

	public enum BinaryRel {
		EQ(0),
		NE(1),
		LT(2),
		GT(3),
		LE(4),
		GE(5);

		public int getCode() {
			return code;
		}

		private int code;

		BinaryRel(int code) {
			this.code = code;
		}

	}

	public BinaryRel getRel() {
		return rel;
	}

	private BinaryRel rel;

	// Relation binaire
	// f : E X F -> {0,1}
	public NodeRel(BinaryRel rel, Node op1, Node op2) {
		super(op1, op2);
		this.rel = rel;
		this.type = new TypeBoolean();
	}

	@Override
	public boolean checksType() {
		super.checksType();
		return (this.getOp1().getType() instanceof TypeInt) && (this.getOp2().getType() instanceof TypeInt);
	}

	public NodeExp getOp1() {
		return (NodeExp) this.get(0);
	};

	public NodeExp getOp2() {
		return (NodeExp) this.get(1);
	}

	@Override
	public NodeRel clone() {
		return new NodeRel(rel, (Node) getOp1().clone(), (Node) getOp2().clone());
	};

	@Override
	public IntermediateCode generateIntermediateCode() {
		LabelLocation l1 = new LabelLocation();
		LabelLocation l2 = new LabelLocation();
		Exp exp1 = (Exp)getOp1().generateIntermediateCode();
		Exp exp2 = (Exp)getOp2().generateIntermediateCode();
		return new Cjump(rel.getCode(), exp1, exp2, l1, l2);
		// return new ExpList((Exp)getOp1().generateIntermediateCode(), (Exp)getOp2().generateIntermediateCode());
	}
}
