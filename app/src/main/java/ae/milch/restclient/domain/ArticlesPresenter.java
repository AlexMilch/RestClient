package ae.milch.restclient.domain;

import java.util.List;

import ae.milch.restclient.data.ApiConsts;
import ae.milch.restclient.data.ApiService;
import ae.milch.restclient.data.Article;
import ae.milch.restclient.data.ArticleResponse;
import ae.milch.restclient.data.NetworkModule;
import ae.milch.restclient.ui.article.ArticleView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticlesPresenter {

    private final ArticleView view;

    public ArticlesPresenter(ArticleView view) {
        this.view = view;
    }

    public void loadArticles(String domain) {
        ApiService apiService = new NetworkModule().createService();
        apiService.getArticles(domain, ApiConsts.API_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convert)
                .subscribe(view::onArticlesLoaded,
                        Throwable::printStackTrace);
    }

    private List<Article> convert(ArticleResponse articleResponse) {
        return articleResponse.getArticles();
    }
}
