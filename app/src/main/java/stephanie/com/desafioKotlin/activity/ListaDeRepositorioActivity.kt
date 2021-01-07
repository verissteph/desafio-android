package stephanie.com.desafioKotlin.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
import stephanie.com.desafioKotlin.Utils.EndlessRecyclerViewScrollListener
import stephanie.com.desafioKotlin.adapter.RepositorioAdapter
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.InicializadorAPIRepo


class ListaDeRepositorioActivity :
    AppCompatActivity(),
    RepositorioAdapter.OnItemClickListener {
    private val usuario by lazy { InicializadorAPIRepo.start() }
    private val adapterRepo = RepositorioAdapter(ArrayList(), this)
    private var isLoading: Boolean = false
    lateinit var recyclerRepositorio: RecyclerView
    lateinit var layoutManager:LinearLayoutManager
    var handler: Handler = Handler()
    private val scrollListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        recyclerRepositorio = findViewById(R.id.recycler_repositorio)
        layoutManager = LinearLayoutManager(this)
        recyclerRepositorio.layoutManager = layoutManager
        recyclerRepositorio.setHasFixedSize(true)
        recyclerRepositorio.adapter = adapterRepo

        recyclerRepositorio.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


            }
        })
//
//        load()
//        addScrollerListener(scrollListener)



        usuario.getRepo().enqueue(object : Callback<ItemRepositorio> {
            override fun onResponse(
                call: Call<ItemRepositorio>,
                response: Response<ItemRepositorio>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        recyclerRepositorio.adapter =
                            RepositorioAdapter(
                                it.items as MutableList<Repositorio>,
                                this@ListaDeRepositorioActivity
                            )
                        adapterRepo.listaRepositorio.addAll(it.items)
                    }
                }
            }

            override fun onFailure(call: Call<ItemRepositorio>, t: Throwable) {
                Log.d("Erro", t.message.toString())
            }

        })
    }

//    private fun addScrollerListener(scrollListener: EndlessRecyclerViewScrollListener?){
//        //attaches scrollListener with RecyclerView
//        recyclerRepositorio.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (!isLoading) {
//                    //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
//                    ////It checks, fully visible view is the last one.
//                    if (layoutManager.findLastCompletelyVisibleItemPosition() == adapterRepo.listaRepositorio.size - 1) {
//                        loadMore()
//                        isLoading = true
//                    }
//                }
//            }
//        })
//    }

//    private fun loadMore(){
//        handler.post(Runnable
//        {
//
//            adapterRepo.notifyItemInserted(adapterRepo.listaRepositorio.size - 1)
//        })
//        handler.postDelayed({
//            adapterRepo.listaRepositorio.removeAt(adapterRepo.listaRepositorio.size - 1)
//            val tamanhoLista = adapterRepo.listaRepositorio.size
//            adapterRepo.notifyItemRemoved(tamanhoLista)
//            adapterRepo.notifyDataSetChanged()
//            isLoading = false
//        }, 2500)
//    }
//    private fun load()
//    {
//        for(i in 0..9)
//        {
//            Toast.makeText(this, "item nº:${i}", Toast.LENGTH_LONG).show()
//        }
//    }

    override fun onItemClick(position: Int) {
        val intencao = Intent(this, PullRequestsActivity::class.java)
        intencao.putExtra(Constants.OWNER, adapterRepo.listaRepositorio[position].owner.login)
        intencao.putExtra(Constants.REPOSITORIO, adapterRepo.listaRepositorio[position].nome_repo)
            startActivity(intencao)

    }

}
