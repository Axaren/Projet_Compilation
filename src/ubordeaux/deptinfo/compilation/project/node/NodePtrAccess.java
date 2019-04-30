package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Jump;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Label;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocation;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocationList;
import ubordeaux.deptinfo.compilation.project.type.Type;
import ubordeaux.deptinfo.compilation.project.type.TypeComplex;
import ubordeaux.deptinfo.compilation.project.type.TypePointer;

public final class NodePtrAccess extends NodeExp {

	// e = NodeExp(TypePointer(\tau))
	public NodePtrAccess(NodeExp e) {
		super(e);
		if (e.getType() instanceof TypeComplex) {
			Type t = (TypeComplex) e.getType();
			if (t != null && (t instanceof TypePointer)) {
				Type tr = ((TypePointer) t).get(0);
				if (tr != null)
					this.type = tr;
			} else {
				System.err.println("Cannot dereference a non-pointer"+e.getType());
			}
		} else {
			System.err.println("Cannot dereference a non-pointer "+e.getType());
		}
	}

	@Override
	public boolean checksType() {
		super.checksType();
		return true;
	}

	@Override
	public NodePtrAccess clone() {
		return new NodePtrAccess((NodeExp) this.get(0).clone());
	}


	@Override
	public void generateIntermediateCode() {
		this.get(0).generateIntermediateCode();

		addStmList(new Jump(this.get(0).getExpList().getHead(), new LabelLocationList(new LabelLocation(), null)));
	}
}
