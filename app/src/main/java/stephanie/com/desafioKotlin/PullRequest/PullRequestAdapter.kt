package stephanie.com.desafioKotlin.PullRequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import stephanie.com.desafioKotlin.databinding.ItemPullRequestBinding
import stephanie.com.desafioKotlin.modelo.PullRequest

class PullRequestAdapter(
    val listaPullRequest: MutableList<PullRequest>,
    val listener: OnItemClickListener,
) : RecyclerView.Adapter<PullRequestViewHolder>() {


 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.binding(this.listaPullRequest[position])
        // Poderia passar o próprio objeto no onItemClick
        holder.pullBinding.cardPull.setOnClickListener {
            listener.OnItemClick(position)
        }
    }

    override fun getItemCount(): Int = listaPullRequest.size


    interface OnItemClickListener {
        // Que tal passar o próprio objeto ao clicar
        fun OnItemClick(position: Int)
    }
}
