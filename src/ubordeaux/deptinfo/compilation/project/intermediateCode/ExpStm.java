package ubordeaux.deptinfo.compilation.project.intermediateCode;

public class ExpStm extends Stm {

    private Exp exp;

    public ExpStm(Exp exp) {
        super();
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    } 

    @Override
    public String toString() {
        return "ExpStm(" +
                exp.toString() +
                ")";
    }

    public void toDot(StringBuffer stringBuffer) {
		super.toDot(stringBuffer);
		stringBuffer.append("exp1_"+this.uniqId+ " [shape=\"ellipse\", label=\"" + exp.toString() + "\"];\n");	
		stringBuffer.append("stm_"+this.uniqId+" -> exp1_" + this.getUniqId()+";\n");
	}

}
