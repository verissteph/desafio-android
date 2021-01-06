package stephanie.com.desafioKotlin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.Utils.Constants
import stephanie.com.desafioKotlin.adapter.RepositorioAdapter
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.InicializadorAPIRepo


class ListaDeRepositorioActivity :
    AppCompatActivity(),
    RepositorioAdapter.OnItemClickListener {
    private val usuario by lazy { InicializadorAPIRepo.start() }
    private val adapterRepo = RepositorioAdapter(ArrayList(),this)

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
                            RepositorioAdapter(it.items as MutableList<Repositorio>, this@ListaDeRepositorioActivity)
                            adapterRepo.listaRepositorio.addAll(it.items)
                    }
                }
            }

            override fun onFailure(call: Call<ItemRepositorio>, t: Throwable) {
                Log.d("Erro", t.message.toString())
            }

        })

    }


    override fun onItemClick(position: Int) {
        val intencao = Intent(this, PullRequestsActivity::class.java)
        intencao.putExtra(Constants.OWNER, adapterRepo.listaRepositorio[position].owner.login)
        intencao.putExtra(Constants.REPOSITORIO,adapterRepo.listaRepositorio[position].nome_repo)
           startActivity(intencao)
    }

}
