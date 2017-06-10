package ae.milch.restclient.domain;

import java.util.List;

import ae.milch.restclient.data.ApiConcts;
import ae.milch.restclient.data.ApiService;
import ae.milch.restclient.data.Article;
import ae.milch.restclient.data.Response;
import ae.milch.restclient.ui.MainActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticlesPresenter {

    private final MainActivity activity;

    public ArticlesPresenter(MainActivity activity) {
        this.activity = activity;
    }

    public void loadArticles(String domain) {
        ApiService apiService = createService();
        apiService.getArticles(domain, ApiConcts.API_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convert)
                .subscribe(
                        activity::initAdapter,
                        Throwable::printStackTrace);
    }

    private List<Article> convert(Response response) {
        return response.getArticles();
    }

    private ApiService createService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConcts.ENDPOINT)
                .build();
        return retrofit.create(ApiService.class);
    }
}
