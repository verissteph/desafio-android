package stephanie.com.desafioKotlin.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.databinding.ItemPullRequestBinding
import stephanie.com.desafioKotlin.modelo.PullRequest

class PullRequestAdapter(
    val listaPullRequest: MutableList<PullRequest>,
    val listener: OnItemClickListener,
) : RecyclerView.Adapter<PullRequestAdapter.PullViewHolder>() {

    inner class PullViewHolder(val pullBinding: ItemPullRequestBinding) :
        RecyclerView.ViewHolder(pullBinding.root) {
        fun binding(pullRequest: PullRequest) {
            pullBinding.pullRequestDate.text = pullRequest.criacao_pull
            pullBinding.pullRequestDescricao.text = pullRequest.corpo_pull
            pullBinding.pullRequestName.text = pullRequest.user.nome_pull
            pullBinding.pullRequestTitle.text = pullRequest.titulo_pull
            Picasso.get().load(pullRequest.user.foto_pull).into(pullBinding.fotoUsuarioPullRequest)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder {
//
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
        holder.pullBinding.cardPull.setOnClickListener{
            listener.OnItemClick(position)
        }
    }

    override fun getItemCount(): Int = listaPullRequest.size


    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }
}
