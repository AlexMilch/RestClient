package ae.milch.restclient.domain;

import java.util.List;

import ae.milch.restclient.data.ApiConsts;
import ae.milch.restclient.data.ApiService;
import ae.milch.restclient.data.Source;
import ae.milch.restclient.data.SourceResponse;
import ae.milch.restclient.ui.SourceActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SourcesPresenter {

    private final SourceActivity activity;

    public SourcesPresenter(SourceActivity activity){
        this.activity = activity;
    }

    public void loadSources(){
        ApiService apiService = createService();
        apiService.getSources("en")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convert)
                .subscribe(
                        activity::onSourcesLoaded,
                        Throwable::printStackTrace);
    }

    private List<Source> convert(SourceResponse response) {
        return response.getSources();
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
                .baseUrl(ApiConsts.ENDPOINT)
                .build();
        return retrofit.create(ApiService.class);
    }
}
