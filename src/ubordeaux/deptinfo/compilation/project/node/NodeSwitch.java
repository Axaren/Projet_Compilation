package ubordeaux.deptinfo.compilation.project.node;

import java.util.ArrayList;
import java.util.List;
import ubordeaux.deptinfo.compilation.project.intermediateCode.*;

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
    IntermediateCode exp = getExp().generateIntermediateCode();
    NodeList caseList = (NodeList) getStm();
    int nbCaseList = caseList.size();

    LabelLocation testLocation = new LabelLocation();
    LabelLocation nextLocation = new LabelLocation();
    Label test = new Label(testLocation);
    Label next = new Label(nextLocation);

    Seq currentSeq = new Seq();
    Seq res = new Seq(new Jump(testLocation), currentSeq);
    List<LabelLocation> casesLocations = new ArrayList<>(nbCaseList);

    for (int i = 0; i < nbCaseList; i++) {
      NodeCase currentCase = (NodeCase) caseList.get(i);
      LabelLocation currentCaseLocation = new LabelLocation(currentCase.getNameValue());
      casesLocations.add(currentCaseLocation);
      Seq newCurrentSeq = new Seq((Stm) currentCase.generateIntermediateCode());
      currentSeq.setRight(new Seq(
          new Label(currentCaseLocation),
          newCurrentSeq));
      currentSeq = newCurrentSeq;
    }

    Seq testSeq = new Seq(test);
    currentSeq.setRight(testSeq);
    currentSeq = testSeq;

    for (int i = 0; i < nbCaseList-1; i++) {
      NodeCase currentcase = (NodeCase) caseList.get(i);
      LabelLocation falseLocation = new LabelLocation();
      Label falseLabel = new Label(falseLocation);
      Seq newCurrentSeq = new Seq(falseLabel);
      Seq CjumpSeq = new Seq(
          new Cjump(0, new Mem((Exp) exp), new Mem(new Name(new LabelLocation(currentcase.getNameValue()))),casesLocations.get(i),falseLocation),
          newCurrentSeq);
      currentSeq.setRight(CjumpSeq);
      currentSeq = newCurrentSeq;
    }

    currentSeq.setRight(next);

    return res;

	}
}
