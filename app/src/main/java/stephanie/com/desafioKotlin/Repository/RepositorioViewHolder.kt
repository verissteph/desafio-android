package stephanie.com.desafioKotlin.Repository

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.databinding.ItemRepoBinding
import stephanie.com.desafioKotlin.modelo.Repositorio

class RepositorioViewHolder(val repoBinding: ItemRepoBinding) :
    RecyclerView.ViewHolder(repoBinding.root) {

    fun binding(repositorio: Repositorio) {
        repoBinding.apply {
            nomeRepositorio.text = repositorio.nomeRepo
            descricaoRepositorio.text = repositorio.descricaoRepo
            qdeForksRepositorio.text = repositorio.forksCountRepo
            qdeStarsRepositorio.text = repositorio.stargazersCountRepo
            usernameRepositorio.text = repositorio.owner.login
            fullnameRepositorio.text = repositorio.fullNameRepo
        }
        Picasso.get().load(repositorio.owner.avatarUrl).into(repoBinding.fotoRepositorio);
    }
}