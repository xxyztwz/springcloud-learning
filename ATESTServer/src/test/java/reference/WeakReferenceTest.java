package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

public class WeakReferenceTest {
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

	public static void checkQueue() {
		Reference<? extends VeryBig> ref = null;
		while ((ref = rq.poll()) != null) {
			if (ref != null) {
				System.out.println("In queue: " + ((VeryBigWeakReference) (ref)).id);
			}
		}
	}
	
	static VeryBig vb;

	public static void main(String args[]) {
		int size = 3;
		LinkedList<WeakReference<VeryBig>> weakList = new LinkedList<WeakReference<VeryBig>>();
		for (int i = 0; i < size; i++) {
			weakList.add(new VeryBigWeakReference(new VeryBig("Weak " + i), rq));
			System.out.println("Just created weak: " + weakList.getLast());

		}
		vb = weakList.get(0).get();//只要不触发GC，弱引用还是可以转换成强引用的

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

class VeryBig {
	public String id;
	// 占用空间,让线程进行回收
	long[] b = new long[10 * 1024];

	public VeryBig(String id) {
		this.id = id;
	}

	protected void finalize() {
		//WeakReferenceTest.vb = this;
		System.out.println("Finalizing VeryBig " + id);
	}
}

class VeryBigWeakReference extends WeakReference<VeryBig> {
	public String id;

	public VeryBigWeakReference(VeryBig big, ReferenceQueue<VeryBig> rq) {
		super(big, rq);
		this.id = big.id;
	}

	protected void finalize() {
		System.out.println("Finalizing VeryBigWeakReference " + id);
	}
}
