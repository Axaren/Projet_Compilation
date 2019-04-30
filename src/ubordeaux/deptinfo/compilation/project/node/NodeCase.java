package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.IntermediateCode;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Label;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocation;

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

	@Override
	public NodeCase clone() {
		return new NodeCase(nameValue, (Node) this.getStm().clone());
	}

	private Node getStm() {
		return super.get(0);
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}

	@Override
	public IntermediateCode generateIntermediateCode() {
		getStm().generateIntermediateCode();
		LabelLocation lloc;
		if (defaultValue) {
			lloc = new LabelLocation();
		}
		else {
			lloc = new LabelLocation(nameValue);
		}
		addStmList();
		addStmList(new Label(lloc));
	}
}
