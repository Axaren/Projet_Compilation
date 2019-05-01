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

}
