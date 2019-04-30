package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.IntermediateCode;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Jump;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Label;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocation;

public final class NodeCaseList extends NodeExp {

	public NodeCaseList() {
		super();
	}

	@Override
	public boolean checksType() {
		super.checksType();
		return true;
	}


	@Override
	public NodeCaseList clone() {
		return new NodeCaseList();
	}

	@Override
	public IntermediateCode generateIntermediateCode() {
		for (Node child : elts) {
			child.generateIntermediateCode();
		}
		return new Jump(new LabelLocation());
	}

}
