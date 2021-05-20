package stephanie.com.desafioKotlin.PullRequest

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.databinding.ItemPullRequestBinding
import stephanie.com.desafioKotlin.modelo.PullRequest

class PullViewHolder(val pullBinding: ItemPullRequestBinding): RecyclerView.ViewHolder(pullBinding.root) {
    fun binding(pullRequest: PullRequest) {
        pullBinding.pullRequestDate.text = pullRequest.criacaoPull
        pullBinding.pullRequestDescricao.text = pullRequest.corpoPull
        pullBinding.pullRequestName.text = pullRequest.user.nomePull
        pullBinding.pullRequestTitle.text = pullRequest.tituloPull
        Picasso.get().load(pullRequest.user.fotoPull).into(pullBinding.fotoUsuarioPullRequest)

    }
}
