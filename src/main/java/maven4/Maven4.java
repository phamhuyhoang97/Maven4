package maven4;

import static spark.Spark.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
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
	private String str;
	public PrimeNumber(int n) {
		this.n = n;
		this.str = primes(n);
	}
	
	public String primes(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; i++) {
			if(isPrime(i)) {
				sb.append(" ");
				sb.append(i);
			}
		}
		return sb.toString();

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
	public String getStr() {
		return str;
	}
	
}
