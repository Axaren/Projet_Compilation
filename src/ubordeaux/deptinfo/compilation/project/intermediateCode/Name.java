package ubordeaux.deptinfo.compilation.project.intermediateCode;

public class Name extends Exp {
	private LabelLocation label;

	public Name(LabelLocation label) {
		super();
		this.label = label;
	}

	@Override
	public String toString() {
		return label.toString();
	}
}
