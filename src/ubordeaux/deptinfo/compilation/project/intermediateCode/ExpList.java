package ubordeaux.deptinfo.compilation.project.intermediateCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpList implements IntermediateCode {
	private List<Exp> exps;

	public ExpList(Exp ... exps) {
		super();
		this.exps = new ArrayList<>();
		this.exps.addAll(Arrays.asList(exps));
	}

	public void add(Exp exp) {
		this.exps.add(exp);
	}

	public Exp get(int i) {
		return exps.get(i);
	}

	public void addExpList(Exp exp){
		this.exps.add(exp);
	}

	@Override
	public void canonicalTransformation() {
		// TODO Auto-generated method stub
		System.err.println("TODO: " + this.getClass().getSimpleName() + ".canonicalTransformation()()");
	}

	public String toString() {
		String s = "[";
		for (Exp exp : exps) {
			s += exp.toString() + ", ";
		}
		s += "]";
		return s;
	}

}
