package ubordeaux.deptinfo.compilation.project.intermediateCode;

public class Move extends Stm {
	private Exp dst, src;

	public Move(Exp dst, Exp src) {
		super();
		this.dst = dst;
		this.src = src;
	}

	@Override
	public String toString() {
		return " Move(" +
				 dst +
				"," + src +
				")";
	}

	public void toDot(StringBuffer stringBuffer) {
		super.toDot(stringBuffer);
		stringBuffer.append("exp1_"+this.uniqId+ " [shape=\"ellipse\", label=\"" + dst.toString() + "\"];\n");	
		stringBuffer.append("exp2_"+this.uniqId+ " [shape=\"ellipse\", label=\"" + src.toString() + "\"];\n");		
		stringBuffer.append("stm_"+this.uniqId+" -> exp1_" + this.getUniqId()+";\n");
		stringBuffer.append("stm_"+this.uniqId+" -> exp2_" + this.getUniqId()+";\n");
	}
}
