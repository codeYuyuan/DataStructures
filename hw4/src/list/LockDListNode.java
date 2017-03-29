package list;

public class LockDListNode extends DListNode{
	public boolean isLock;
	LockDListNode(Object i, DListNode p, DListNode n){
		super(i,p,n);
		isLock = false;
	}
	protected LockDListNode newNode(Object item, DListNode prev, DListNode next){
		return new LockDListNode(item, prev, next);
	}
}