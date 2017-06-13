package ae.milch.restclient.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(ApiConsts.Api.ARTICLES)
    Observable<ArticleResponse> getArticles(@Query(ApiConsts.Fields.SOURCE) String source,
                                            @Query(ApiConsts.Fields.API_KEY) String apiKey);

    @GET(ApiConsts.Api.SOURCES)
    Observable<SourceResponse> getSources(@Query(ApiConsts.Fields.LANGUAGE) String language);
}
