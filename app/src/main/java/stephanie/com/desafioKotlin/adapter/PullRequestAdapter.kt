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
import stephanie.com.desafioKotlin.modelo.PullRequest

class PullRequestAdapter(
    val listaPullRequest: MutableList<PullRequest>,
    val listener: OnItemClickListener,
) : RecyclerView.Adapter<PullRequestAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false)
        return ItemViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val nomeLista = listaPullRequest[position]


        holder.pullRequestTitulo.text = nomeLista.titulo_pull
        holder.pullRequestDescricao.text = nomeLista.corpo_pull
        holder.pullRequestNome.text = nomeLista.user.nome_pull
        holder.pullRequestDate.text = nomeLista.criacao_pull



        Picasso.get().load(nomeLista.user.foto_pull).into(holder.fotoUsuarioPullRequest)

        holder.itemView.setOnClickListener{
            listener.OnItemClick(position)
        }

    }

    override fun getItemCount(): Int = listaPullRequest.size

    class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val pullRequestTitulo: TextView = itemView.findViewById(R.id.pull_request_title)
        val pullRequestDescricao: TextView = itemView.findViewById(R.id.pull_request_descricao)
        val fotoUsuarioPullRequest: ImageView =
            itemView.findViewById(R.id.foto_usuario_pull_request)
        val pullRequestNome: TextView = itemView.findViewById(R.id.pull_request_name)
        val pullRequestDate: TextView = itemView.findViewById(R.id.pull_request_date)

    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }
}
