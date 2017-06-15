package ae.milch.restclient.ui.source;

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
import ae.milch.restclient.data.Source;
import ae.milch.restclient.domain.SourcesPresenter;
import ae.milch.restclient.ui.category.CategoriesAdapter;

public class SourceActivity extends AppCompatActivity implements SourceView {

    private SourcesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView rvSources = (RecyclerView) findViewById(R.id.rv_sources);
        rvSources.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SourcesAdapter(new ArrayList<>(), this);
        rvSources.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        rvSources.addItemDecoration(decoration);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String categoryId = extras.getString(CategoriesAdapter.EXTRA_CATEGORY_ID);
        String categoryName = extras.getString(CategoriesAdapter.EXTRA_CATEGORY_NAME);
        setTitle(categoryName);
        SourcesPresenter presenter = new SourcesPresenter(this);
        presenter.loadSources(categoryId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSourcesLoaded(List<Source> sources) {
        adapter.initSources(sources);
        adapter.notifyDataSetChanged();
    }
}
