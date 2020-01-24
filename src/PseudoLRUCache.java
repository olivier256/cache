/**
 * @link https://stackoverflow.com/questions/24409288/pseudo-least-recently-used-binary-tree
 */
public class PseudoLRUCache {
	private static final int[] MISS = new int[] {0, 0, 1, 1, 2, 3, 2, 3};

	private static final int[][] HITS = { {1, 6}, {1, 4}, {2, 1}, {2, 0}};

	int[] cache;

	int config;

	public PseudoLRUCache(final int n) {
		cache = new int[n];
		config = 0;
	}

	public void refer(final int x) {
		int i = indexOf(x, cache);
		if (i >= 0) {
			hit(i);
		} else {
			i = MISS[config];
			cache[i] = x;
			hit(i);
		}
	}

	private void hit(int i) {
		config = config & HITS[i][0] | HITS[i][1];
	}

	private int indexOf(final int n, final int[] cache2) {
		for (int i = 0; i < cache.length; i++) {
			if (cache2[i] == n) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i : cache) {
			sb.append(i + ", ");
		}
		sb.append(" - " + config);
		return sb.toString();
	}

	public static void main(final String[] args) {
		PseudoLRUCache cache = new PseudoLRUCache(4);

		refering(cache, 42);
		refering(cache, 13);
		refering(cache, 26);
		refering(cache, 37);
		refering(cache, 13);
		refering(cache, 13);
		refering(cache, 13);
		refering(cache, 49);
		refering(cache, 42); // Wrong eviction
		refering(cache, 42);
		refering(cache, 13);
		refering(cache, 26);
		refering(cache, 37); // Wrong eviction
		refering(cache, 15);
	}

	private static void refering(final PseudoLRUCache cache, final int a) {
		System.out.print("Refering " + a + " ... ");
		cache.refer(a);
		System.out.println(cache);
	}
}
