package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Exp;
import ubordeaux.deptinfo.compilation.project.intermediateCode.ExpStm;
import ubordeaux.deptinfo.compilation.project.intermediateCode.IntermediateCode;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Label;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocation;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Seq;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Stm;

public final class NodeCase extends Node {

	private String nameValue;
	private boolean defaultValue;
	
	public NodeCase(String nameValue, Node stm) {
		super(stm);
		this.nameValue = nameValue;
		this.defaultValue = false;
	}

	public NodeCase(Node stm) {
		this(null, stm);
		this.defaultValue = true;
	}

	@Override
	public boolean checksType() {
		super.checksType();
		return true;
	}

	public String getNameValue() {
		return nameValue;
	}

	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}

	@Override
	public NodeCase clone() {
		return new NodeCase(nameValue, (Node) this.getStm().clone());
	}

	public Node getStm() {
		return super.get(0);
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}

	@Override
	public IntermediateCode generateIntermediateCode() {
		IntermediateCode stmCode = getStm().generateIntermediateCode();
		LabelLocation lloc;
		if (defaultValue) {
			lloc = new LabelLocation();
		}
		else {
			lloc = new LabelLocation(nameValue);
		}

		if (stmCode instanceof Exp) {
			stmCode = new ExpStm((Exp) stmCode);
		}

		return new Seq(new Label(lloc), (Stm) stmCode);
	}
}
