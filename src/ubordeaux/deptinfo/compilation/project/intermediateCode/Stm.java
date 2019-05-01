package ubordeaux.deptinfo.compilation.project.intermediateCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Stm implements IntermediateCode {

	protected int uniqId;
	private static int staticUniqId;

	public Stm() {
		super();
		this.uniqId = Stm.staticUniqId++;
	}

	@Override
	public void canonicalTransformation() {
		// TODO Auto-generated method stub
		System.err.println("TODO: " + this.getClass().getSimpleName() + ".canonicalTransformation()()");
	}

	@Override
	public String toString() {
		return "" + getClass().getSimpleName();
	}

	public void toDot(StringBuffer stringBuffer) {
		stringBuffer.append("stm_" + this.uniqId + " [shape=\"ellipse\", label=\"" + toDotNodeName() + "\"];\n");
	}

	public final void toDot(String file) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write("digraph Ctree {\n");
			StringBuffer str = new StringBuffer();
			toDot(str);
			out.write(str.toString());
			out.write("}\n");
			out.close();
		} catch (IOException e) {
			System.err.println("ERROR: build dot");
		}
	}

	protected String toDotNodeName() {
		return getClass().getSimpleName();
	}
	
	public int getUniqId() {
		return this.uniqId;
	}

}
