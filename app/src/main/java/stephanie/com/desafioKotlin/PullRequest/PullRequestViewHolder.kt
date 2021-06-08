package stephanie.com.desafioKotlin.PullRequest

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.databinding.ItemPullRequestBinding
import stephanie.com.desafioKotlin.modelo.PullRequest

class PullRequestViewHolder(val pullBinding: ItemPullRequestBinding) :
    RecyclerView.ViewHolder(pullBinding.root) {
    fun binding(pullRequest: PullRequest) {
        pullBinding.apply {
            pullRequestDate.text = pullRequest.criacaoPull
            pullRequestDescricao.text = pullRequest.corpoPull
            pullRequestName.text = pullRequest.user.nomePull
            pullRequestTitle.text = pullRequest.tituloPull

        }
        Picasso.get().load(pullRequest.user.fotoPull).into(pullBinding.fotoUsuarioPullRequest)

    }
}
