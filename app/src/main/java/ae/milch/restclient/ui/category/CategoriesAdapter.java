package ae.milch.restclient.ui.category;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ae.milch.restclient.R;
import ae.milch.restclient.ui.source.SourceActivity;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    public static final String EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID";
    public static final String EXTRA_CATEGORY_NAME = "EXTRA_CATEGORY_NAME";
    private final CategoryActivity activity;
    private final List<String> categoryIds;
    private final List<String> categoryNames;

    CategoriesAdapter(List<String> categoryIds, List<String> categoryNames, CategoryActivity activity) {
        this.categoryIds = categoryIds;
        this.categoryNames = categoryNames;
        this.activity = activity;
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_view, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        String categoryName = getCurrentName(position);
        if (TextUtils.isEmpty(categoryName)) {
            return;
        }
        holder.tvCategory.setText(categoryName);
        holder.itemView.setOnClickListener(v -> {
            String currentId = getCurrentId(position);
            if (TextUtils.isEmpty(currentId)) {
                return;
            }
            moveToSources(currentId, categoryName);
        });
    }

    private void moveToSources(String id, String name) {
        Intent intent = new Intent(activity, SourceActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, id);
        intent.putExtra(EXTRA_CATEGORY_NAME, name);
        activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return categoryNames == null ? 0 : categoryNames.size();
    }

    private String getCurrentName(int position) {
        return categoryNames == null ? null : categoryNames.get(position);
    }

    private String getCurrentId(int position) {
        return categoryIds == null ? null : categoryIds.get(position);
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategory;


        CategoryViewHolder(View item) {
            super(item);

            tvCategory = (TextView) item.findViewById(R.id.tv_category);

        }
    }


}
