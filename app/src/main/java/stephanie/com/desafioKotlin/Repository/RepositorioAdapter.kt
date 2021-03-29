package stephanie.com.desafioKotlin.Repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.modelo.Repositorio
import stephanie.com.desafioKotlin.databinding.ItemRepoBinding


class RepositorioAdapter(
    val listaRepositorio: MutableList<Repositorio>,
    val listener: OnItemClickListener

) : RecyclerView.Adapter<RepositorioAdapter.RepositorioViewHolder>() {

    class RepositorioViewHolder(val repoBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(repoBinding.root) {


        fun binding(repositorio: Repositorio) {
            repoBinding.nomeRepositorio.text = repositorio.nomeRepo
            repoBinding.descricaoRepositorio.text = repositorio.descricaoRepo
            repoBinding.qdeForksRepositorio.text = repositorio.forksCountRepo
            repoBinding.qdeStarsRepositorio.text = repositorio.stargazersCountRepo
            repoBinding.usernameRepositorio.text = repositorio.owner.login
            repoBinding.fullnameRepositorio.text = repositorio.fullNameRepo
            Picasso.get().load(repositorio.owner.avatarUrl).into(repoBinding.fotoRepositorio);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositorioViewHolder {
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


