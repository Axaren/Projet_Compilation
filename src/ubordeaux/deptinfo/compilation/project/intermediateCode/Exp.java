package ubordeaux.deptinfo.compilation.project.intermediateCode;

public abstract class Exp implements IntermediateCode {
	
	@Override
	public void canonicalTransformation() {
		// TODO Auto-generated method stub
		System.err.println("TODO: " + this.getClass().getSimpleName() + ".canonicalTransformation()()");
	}

	@Override
	public String toString() {
		return "Exp{}" + getClass().getSimpleName() ;
	}

	protected String toDotNodeName() {
		return getClass().getSimpleName();
	}
}