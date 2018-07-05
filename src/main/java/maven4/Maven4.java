package maven4;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class Maven4 {

	private static LoadingCache<Integer, PrimeNumber> cache;
	static {
		cache = CacheBuilder.newBuilder().maximumSize(100)
				.expireAfterAccess(10, TimeUnit.SECONDS)
				.expireAfterWrite(20, TimeUnit.SECONDS)
				.build(new CacheLoader<Integer, PrimeNumber>() {
					@Override
					public PrimeNumber load(Integer id) throws Exception {
						return getPrimeById(id);
					}
				});
	}

	public static LoadingCache<Integer, PrimeNumber> getLoadingCache() {
		return cache;
	}

	public static PrimeNumber getPrimeById(int id) {
		PrimeNumber pn = new PrimeNumber(id);
		return pn;
		
	}

}
class PrimeNumber{
	private int n;
	private ArrayList<Integer> arr;
	
	public PrimeNumber(int n) {
		this.n = n;
		this.arr = primes(n);
	}
	
	public static ArrayList<Integer> primes(int n) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			if(isPrime(i)) {
				arr.add(i);
			}
		}
		return arr;

	}
	
	public static boolean isPrime(int n) {
		for (int i = 2; i < Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public ArrayList<Integer> getArr() {
		return arr;
	}
	
}
