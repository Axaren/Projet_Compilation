package ubordeaux.deptinfo.compilation.project.intermediateCode;

public class Mem extends Exp {
	private Exp exp;

	public Mem(Exp exp) {
		super();
		this.exp = exp;
	}

	public Exp getExp() {
		return exp;
	}

	@Override
	public String toString() {
		return " Mem(" +
				exp.toString() +
				")";
	}
}
