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
import stephanie.com.desafioKotlin.Utils.Constants
import stephanie.com.desafioKotlin.Utils.EndlessRecyclerViewScrollListener
import stephanie.com.desafioKotlin.adapter.RepositorioAdapter
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.InicializadorAPIRepo
const val QDE_ITEMS = 20


class ListaDeRepositorioActivity :
    AppCompatActivity(),
    RepositorioAdapter.OnItemClickListener {
    private val usuario by lazy { InicializadorAPIRepo.start() }
    private val adapterRepo = RepositorioAdapter(ArrayList(), this)
    lateinit var recyclerRepositorio: RecyclerView
    lateinit var layoutManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        recyclerRepositorio = findViewById(R.id.recycler_repositorio)
        layoutManager = LinearLayoutManager(this)
        recyclerRepositorio.layoutManager = layoutManager
        recyclerRepositorio.setHasFixedSize(true)
        recyclerRepositorio.adapter = adapterRepo

        recyclerRepositorio.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                 getRepo(page)
            }



        })
        getRepo(1)
    }


    private fun getRepo(page:Int) {
        usuario.getRepo(page).enqueue(object : Callback<ItemRepositorio> {
            override fun onResponse(
                call: Call<ItemRepositorio>,
                response: Response<ItemRepositorio>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                    Log.i("pagina","esta é a pagina:${page}")
                       adapterRepo.listaRepositorio.addAll(it.items)
                        adapterRepo.notifyDataSetChanged()
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
        intencao.putExtra(Constants.REPOSITORIO, adapterRepo.listaRepositorio[position].nome_repo)
            startActivity(intencao)

    }


}
