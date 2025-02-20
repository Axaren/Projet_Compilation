package ubordeaux.deptinfo.compilation.project.intermediateCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Seq extends Stm {

	public void setLeft(Stm left) {
		this.left = left;
	}

	public void setRight(Stm right) {
		this.right = right;
	}

	private Stm left, right;

	public Seq(Stm left, Stm right) {
		super();
		this.left = left;
		this.right = right;
	}

	public Seq(Stm left) {
	  super();
	  this.left = left;
  }

  public Seq() {
	  super();
  }

	@Override
	public String toString() {
		if (this.left == null)
			return "Seq(vide...)";
		if (this.right == null)
			return "Seq( " + this.left.toString() + " )";
		return "Seq( " + this.left.toString() + ", " + this.right.toString() + " )";
	}

	public void toDot(StringBuffer stringBuffer) {
		super.toDot(stringBuffer);
		if (this.left != null) { 
			this.left.toDot(stringBuffer);
			if (this.left != null) stringBuffer.append("stm_"+this.uniqId+" -> stm_" + this.left.getUniqId()+";\n");
		}
		if (this.right != null) {
			this.right.toDot(stringBuffer);
			stringBuffer.append("stm_"+this.uniqId+" -> stm_" + this.right.getUniqId()+";\n");
		}
	}

	public Stm getLeft() {
		return left;
	}


	public Stm getRight() {
		return right;
	}


}
