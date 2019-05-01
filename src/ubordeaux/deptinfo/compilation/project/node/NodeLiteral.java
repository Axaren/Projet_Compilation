package ubordeaux.deptinfo.compilation.project.node;

import ubordeaux.deptinfo.compilation.project.intermediateCode.Const;
import ubordeaux.deptinfo.compilation.project.intermediateCode.IntermediateCode;
import ubordeaux.deptinfo.compilation.project.intermediateCode.LabelLocation;
import ubordeaux.deptinfo.compilation.project.intermediateCode.Name;
import ubordeaux.deptinfo.compilation.project.type.Type;

public final class NodeLiteral extends NodeExp {

	private Object value;

	public NodeLiteral(Type type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}

	public String toString() {
		return this.getClass().getSimpleName() + '#' + value + ':' + type + '#';
	}

	@Override
	public boolean checksType() {
		super.checksType();
		return true;
	}

	public String toDotNodeName() {
		if (value != null)
			return "NodeLiteral " + value.toString();
		else 
			return "NodeLiteral null";
	}

	@Override
	public NodeLiteral clone() {
		return new NodeLiteral(type, value);
	}

	public IntermediateCode generateIntermediateCode() {
		if (value == null) return new Const(0); 

		if (value instanceof Integer) {
			return new Const((int)value);
		} else if (value instanceof Boolean) {
			if ((boolean)value == true)
				return new Const(1);
			else 
				return new Const(0);
		} else if (value instanceof String) {
			// a literal string a statically allocated : we return a reference to its label
			return new Name(new LabelLocation((String)value));
		} 

		System.err.println("Intermediate code error | Invalid value type "+value.getClass().getSimpleName());
		return null;
	}

}
