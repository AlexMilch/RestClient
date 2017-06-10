package ae.milch.restclient.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ae.milch.restclient.R;
import ae.milch.restclient.data.Article;

class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<Article> articles;

    ArticlesAdapter(List<Article> articles) {
        this.articles = articles;
    }

    void initArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = getItem(position);
        if (article == null) {
            return;
        }
        holder.tvName.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        if (articles == null) {
            return 0;
        }
        return articles.size();
    }

    private Article getItem(int position) {
        return articles == null ? null : articles.get(position);
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        ArticleViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
