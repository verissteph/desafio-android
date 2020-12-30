package stephanie.com.desafioKotlin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.adapter.RepositorioAdapter
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.InicializadorAPI


class ListaDeRepositorioActivity : AppCompatActivity(), RepositorioAdapter.OnItemClickListener {
    private val usuario by lazy { InicializadorAPI.start() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        val recycler_repositorio = findViewById<RecyclerView>(R.id.recycler_repositorio)

        recycler_repositorio?.layoutManager = LinearLayoutManager(this)
        recycler_repositorio.setHasFixedSize(true)
        recycler_repositorio.adapter =
            RepositorioAdapter(ArrayList(), this)

        usuario.getRepo().enqueue(object : Callback<ItemRepositorio> {
            override fun onResponse(
                call: Call<ItemRepositorio>,
                response: Response<ItemRepositorio>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        recycler_repositorio.adapter =
                            RepositorioAdapter(it.items, this@ListaDeRepositorioActivity)
                    }
                }
            }

            override fun onFailure(call: Call<ItemRepositorio>, t: Throwable) {
                Log.d("Erro", t.message.toString())
            }

        })

    }


    override fun onItemClick(item: ItemRepositorio) {
        val intencao = Intent(this, PullRequestsActivity::class.java)
        intencao.putExtra("criador", item.owner_login) //owner.login ->criador
        intencao.putExtra("repositorio", item.nome_repositorio) //repositorio.name ->repositorio
        startActivity(intencao)
    }

}
