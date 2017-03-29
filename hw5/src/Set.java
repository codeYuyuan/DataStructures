/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
	 List l;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equals().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
	  l = new DList();
    // Your solution here.
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return l.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/

public void insert(Comparable c) {
	  if(cardinality()==0)
		  l.insertFront(c);
	  else{
		  try{
			  ListNode currentNode = l.front();
			  while (c.compareTo((Comparable)currentNode.item())> 0 &&currentNode != l.back())
				  currentNode = currentNode.next();
			  if(c.compareTo((Comparable)currentNode.item())< 0)
				  currentNode.insertBefore(c);
			  else if(currentNode == l.back()&& c.compareTo((Comparable)currentNode.item())> 0)
				  l.insertBack((Object)c);
		  }catch(InvalidNodeException e){
			  System.out.println("Insertion failed");
			  e.printStackTrace();
		  }
	  }
	  // Your solution here.
	}

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
 * @throws InvalidNodeException 
   **/
  public void union(Set s){
	  if(this.cardinality()!=0 && s.cardinality()!=0){
		  ListNode currentNodeThis = l.front();
		  ListNode currentNodeS = s.l.front();
		  Comparable myitem, sitem;	  	  
			try {
				while(currentNodeThis.isValidNode()&&currentNodeS.isValidNode()){
					myitem = (Comparable) currentNodeThis.item();
					sitem = (Comparable) currentNodeS.item();
					if(myitem.compareTo(sitem)==0){
						currentNodeThis = currentNodeThis.next();
						currentNodeS = currentNodeS.next();
					}
					else if(myitem.compareTo(sitem)<0){
						currentNodeThis = currentNodeThis.next();
					}
					else{
						currentNodeThis.insertBefore(sitem);
						currentNodeS = currentNodeS.next();
					}
				}
				while(currentNodeS.isValidNode()){
					l.insertBack(currentNodeS.item());
					currentNodeS = currentNodeS.next();
				}
			} catch (InvalidNodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	  }
	  
	  
    // Your solution here.
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
	  if(s.cardinality()==0 && this.cardinality()!=0){
		  this.l = s.l;
	  }
	  else if(this.cardinality()!=0){
		  ListNode currentNodeThis = l.front();
		  ListNode currentNodeS = s.l.front();
		  Comparable myitem, sitem;	  	
		  try{
			  while(currentNodeThis.isValidNode()&&currentNodeS.isValidNode()){
				  myitem = (Comparable) currentNodeThis.item();
				  sitem = (Comparable) currentNodeS.item();
				  
				  if(myitem.compareTo(sitem)==0){
					  currentNodeThis = currentNodeThis.next();
					  currentNodeS = currentNodeS.next();					  
				  }
				  else if (myitem.compareTo(sitem)<0){
					  currentNodeThis = currentNodeThis.next();
					  currentNodeThis.prev().remove();
				  }			  
				  else {
					  currentNodeS = currentNodeS.next();
				  }
			  }
			  while(currentNodeThis.isValidNode()){
				  ListNode temp = currentNodeThis.next();
				  currentNodeThis.remove();
				  currentNodeThis = temp;				  
			  }
			  
		  }catch(InvalidNodeException e){
			  System.out.println("Intersection Failed");
		  }
		  
	  }
    // Your solution here.
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
	  String s = "{ ";
	  ListNode currentNode = l.front();
	  try{
		  while(currentNode.isValidNode()){
			  s+= currentNode.item() + " ";
			  currentNode = currentNode.next();
		  }		  
	  }catch(InvalidNodeException e){
		  System.out.println("To String Failed");
		  
	  }
	  
	  return s+" }";
    // Replace the following line with your solution.
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
    Set s4 = new Set();
    System.out.println("Empty Set s4 = " + s4);
    
    System.out.println("s4.cardinality() = " + s4.cardinality());
    
    s4.union(s4);
    System.out.println("After s4.union(s4), s4 = " + s4);

    s4.intersect(s4);
    System.out.println("After s4.intersect(s4), s4 = " + s4);

    s3.union(s4);
    System.out.println("After s3.union(s4), s3 = " + s3);

    s3.intersect(s4);
    System.out.println("After s3.intersect(s4), s3 = " + s3);
    
    Set s5 = new Set();
    s5.insert(new Integer(4));
    s5.insert(new Integer(5));
    s5.insert(new Integer(6));
    s5.insert(new Integer(7));
    s5.insert(new Integer(8));
    s5.insert(new Integer(9));
    System.out.println("Set s5 = " + s5);
    System.out.println("Set s2 = " + s2);
    s5.intersect(s2);    
    System.out.println("After s5.intersect(s2), s5 = " + s5);
  }
}
