package ae.milch.restclient.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(ApiConcts.ARTICLES)
    Observable<Response> getArticles(@Query(ApiConcts.Fields.SOURCE) String source,
                                     @Query(ApiConcts.Fields.API_KEY) String apiKey);
}
