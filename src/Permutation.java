import java.util.Iterator;
import java.util.Scanner;

public class Permutation {
	public static void main(String[] args){
		int k = Integer.parseInt(args[0]);
		Scanner sc = new Scanner(System.in); //usarei scanner pq nao me disponibilizaram a classe StdIn
		
		String[] str = sc.nextLine().split(" ");
		
		RandomizedQueue rq = new RandomizedQueue(str.length);
		for(int i = 0; i!= str.length; i++){
			rq.enqueue(str[i]);
		}
		
		
		Iterator iter = rq.iterator();
		int i = 0;
		while(iter.hasNext() == true && i != k){
			System.out.println(iter.next());
			i++;
		}
		
	}
}
