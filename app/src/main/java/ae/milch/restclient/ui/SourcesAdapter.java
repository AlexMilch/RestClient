package ae.milch.restclient.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ae.milch.restclient.R;
import ae.milch.restclient.data.Source;

class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.SourceViewHolder> {

    static final String EXTRA_SOURCE_ID = "EXTRA_SOURCE_ID";
    private List<Source> sources;
    private final SourceActivity activity;

    SourcesAdapter(List<Source> sources, SourceActivity activity) {
        this.sources = sources;
        this.activity = activity;
    }

    void initSources(List<Source> sources) {
        this.sources = sources;
    }

    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_item_view, parent, false);
        return new SourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SourceViewHolder holder, int position) {
        Source item = getItem(position);
        if (item == null){
            return;
        }
        holder.tvName.setText(item.getName());
        holder.tvCategory.setText(item.getCategory());
        holder.itemView.setOnClickListener(v -> moveToArticles(item.getId()));
    }

    private void moveToArticles(String id) {
        Intent intent = new Intent(activity, ArticleActivity.class);
        intent.putExtra(EXTRA_SOURCE_ID, id);
        activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (sources == null) {
            return 0;
        }
        return sources.size();
    }

    private Source getItem(int position){
        return sources == null ? null : sources.get(position);
    }

    static class SourceViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvCategory;

        SourceViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
        }
    }
}
