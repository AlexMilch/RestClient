package ae.milch.restclient.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import ae.milch.restclient.R;
import ae.milch.restclient.data.Article;
import ae.milch.restclient.domain.MainPresenter;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        load();
    }

    private void load() {
        presenter.loadArticles("bbc-news");
    }

    public void initAdapter(List<Article> articles) {

    }
}
