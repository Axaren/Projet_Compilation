package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.IntermediateCode;
import ubordeaux.deptinfo.compilation.project.type.Type;

public final class NodeId extends NodeExp {

	protected String name;
	private boolean temp;

	public NodeId(String name, Type type) {
		super();
		this.name = name;
		this.type = type;
	}

	public void setTemp(boolean tmp) {
		this.temp = tpm;
	}

	public String toString() {
		return this.getClass().getSimpleName() + "#" + name + ':' + type + '#';
	}

	public String getName() {
		return name;
	}

	public boolean isTemp() {
		return temp;
	}

	@Override
	public boolean checksType() {
		super.checksType();
		return true;
	}

	protected String toDotNodeName() {
		return "NodeId " + name;
	}

	@Override
	public NodeId clone() {
		return new NodeId(name, type);
	}

	@Override
	public IntermediateCode generateIntermediateCode() {
		return super.generateIntermediateCode();
	}
}
