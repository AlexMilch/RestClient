package ae.milch.restclient.domain;

import java.util.List;

import ae.milch.restclient.data.ApiService;
import ae.milch.restclient.data.NetworkModule;
import ae.milch.restclient.data.Source;
import ae.milch.restclient.data.SourceResponse;
import ae.milch.restclient.ui.source.SourceActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SourcesPresenter {

    private static final String LANGUAGE = "en";
    private final SourceActivity activity;

    public SourcesPresenter(SourceActivity activity) {
        this.activity = activity;
    }

    public void loadSources(String category) {
        ApiService apiService = new NetworkModule().createService();
        apiService.getSources(category, LANGUAGE)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convert)
                .subscribe(activity::onSourcesLoaded,
                        Throwable::printStackTrace);
    }

    private List<Source> convert(SourceResponse response) {
        return response.getSources();
    }
}
