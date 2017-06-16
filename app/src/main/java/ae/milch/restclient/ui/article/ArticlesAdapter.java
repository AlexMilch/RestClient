package ae.milch.restclient.ui.article;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ae.milch.restclient.R;
import ae.milch.restclient.data.Article;

class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<Article> articles;
    private final ArticleActivity activity;

    ArticlesAdapter(List<Article> articles, ArticleActivity activity) {
        this.articles = articles;
        this.activity = activity;
    }

    void initArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item_view, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = getItem(position);
        if (article == null) {
            return;
        }
        holder.tvName.setText(article.getTitle());
        holder.ivArticle.setImageURI(Uri.parse(article.getUrlToImage()));
        holder.itemView.setOnClickListener(v -> openArticle(article.getUrl()));
    }

    private void openArticle(String link) {
        Uri address = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, address);
        activity.startActivity(intent);
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
        SimpleDraweeView ivArticle;

        ArticleViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            ivArticle = (SimpleDraweeView) itemView.findViewById(R.id.iv_picture);
        }
    }
}
