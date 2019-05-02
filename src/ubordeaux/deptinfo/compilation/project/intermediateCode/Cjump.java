package ubordeaux.deptinfo.compilation.project.intermediateCode;

public class Cjump extends Stm {
	private int relop;
	private Exp left, right;
	private LabelLocation iftrue, iffalse;

	public Cjump(int relop, Exp left, Exp right, LabelLocation iftrue, LabelLocation iffalse) {
		super();
		this.relop = relop;
		this.left = left;
		this.right = right;
		this.iftrue = iftrue;
		this.iffalse = iffalse;
	}

	public final static int EQ = 0, NE = 1, LT = 2, GT = 3, LE = 4, GE = 5, ULT = 6, ULE = 7, UGT = 8, UGE = 9;

	public static int notRel(int relop) {
		switch (relop) {
		case EQ:
			return NE;
		case NE:
			return EQ;
		case LT:
			return GE;
		case GE:
			return LT;
		case GT:
			return LE;
		case LE:
			return GT;
		case ULT:
			return UGE;
		case UGE:
			return ULT;
		case UGT:
			return ULE;
		case ULE:
			return UGT;
		default:
			throw new Error("bad relop in CJUMP.notRel");
		}
	}
	
	public LabelLocation getIftrue() {
		return iftrue;
	}

	public LabelLocation getIffalse() {
		return iffalse;
	}

	@Override
	public String toString() {
		return " Cjump(" +
				"relop=" + relop +
				", left=" + left +
				", right=" + right +
				", iftrue=" + iftrue +
				", iffalse=" + iffalse +
				")";
	}
	
	public void toDot(StringBuffer stringBuffer) {
		super.toDot(stringBuffer);
		stringBuffer.append("exp1_"+this.uniqId+ " [shape=\"ellipse\", label=\"" + left.toString() + "\"];\n");	
		stringBuffer.append("exp2_"+this.uniqId+ " [shape=\"ellipse\", label=\"" + right.toString() + "\"];\n");		
		stringBuffer.append("exp3_"+this.uniqId+ " [shape=\"ellipse\", label=\"" + iftrue.toString() + "\"];\n");		
		stringBuffer.append("exp4_"+this.uniqId+ " [shape=\"ellipse\", label=\"" + iffalse.toString() + "\"];\n");				
		stringBuffer.append("stm_"+this.uniqId+" -> exp1_" + this.getUniqId()+";\n");
		stringBuffer.append("stm_"+this.uniqId+" -> exp2_" + this.getUniqId()+";\n");
		stringBuffer.append("stm_"+this.uniqId+" -> exp3_" + this.getUniqId()+";\n");
		stringBuffer.append("stm_"+this.uniqId+" -> exp4_" + this.getUniqId()+";\n");
	}


	protected String toDotNodeName() {
		return getClass().getSimpleName() + " rel="+relop;
	}
}
