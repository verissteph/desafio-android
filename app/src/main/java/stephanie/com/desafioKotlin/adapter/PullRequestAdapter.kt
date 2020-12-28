package stephanie.com.desafioKotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.modelo.ItemPullRequest
import stephanie.com.desafioKotlin.modelo.ItemRepositorio

class PullRequestAdapter(private var listaPullRequest:List<ItemPullRequest> = emptyList()):RecyclerView.Adapter<PullRequestAdapter.ItemViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request,parent,false)
        return ItemViewHolder(itemView)
    }


    fun updateList(items: List<ItemPullRequest>) {
        listaPullRequest = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val nomeLista = listaPullRequest[position]
        holder.pullRequestTitulo.text = nomeLista.pull_request_titulo
        holder.pullRequestDescricao.text = nomeLista.pull_request_descricao
//        holder.fotoUsuarioPullRequest.toString()
        holder.usernamePullRequest.text = nomeLista.username_pull_request
        holder.pullRequestNome.text = nomeLista.pull_request_nome
        holder.pullRequestDate.text = nomeLista.pull_request_date
        Picasso.get().load(nomeLista.foto_usuario_pull_request).into(holder.fotoUsuarioPullRequest)


    }

    override fun getItemCount(): Int = listaPullRequest.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val pullRequestTitulo:TextView = itemView.findViewById(R.id.pull_request_name)
        val pullRequestDescricao:TextView = itemView.findViewById(R.id.pull_request_descricao)
        val fotoUsuarioPullRequest:ImageView = itemView.findViewById(R.id.foto_usuario_pull_request)
        val usernamePullRequest:TextView = itemView.findViewById(R.id.username_pull_request)
        val pullRequestNome:TextView = itemView.findViewById(R.id.pull_request_name)
        val pullRequestDate:TextView = itemView.findViewById(R.id.pull_request_date)
    }

}