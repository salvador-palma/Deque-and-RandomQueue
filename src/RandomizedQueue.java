import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
	
	Item[] rq;
	int size;
	int minSize;
 // construct an empty randomized queue
 public RandomizedQueue(int min){
	 //rq = new Item[1];
	 rq = (Item[]) new Object[8];
	 size=0;
	 minSize = min;
 }
 
 
 // is the randomized queue empty?
 public boolean isEmpty(){
	 return size == 0;
	 
 }
 // return the number of items on the randomized queue
 public int size(){
	 return size;
	 
 }
 // add the item
 public void enqueue(Item item){
	 if(item ==null){ throw new IllegalArgumentException("Item null");}
	 
	 //resize se não couberem mais items
	 if(rq.length == size){
		Item[] rq2 = (Item[]) new Object[size*2];
		for(int i = 0; i != size; i++){
			rq2[i] = rq[i];
		}
		
		rq = rq2;
	 }
	 
	 
	 //enqueue basico
	 rq[size] = item;
	 size++;
	 
 }
 // remove and return a random item
 public Item dequeue(){
	 if(size==0){
		 throw new NoSuchElementException("Empty Queue");
	 }
	 Item ret=null;
	 int rd=0;
	 //obter um valor random que nao tenha o item respetivo a null
	 int check = 0;
	 while(check == 0){
		 rd = (int)(Math.random() * size);
		 if(rq[rd] != null){
			check = 1; 
			ret = rq[rd];
		 }
		 
	 }
	 
	 rq[rd] = rq[size-1]; //passar o ultimo elemento pra o removido já que n interessa a ordem
	 rq[size-1] = null; //apagar o ultimo
	 size--;
	 
	 //resizes se apenas se tiver a usar 1/4 do array (sendo sempre maior q 8)
	 if(rq.length > minSize && size <= rq.length/4){
		 Item[] rq2 = (Item[]) new Object[rq.length/2];
		 for(int i=0; i!=size; i++) {
				rq2[i] = rq[i];
		 }
		 rq = rq2;
	 }
	 return ret;
	 
 }
 // return a random item (but do not remove it)
 public Item sample(){ //parecido ao dequeue mas ainda mais facil
	 if(size==0){
		 throw new NoSuchElementException("Empty Queue");
	 }
	 Item ret=null;
	 int rd=0;
	 
	 int check = 0;
	 while(check == 0){
		 rd = (int)(Math.random() * size);
		 if(rq[rd] != null){
			check = 1; 
			ret = rq[rd];
		 }
		 
	 }
	 return ret;
	 
 }
 // return an independent iterator over items in random order
 public Iterator<Item> iterator(){
	 Iter i = new Iter();
	 return i;
	 
 }
 private class Iter implements Iterator<Item>{
		//Node cur = first;//front to back
		//hasNext next remove
	 	public Item[] FYS(Item[] a, int n){ //Fisher Yattes shuffle
	    	
	    	for (int i = n-1; i > 0; i--){
	    		int r = (int)(Math.random()* (i+1));
	    		Item t = a[i];
	    		a[i] = a[r];
	    		a[r] = t;
	    	}
	    	/*
	    	for (int i = 0; i != a.length; i++){
	    		System.out.println(a[i]);
	    	}*/
	    	return a;
	    }
	    Item[] randomized = FYS(rq, size);
	    int i =0;
	    
		public boolean hasNext(){
			return i != randomized.length && randomized[i] != null;
		}
		
		public Item next(){
			if(i >= randomized.length){
				//throw new IllegalStateException("End of Iterator");
				throw new NoSuchElementException("End of Iterator");
			}
			Item it = randomized[i]; 
			i++; //cur++
			return it;
		}
		public void remove(){
			//nada
		}
		
		
}
 // unit testing (required)
 public static void main(String[] args){
	 RandomizedQueue q = new RandomizedQueue(8);
	 q.enqueue(1);
	 q.enqueue(2);
	 q.enqueue(3);
	 q.enqueue(4);
	 q.enqueue(5);
	 q.dequeue();
	 System.out.println(q.sample());
	 System.out.println(q.isEmpty());
	 System.out.println(q.size());
	 Iterator iter = q.iterator();
		while(iter.hasNext() == true){
			System.out.println(iter.next());
			
	}
	
	 
 }
}
