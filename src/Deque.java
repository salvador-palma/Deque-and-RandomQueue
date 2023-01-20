import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	public class Node{
		Item item;
		Node next, before;
	}
	Node first,last;
	int size;
	
	// construct an empty deque
	public Deque(){
		first=null; //reset ponteiros
		last=null;
		size=0;
	}
	// is the deque empty?
	public boolean isEmpty(){
		return size==0;
	}
	// return the number of items on the deque
	public int size(){
		return size;
	}
	// add the item to the front
	public void addFirst(Item item){
		if(item ==null){ throw new IllegalArgumentException("Item null");}
		Node n = new Node(); //criar node com o item pretendido
		n.item = item;
		
		
		if(size ==0){ //caso seja o primeiro da deque os dois ponteiros apontam pra ele
			last = n;
			first=n;
		}else{ //caso nao, aponta o ponteiro first para o novo node e reorganiza os ponteiros internos dos nodes
			Node t = first;
			first = n;
			//n.next = first;
			n.next = t;
			t.before = n;
		}
		size++;
		
	}
	// add the item to the back
	public void addLast(Item item){ //igual à funcao addFirst mas ao contrario
		if(item ==null){ throw new IllegalArgumentException("Item null");}
		Node n = new Node();
		n.item = item;
		
		
		if(size ==0){
			last = n;
			first=n;
		}else{
			Node t = last;
			last = n;
			//n.next = first;
			n.before = t;
			t.next = n;
		}
		size++;
		
	}
	// remove and return the item from the front
	public Item removeFirst(){
		Item ret = first.item; //obter ja o item pra devolver
		
		//first=first.before;
		first=first.next; //o ponteiro aponta já para o proximo node já que o atual sera removido
		
		if(size() == 1){
			last =null; //caso seja o ultimo node os ponteiros vao dar reset
			first=null;
		}else if(size() == 0){
			//throw new IllegalStateException("Empty Queue");
			throw new NoSuchElementException("Empty Queue");
		}else{
			first.before=null; //apagar a existencia do node removido
		}
		size--;
		return ret;
	}
	// remove and return the item from the back
	public Item removeLast(){//igual à funcao removeFirst mas ao contrario
		
		Item ret = last.item;
		
		//first=first.before;
		last=last.before;
		
		if(size() == 1){
			last =null;
			first=null;
		}else if(size() == 0){
			//throw new IllegalStateException("Empty Queue");
			throw new NoSuchElementException("Empty Queue");
		}else{
			last.next=null;
		}
		size--;
		return ret;
		
	}
	// return an iterator over items in order from front to back
	public Iterator<Item> iterator(){
		Iter i = new Iter();
		return i;
		
	}
	
	private class Iter implements Iterator<Item>{
		Node cur = first;//front to back
		//hasNext next remove
		public boolean hasNext(){
			return cur != null;
		}
		
		public Item next(){
			if(cur == null){
				//throw new IllegalStateException("End of Iterator");
				throw new NoSuchElementException("End of Iterator");
			}
			Item i = cur.item; 
			cur=cur.next; //cur++
			return i;
		}
		public void remove(){
			//nada
		}
		
		
	}
	// unit testing (required)
	public static void main(String[] args){
		Deque dq = new Deque();
		
		dq.addFirst(1);
		dq.addLast(2);
		dq.addLast(3);
		dq.addLast(4);
		dq.addLast(5);
		dq.addFirst(0);
		dq.addFirst(-1);
		dq.addFirst(-2);
		dq.addFirst(-3);
		dq.removeFirst();
		dq.removeLast();
		dq.removeLast();
		dq.addLast(20);
		System.out.println(dq.size());
		System.out.println(dq.isEmpty());
		Iterator iter = dq.iterator();
		while(iter.hasNext() == true){
			System.out.println(iter.next());
			
		}
		
		
	}
}