package ae.milch.restclient.domain;

import java.util.List;

import ae.milch.restclient.data.ApiService;
import ae.milch.restclient.data.NetworkModule;
import ae.milch.restclient.data.Source;
import ae.milch.restclient.data.SourceResponse;
import ae.milch.restclient.ui.source.SourceView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SourcesPresenter {

    private static final String LANGUAGE = "en";
    private final SourceView view;

    public SourcesPresenter(SourceView view) {
        this.view = view;
    }

    public void loadSources(String category) {
        ApiService apiService = new NetworkModule().createService();
        apiService.getSources(category, LANGUAGE)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convert)
                .subscribe(view::onSourcesLoaded,
                        Throwable::printStackTrace);
    }

    private List<Source> convert(SourceResponse response) {
        return response.getSources();
    }
}
