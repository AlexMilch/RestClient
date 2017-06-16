package ae.milch.restclient.ui.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import ae.milch.restclient.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        RecyclerView rvCategory = (RecyclerView) findViewById(R.id.rv_category);
        rvCategory.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration decoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        rvCategory.addItemDecoration(decoration);

        initAdapter(rvCategory);
    }

    private void initAdapter(RecyclerView rvCategory) {
        List<String> categoryIds = Arrays.asList(getResources().getStringArray(R.array.category_ids));
        List<String> categoryNames = Arrays.asList(getResources().getStringArray(R.array.category_names));
        CategoriesAdapter adapter = new CategoriesAdapter(categoryIds, categoryNames, this);
        rvCategory.setAdapter(adapter);
    }
}
