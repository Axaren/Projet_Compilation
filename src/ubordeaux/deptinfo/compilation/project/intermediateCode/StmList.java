package ubordeaux.deptinfo.compilation.project.intermediateCode;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class StmList {
	private List<Stm> stms;

	public StmList(Stm ... stms) {
		super();
		this.stms = new ArrayList<Stm>();
		this.stms.addAll(Arrays.asList(stms));
	}

	public void add(Stm stm) {
		this.stms.add(stm);
	}

	public Stm get(int i) {
		return this.stms.get(i);
	}

}
