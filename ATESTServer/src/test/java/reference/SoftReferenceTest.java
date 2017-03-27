package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedList;

public class SoftReferenceTest {
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();
	private static LinkedList<SoftReference<VeryBig>> softList = new LinkedList<SoftReference<VeryBig>>();
	
	public static void checkQueue() throws InterruptedException {
		Reference<? extends VeryBig> ref = null;
		int i = 0;
		while ((ref = rq.remove()) != null) {//ReferenceQueue.remove()方法是阻塞式的
			if (ref != null) {
				i++;
				if(i%9==8){
					System.out.println("In queue: " + i);
				}
			}
		}

	}
	
	
	public static void main(String[] args) throws InterruptedException {
		CreateThread ct = new CreateThread();
		ct.start();
		checkQueue();
	}
	
	static class CreateThread extends Thread{

		@Override
		public void run() {
			int j=0;
			while(true){
				for (int i = 0; i < 10; i++) {
					softList.add(new VeryBigSoftReference(new VeryBig("Weak " + j+ i), rq));
				}
				System.out.println("Just created weak: " + softList.getLast().get().id);
				j++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}

class VeryBigSoftReference extends SoftReference<VeryBig> {
	public String id;

	public VeryBigSoftReference(VeryBig big, ReferenceQueue<VeryBig> rq) {
		super(big, rq);
		this.id = big.id;
	}
}
