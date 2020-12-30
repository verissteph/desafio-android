package stephanie.com.desafioKotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.modelo.ItemRepositorio
import stephanie.com.desafioKotlin.modelo.Repositorio


class RepositorioAdapter(
     var listaRepositorio: List<Repositorio>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<RepositorioAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ItemViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {


        holder.nomeRepositorio.text = listaRepositorio[position].nome_repo
        holder.descricaoRepositorio.text = listaRepositorio[position].descricao_repo
        holder.qdForksFepositorio.text = listaRepositorio[position].forks_count_repo
        holder.qdeStarsRepositorios.text = listaRepositorio[position].stargazers_count_repo
        holder.usernameRepositorio.text =listaRepositorio[position].username_repo
        holder.fullnameRepositorio.text = listaRepositorio[position].full_name_repo

        //Falta incluir a foto
    }

    override fun getItemCount() = listaRepositorio.size

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)
         {
        val nomeRepositorio: TextView = itemView.findViewById(R.id.nome_repositorio)
        val descricaoRepositorio: TextView = itemView.findViewById(R.id.descricao_repositorio)
        val qdForksFepositorio: TextView = itemView.findViewById(R.id.qde_forks_repositorio)
        val qdeStarsRepositorios: TextView = itemView.findViewById(R.id.qde_stars_repositorio)
        val fotoRepositorio: ImageView = itemView.findViewById(R.id.foto_repositorio)
        val usernameRepositorio: TextView = itemView.findViewById(R.id.username_repositorio)
        val fullnameRepositorio: TextView = itemView.findViewById(R.id.fullname_repositorio)

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}


