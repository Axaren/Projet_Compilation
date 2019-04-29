package ubordeaux.deptinfo.compilation.project.intermediateCode;

public class StmList {
	private Stm head;
	private StmList tail;

	public StmList(Stm head, StmList tail) {
		super();
		this.head = head;
		this.tail = tail;
	}

	public Stm getHead() {
		return head;
	}

	public Stm get(int i) {
		return this.get(i);
	}

	public void setHead(Stm head) {
		this.head = head;
	}

	public StmList getTail() {
		return tail;
	}

	public void setTail(StmList tail) {
		this.tail = tail;
	}
}
