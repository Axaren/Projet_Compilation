package ubordeaux.deptinfo.compilation.project.intermediateCode;

public class Const extends Exp {
	private Object value;

	public Const(Object value) {
		super();
		this.value = value;
	}

	@Override
	public String toString() {
		return " Const(" +
				value +
				")";
	}
}
