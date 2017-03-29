/* Homework6Test.java */

import dict.*;
import list.*;

/**
 *  Initializes a hash table, then stocks it with random SimpleBoards.
 **/

public class Homework6Test {

  /**
   *  Generates a random 8 x 8 SimpleBoard.
   **/

  private static SimpleBoard randomBoard() {
    SimpleBoard board = new SimpleBoard();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
	double fval = Math.random() * 12;
	int value = (int) fval;
	board.setElementAt(x, y, value);
      }
    }
    return board;
  }

  /**
   *  Empties the given table, then inserts "numBoards" boards into the table.
   *  @param table is the hash table to be initialized.
   *  @param numBoards is the number of random boards to place in the table.
   **/

  public static void initTable(HashTableChained table, int numBoards) {
    table.makeEmpty();
    for (int i = 0; i < numBoards; i++) {
      table.insert(randomBoard(), new Integer(i));
    }
  }

  /**
   *  Creates a hash table and stores a certain number of SimpleBoards in it.
   *  The number is 100 by default, but you can specify any number at the
   *  command line.  For example:
   *
   *    java Homework6Test 12000
   **/

  public static void main(String[] args) {
    int numBoards;

    if (args.length == 0) {
      numBoards = 200;
    } else {
      numBoards = Integer.parseInt(args[0]);
    }
    HashTableChained table = new HashTableChained(numBoards);
    initTable(table, numBoards); 
    table.insert("1", "The first one");
    table.insert("2", "The second one");
    table.insert("3", "The third one");
    table.insert("what", "nani?");
    table.insert("the","Eh-heng");
    table.insert("hell!","impolite");
    int n = table.size();
    int N = table.hashTable.length;
    System.out.println("number of entries is: " + n);
    System.out.println("number table's buckets is: " + N);
    double loadFactor = (double)n/(double)N;
    System.out.println("load factor is : " + loadFactor);
    double collisionE = n - N + N * Math.pow((double)(1.0 - 1.0/N), (double)n);
    System.out.println("expected collision is: " + collisionE );
    System.out.println("Actual number of collision is: " + table.collision());
    String string=table.toString();
    System.out.println(string);

  }
}