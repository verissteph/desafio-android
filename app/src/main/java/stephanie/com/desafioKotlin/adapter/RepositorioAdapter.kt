package stephanie.com.desafioKotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.databinding.ItemRepoBinding
import stephanie.com.desafioKotlin.modelo.Repositorio


class RepositorioAdapter(
    val listaRepositorio: MutableList<Repositorio>,
    val listener: OnItemClickListener

) : RecyclerView.Adapter<RepositorioAdapter.RepositorioViewHolder>() {

    inner class RepositorioViewHolder(val repoBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(repoBinding.root) {

        fun binding(repositorio: Repositorio) {
            repoBinding.nomeRepositorio.text = repositorio.nome_repo
            repoBinding.descricaoRepositorio.text = repositorio.descricao_repo
            repoBinding.qdeForksRepositorio.text = repositorio.forks_count_repo
            repoBinding.qdeStarsRepositorio.text = repositorio.stargazers_count_repo

            repoBinding.usernameRepositorio.text = repositorio.owner.login
            repoBinding.fullnameRepositorio.text = repositorio.full_name_repo
            Picasso.get().load(repositorio.owner.avatar_url).into(repoBinding.fotoRepositorio);

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositorioViewHolder {
//
        return RepositorioViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RepositorioViewHolder, position: Int) {
        holder.binding(this.listaRepositorio[position])

        holder.repoBinding.cardRepo.setOnClickListener {
            listener.onItemClick(position)
        }

    }

    override fun getItemCount() = listaRepositorio.size


    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }


}


