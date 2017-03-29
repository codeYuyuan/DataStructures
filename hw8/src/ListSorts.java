/* ListSorts.java */

import list.*;

public class ListSorts {



  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
     // Replace the following line with your solution.
    
      LinkedQueue newQs = new LinkedQueue();
      Object obj;     
      while(q.size() !=0){
        try {
        	LinkedQueue newQ = new LinkedQueue();
			obj = q.dequeue();
			newQ.enqueue(obj);
	        newQs.enqueue((Object)newQ);
		} catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      } 
    
    return newQs;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    Comparable obj1, obj2;
    LinkedQueue newQ = new LinkedQueue();
    try {
      while(!q1.isEmpty() && !q2.isEmpty()){
		obj1 =(Comparable)(q1.front());
		obj2 =(Comparable)(q2.front());
	      if (obj1.compareTo(obj2)>0){
	        newQ.enqueue(q2.dequeue());        
	      }else{
	        newQ.enqueue(q1.dequeue());	               
	      } 
      }
	    if(q1.isEmpty())
	      newQ.append(q2);
	    else
	      newQ.append(q1);
     }catch (QueueEmptyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
          // Replace the following line with your solution.
    return newQ;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    Object obj;
    while(qIn.size()!=0){
      try {
		obj = qIn.dequeue();
		if(((Comparable)obj).compareTo(pivot)>0){
	        qLarge.enqueue(obj);
	      }else if(((Comparable)obj).compareTo(pivot)<0){
	        qSmall.enqueue(obj);
	      }else{
	        qEquals.enqueue(obj); 
	      }
	} catch (QueueEmptyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}      
    }
    // Your solution here.
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
	if(q.size()<2)
		return;
    LinkedQueue qs = makeQueueOfQueues(q);
    try{
    	while(qs.size() >1){
        	Object q1 = qs.dequeue();
        	Object q2 = qs.dequeue();
        	LinkedQueue qm = mergeSortedQueues((LinkedQueue)q1,(LinkedQueue)q2);
        	qs.enqueue(qm);
        }
        q.append((LinkedQueue)qs.dequeue());
    }catch(QueueEmptyException e){
    	e.printStackTrace();
    }
    
    // Your solution here.
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
	if(q.size()<2)
		return;
    int a = (int) (q.size() * Math.random());
    
    Object pivot = q.nth(a);
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    partition(q,(Comparable)pivot,qSmall,qEquals,qLarge);
    quickSort(qSmall);
    quickSort(qLarge);
    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);
    // Your solution here.
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    
    Timer stopWatch = new Timer();
    
    int[] arrayTest = {100,1000,10000,100000,1000000};
    
    for (int SORTSIZE : arrayTest){
    	q = makeRandom(SORTSIZE);
        stopWatch.start();
        mergeSort(q);
        stopWatch.stop();
        System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                           stopWatch.elapsed() + " msec.");

        stopWatch.reset();
        q = makeRandom(SORTSIZE);
        stopWatch.start();
        quickSort(q);
        stopWatch.stop();
        System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                           stopWatch.elapsed() + " msec.");
    }
    
    
  }

}
