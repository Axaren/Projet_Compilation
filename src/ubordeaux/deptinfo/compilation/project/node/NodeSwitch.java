package ubordeaux.deptinfo.compilation.project.node;

<<<<<<< HEAD
import ubordeaux.deptinfo.compilation.project.intermediateCode.*;

=======
import ubordeaux.deptinfo.compilation.project.intermediateCode.IntermediateCode;
>>>>>>> ef47619272b87caeeef466cb0a6cea49fe408f21

public final class NodeSwitch extends Node {

	public NodeSwitch(Node e, Node stm) {
		super(e, stm);
	}

	@Override
	public boolean checksType() {
		super.checksType();
		if (!get(0).checksType())
			return false;
		if (!get(1).checksType())
			return false;
		return true;
	}

	@Override
	public NodeSwitch clone() {
		return new NodeSwitch((Node) this.getExp().clone(), (Node) this.getStm().clone());
	}

	private Node getStm() {
		return this.get(1);
	}

	private Node getExp() {
		return this.get(0);
	}

	@Override
	public IntermediateCode generateIntermediateCode() {
		// getExp().generateIntermediateCode();
		// getStm().generateIntermediateCode();
		return null;
	}
}
