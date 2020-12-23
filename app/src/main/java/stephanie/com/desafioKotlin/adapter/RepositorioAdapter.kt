package stephanie.com.desafioKotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.modelo.ItemRepositorio

class RepositorioAdapter(
    private val listaRepositorio: List<ItemRepositorio>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RepositorioAdapter.ItemViewHolder>() {
    // cria os fixadores de visualização (objetos de ItemRepositorio) conforme necessário
    // vincula os fixadores de visualização aos respectivos dados (atraves do onBindViewHolder)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ItemViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val nomeLista = listaRepositorio[position]

        holder.nomeRepositorio.text = nomeLista.nome_repositorio
        holder.descricaoRepositorio.text = nomeLista.descricao_repositorio
        holder.qdForksFepositorio.toString()
        holder.qdeStarsRepositorios.toString()
        holder.fotoRepositorio.setImageResource(nomeLista.foto_repositorio)
        holder.usernameRepositorio.text = nomeLista.username_repositorio
        holder.fullnameRepositorio.text = nomeLista.fullname_repositorio


    }

    override fun getItemCount() = listaRepositorio.size

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val nomeRepositorio: TextView = itemView.findViewById(R.id.nome_repositorio)
        val descricaoRepositorio: TextView = itemView.findViewById(R.id.descricao_repositorio)
        val qdForksFepositorio: TextView = itemView.findViewById(R.id.qde_forks_repositorio)
        val qdeStarsRepositorios: TextView = itemView.findViewById(R.id.qde_stars_repositorio)
        val fotoRepositorio: ImageView = itemView.findViewById(R.id.foto_repositorio)
        val usernameRepositorio: TextView = itemView.findViewById(R.id.username_repositorio)
        val fullnameRepositorio: TextView = itemView.findViewById(R.id.fullname_repositorio)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}


