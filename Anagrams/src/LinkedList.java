import java.util.ArrayList;


public class LinkedList<E> {



	private static class LinkedNode<T> {
		private T item;
		private LinkedNode<T> next;
		private LinkedNode<T> previous;


		private LinkedNode(T value) {
			item = value;
			next = null;

		}



		private LinkedNode(T value, LinkedNode<T> prev) {
			item = value;
			previous = prev;
			next = null;
		}
	}
	// end of the LinkedNode definition



	protected LinkedNode<E> head;
	protected LinkedNode<E> tail;
	protected int size;



	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public boolean isEmpty(){
		return (size==0);
	}
	public E getHeadItem(){
		return head.item;
	}
	public E getItem(int index){
		if(size<index){return null;}

		LinkedNode<E> node = head;

		while(index!=1){
			node = node.next;
			index--;
		}
		return node.item;
	}


	public void add(E val){
		if(isEmpty()){
			LinkedNode<E> node = new LinkedNode<E>(val);
			head = node;
			tail = node;
			size++;
		}
		else{
			LinkedNode<E> node = new LinkedNode<E>(val,tail);
			tail.next = node;
			tail = node;
			size++;
		}
	}

	public void delete(int index){
		LinkedNode<E> temp = head;
		LinkedNode<E> temp2 = tail;
		if(index==1){
			head=temp.next;
			//	head.previous=null;
			size--;
			return;
		}
		if(index==size){
			tail = temp2.previous;
			tail.next=null;
			size--;
			return;
		}
		else{
			while(index!=1){
				temp = temp.next;
				index--;
			}
			temp.previous.next=temp.next;
			size--;
		}
	}

	public String toString() {

		LinkedNode<E> node = head;
		String result = "";
		while (node != null) {
			result+=node.item;	     
			node = node.next;
		}
		return result;
	}

	public static void main (String [] arguments) {
		LinkedList<String> ll = new LinkedList<String>();

		ll.add ("'o");
		ll.add ("cat");
		ll.add ("o'");
		ll.add ("tac");
		ll.add ("act");
		System.out.println("1: "+ll.toString());
		ll.delete(3);
		System.out.println("2: "+ll.toString());
		ll.delete(1);
		System.out.println("3: "+ll.toString());
		ll.delete(2);
		System.out.println("4: "+ll.toString());
		ll.delete(2);
		System.out.println("5: "+ll.toString());
		ll.delete(1);
		System.out.println("empty");
		System.out.println("6: "+ll.toString());
		WordList list = new WordList();
		ArrayList<String> strList2 = list.getList();
		
	}

}