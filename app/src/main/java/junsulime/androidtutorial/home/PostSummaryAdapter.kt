package junsulime.androidtutorial.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import junsulime.androidtutorial.R
import junsulime.androidtutorial.models.PostSummary
import kotlinx.android.synthetic.main.item_post_summary.view.*

class PostSummaryAdapter: RecyclerView.Adapter<PostSummaryAdapter.PostSummaryHolder>() {

    private val posts: MutableList<PostSummary> by lazy { mutableListOf<PostSummary>() }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostSummaryHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_post_summary, viewGroup, false)
        return PostSummaryHolder(v)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostSummaryHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun resetPosts(posts: List<PostSummary>) {
        this.posts.clear()
        this.posts.addAll(posts)
    }

    class PostSummaryHolder(view: View): RecyclerView.ViewHolder(view) {
        private val postSummaryTitle: TextView = view.postSummaryTitle
        private val postSummaryWriter: TextView = view.postSummaryWriter

        fun bind(postSummary: PostSummary) {
            postSummaryTitle.text = postSummary.title
            postSummaryWriter.text = postSummary.user.name
        }
    }
}