package learn;

import java.util.ArrayList;

class Book {
    int id;
    String name, author, publisher;
    int quantity;

    public Book(int id, String name, String author, String publisher, int quantity) {
	this.id = id;
	this.name = name;
	this.author = author;
	this.publisher = publisher;
	this.quantity = quantity;
    }
}

public class ArrayListExample {
    public static void main(String[] args) {
	/*
	 * //Creating list of Books List<Book> list=new ArrayList<Book>(); //Creating
	 * Books Book b1=new Book(101,"Let us C","Yashwant Kanetkar","BPB",8); Book
	 * b2=new
	 * Book(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);
	 * Book b3=new Book(103,"Operating System","Galvin","Wiley",6); //Adding Books
	 * to list list.add(b1); list.add(b2); list.add(b3); Iterator
	 * itr=list.iterator();
	 */

	ArrayList<String> al = new ArrayList<String>();
	al.add("Ravi");
	al.add("Vijay");
	al.add("Ajay");
	ArrayList<String> al2 = new ArrayList<String>();
	al2.add("Ravi");
	al2.add("Hanumat");
	al.retainAll(al2);
	System.out.println("iterating the elements after retaining the elements of al2...");
	for (Object a : al2) {
	    System.out.println(a);

	}
	/*
	 * Iterator<String> itr=al.iterator(); while(itr.hasNext()){
	 * System.out.println(itr.next()); }
	 */
    }
}
