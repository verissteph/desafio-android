package stephanie.com.desafioKotlin.PullRequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.databinding.ItemPullRequestBinding
import stephanie.com.desafioKotlin.modelo.PullRequest

class PullRequestAdapter(
    val listaPullRequest: MutableList<PullRequest>,
    val listener: OnItemClickListener,
) : RecyclerView.Adapter<PullViewHolder>() {


 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder {
        return PullViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: PullViewHolder, position: Int) {
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
