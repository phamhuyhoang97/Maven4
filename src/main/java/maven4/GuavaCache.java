package maven4;

import static spark.Spark.get;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.LoadingCache;

public class GuavaCache {
	/* Chinh sua hop nhat giua server */
	private PrimeNumber getPrimeUsingGuava(int id) throws ExecutionException {
		LoadingCache<Integer, PrimeNumber> PrimeCache = Maven4.getLoadingCache();
		System.out.println("Cache size " + PrimeCache.size());
		return PrimeCache.get(id);
	}

	public static void main(String[] args) {
		GuavaCache gv = new GuavaCache();
		try {
			get("/prime", "application/json", (request, response) -> {
				String n = request.queryParams("n");
				return gv.getPrimeUsingGuava(Integer.parseInt(n)).getArr();
			});

			System.out.println("------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
