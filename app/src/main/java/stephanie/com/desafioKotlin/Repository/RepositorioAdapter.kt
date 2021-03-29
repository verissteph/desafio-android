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


