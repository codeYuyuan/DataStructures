package list;

public class LockDList extends DList{
	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
		}
	public void lockNode(DListNode node){
		((LockDListNode)node).isLock = true;
	}

	public void remove(DListNode node){
		if (((LockDListNode)node).isLock != true){
			super.remove(node);
		}
	}
}