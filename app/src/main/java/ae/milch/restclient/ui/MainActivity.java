package ae.milch.restclient.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ae.milch.restclient.R;
import ae.milch.restclient.data.Article;
import ae.milch.restclient.domain.ArticlesPresenter;

public class MainActivity extends AppCompatActivity {

    private ArticlesPresenter presenter;
    private RecyclerView rvArticles;
    private ArticlesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvArticles = (RecyclerView) findViewById(R.id.rv_articles);
        rvArticles.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticlesAdapter(new ArrayList<>());
        rvArticles.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        rvArticles.addItemDecoration(decoration);
        presenter = new ArticlesPresenter(this);
        presenter.loadArticles("bbc-news");
    }

    public void initAdapter(List<Article> articles) {
        adapter.initArticles(articles);
        adapter.notifyDataSetChanged();
    }
}
