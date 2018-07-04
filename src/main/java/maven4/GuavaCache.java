package maven4;

import static spark.Spark.get;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.LoadingCache;

public class GuavaCache {
	private PrimeNumber getEmpUsingGuava(int id) throws ExecutionException {
		LoadingCache<Integer, PrimeNumber> empCache = Maven4.getLoadingCache();
		System.out.println("Cache size " + empCache.size() );
		return empCache.get(id);
	}

	public static void main(String[] args) {
		GuavaCache gv = new GuavaCache();
		try {
			get("/prime", "application/json", (request, response) -> {
				String n = request.queryParams("n");
				return gv.getEmpUsingGuava(Integer.parseInt(n)).getStr();
			});

			System.out.println("------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
