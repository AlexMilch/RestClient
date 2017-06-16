package ae.milch.restclient.ui.article;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ae.milch.restclient.R;
import ae.milch.restclient.data.Article;
import ae.milch.restclient.domain.ArticlesPresenter;
import ae.milch.restclient.ui.source.SourcesAdapter;


public class ArticleActivity extends AppCompatActivity implements ArticleView {

    private ArticlesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView rvArticles = (RecyclerView) findViewById(R.id.rv_articles);
        rvArticles.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticlesAdapter(new ArrayList<>(), this);
        rvArticles.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        rvArticles.addItemDecoration(decoration);
        ArticlesPresenter presenter = new ArticlesPresenter(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String sourceId = extras.getString(SourcesAdapter.EXTRA_SOURCE_ID);
        String sourceName = extras.getString(SourcesAdapter.EXTRA_SOURCE_NAME);
        presenter.loadArticles(sourceId);
        setTitle(sourceName);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onArticlesLoaded(List<Article> articles) {
        adapter.initArticles(articles);
        adapter.notifyDataSetChanged();
    }
}
