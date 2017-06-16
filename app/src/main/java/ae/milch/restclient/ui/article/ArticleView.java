package ae.milch.restclient.ui.article;

import java.util.List;

import ae.milch.restclient.data.Article;

public interface ArticleView {
    void onArticlesLoaded(List<Article> articles);
}