package stephanie.com.desafioKotlin.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.modelo.Repositorio


class RepositorioAdapter(
    val listaRepositorio: MutableList<Repositorio>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<RepositorioAdapter.ItemViewHolder>() {

//    companion object
//    {
//        private const val VIEW_TYPE_DATA = 0;
//        private const val VIEW_TYPE_PROGRESS = 1;
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ItemViewHolder(itemView)
//        return when (viewType)
//        {
//            VIEW_TYPE_DATA ->
//            {
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo,parent,false)
//                  ItemViewHolder(view)
//
//
//            }
//            VIEW_TYPE_PROGRESS ->
//            {
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.progressbar,parent,false)
//                ItemViewHolder(view)
//            }
//            else -> throw IllegalArgumentException("View type diferente")
//        }
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val lista = listaRepositorio[position]
        holder.nomeRepositorio.text = lista.nome_repo
        holder.descricaoRepositorio.text = lista.descricao_repo
        holder.qdForksFepositorio.text = lista.forks_count_repo
        holder.qdeStarsRepositorios.text = lista.stargazers_count_repo
        holder.usernameRepositorio.text = lista.username_repo
        holder.fullnameRepositorio.text = lista.full_name_repo
        Picasso.get().load(lista.owner.avatar_url).into(holder.fotoRepositorio);

        holder.itemView.setOnClickListener{
            listener.onItemClick(position)
        }

    }

    override fun getItemCount() = listaRepositorio.size




    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
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


