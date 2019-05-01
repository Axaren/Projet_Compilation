package ubordeaux.deptinfo.compilation.project.intermediateCode;

public class Seq extends Stm {
	private Stm left, right;

	public Seq(Stm left, Stm right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		if (this.left == null)
			return "Seq(vide...)";
		if (this.right == null)
			return "Seq( " + this.left.toString() + " )";
		return "Seq( " + this.left.toString() + ", " + this.right.toString() + " )";
	}

}
