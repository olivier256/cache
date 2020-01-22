import java.util.Iterator;
import java.util.LinkedList;

public class LRUCache {
	private final LinkedList<Integer> cache;

	private final int maxCacheSize;

	public LRUCache(final int n) {
		cache = new LinkedList<>();
		maxCacheSize = n;
	}

	public void refer(final Integer x) {
		int index = cache.indexOf(x);	// O(n)
		final boolean cacheMiss = index < 0;
		if (cacheMiss) {
			if (cache.size() < maxCacheSize) {
				// Not max size yet. We don't have to remove anything before pushing
				cache.push(x);	// O(1)
			} else {
				// Already max size. We have to remove the last entry before pushing
				final int indexOfLastEntry = cache.size() - 1;
				cache.remove(indexOfLastEntry);	// O(1)
				cache.push(x);	// O(1)
			}
		} else {
			// Cache hit. We move the entry at front
			cache.remove(index);	// O(1)
			cache.push(x);	// O(1)
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> itr = cache.iterator();
		while (itr.hasNext()) {
			sb.append(itr.next() + " ");
		}
		return sb.toString();
	}

	public static void main(final String[] args) {
		LRUCache ca = new LRUCache(4);
		ca.refer(1);
		System.out.println("Refering 1 : " + ca);
		ca.refer(2);
		System.out.println("Refering 2 : " + ca);
		ca.refer(3);
		System.out.println("Refering 3 : " + ca);
		ca.refer(1);
		System.out.println("Refering 1 : " + ca);
		ca.refer(4);
		System.out.println("Refering 4 : " + ca);
		ca.refer(3);
		System.out.println("Refering 3 : " + ca);
		ca.refer(4);
		System.out.println("Refering 4 : " + ca);
		ca.refer(2);
		System.out.println("Refering 2 : " + ca);
		ca.refer(5);
		System.out.println("Refering 5 : " + ca);
	}
}
