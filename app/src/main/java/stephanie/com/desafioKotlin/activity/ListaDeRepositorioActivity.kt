package stephanie.com.desafioKotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.adapter.RepositorioAdapter
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.InicializadorAPI
import javax.security.auth.callback.Callback


class ListaDeRepositorioActivity : AppCompatActivity(), RepositorioAdapter.OnItemClickListener {
    private val usuario by lazy {InicializadorAPI.start()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        val recycler_repositorio = findViewById<RecyclerView>(R.id.recycler_repositorio)

        recycler_repositorio?.layoutManager = LinearLayoutManager(this)
        recycler_repositorio.setHasFixedSize(true)
        recycler_repositorio.adapter =
            RepositorioAdapter(this)

        usuario.getRepo().enqueue(object : Callback<ItemRepositorio>)


    }


    override fun onItemClick(item: ItemRepositorio) {
        val intencao = Intent(this, PullRequestsActivity::class.java)
        intencao.putExtra("criador", item.owner_login) //owner.login ->criador
        intencao.putExtra("repositorio", item.nome_repositorio) //repositorio.name ->repositorio
        startActivity(intencao)
    }

}
