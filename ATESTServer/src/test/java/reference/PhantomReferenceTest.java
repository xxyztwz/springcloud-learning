package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.PhantomReference;
import java.util.LinkedList;

public class PhantomReferenceTest {
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

	public static void checkQueue() {
		Reference<? extends VeryBig> ref = null;
		while ((ref = rq.poll()) != null) {
			if (ref != null) {
				System.out.println("In queue: " + ((VeryBigPhantomReference) (ref)).id);
			}
		}
	}
	
	static VeryBig vb;

	public static void main(String args[]) {
		int size = 3;
		LinkedList<PhantomReference<VeryBig>> weakList = new LinkedList<PhantomReference<VeryBig>>();
		for (int i = 0; i < size; i++) {
			weakList.add(new VeryBigPhantomReference(new VeryBig("Phantom " + i), rq));
			System.out.println("Just created Phantom: " + weakList.getLast());

		}
		vb = weakList.get(0).get();//虚引用始终取不回来
		System.out.println(vb);
		while (true) {
			try {
				System.gc();
				if(vb!=null){
					System.out.println(vb.id);
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			checkQueue();
		}
	}
}

class VeryBigPhantomReference extends PhantomReference<VeryBig> {
	public String id;

	public VeryBigPhantomReference(VeryBig big, ReferenceQueue<VeryBig> rq) {
		super(big, rq);
		this.id = big.id;
	}

	protected void finalize() {
		System.out.println("Finalizing VeryBigPhantomReference " + id);
	}
}
