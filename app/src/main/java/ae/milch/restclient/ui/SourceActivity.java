package ae.milch.restclient.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ae.milch.restclient.R;
import ae.milch.restclient.data.Source;
import ae.milch.restclient.domain.SourcesPresenter;

public class SourceActivity extends AppCompatActivity {

    private RecyclerView rvSources;
    private SourcesAdapter adapter;
    private SourcesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        rvSources = (RecyclerView) findViewById(R.id.rv_sources);
        rvSources.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SourcesAdapter(new ArrayList<>(), this);
        rvSources.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        rvSources.addItemDecoration(decoration);
        presenter = new SourcesPresenter(this);
        presenter.loadSources();
    }

    public void onSourcesLoaded(List<Source> sources){
        adapter.initSources(sources);
        adapter.notifyDataSetChanged();
    }
}
