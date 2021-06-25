package stephanie.com.desafioKotlin.Repository

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.modelo.ItemRepositorio
import stephanie.com.desafioKotlin.Utils.Constants
import stephanie.com.desafioKotlin.Utils.EndlessRecyclerViewScrollListener
import stephanie.com.desafioKotlin.PullRequest.PullRequestsActivity
import stephanie.com.desafioKotlin.databinding.ActivityListaBinding
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.Inicializador


class ListaDeRepositorioActivity :
    AppCompatActivity(),
// Remover o OnItemClickListener e implementar o clique separado, passando a implementaçào para o adapter
    RepositorioAdapter.OnItemClickListener {

    private val usuario by lazy { Inicializador.start() }

    private val adapterRepo = RepositorioAdapter(ArrayList(), this)

    lateinit var binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.apply {
            recyclerRepositorio.layoutManager = layoutManager
            recyclerRepositorio.setHasFixedSize(true)
            recyclerRepositorio.adapter = adapterRepo

            recyclerRepositorio.addOnScrollListener(object :
                EndlessRecyclerViewScrollListener(layoutManager) {

                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    getRepo(page)

                }
            })
        }
        getRepo(1)


    }

    private fun getRepo(page: Int) {
        binding.progressBar.visibility = View.VISIBLE
        usuario.getRepo(page).enqueue(object : Callback<ItemRepositorio> {
            override fun onResponse(
                call: Call<ItemRepositorio>,
                response: Response<ItemRepositorio>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.i("pagina", "esta é a pagina:${page}")
                        adapterRepo.listaRepositorio.addAll(it.items)
                        adapterRepo.notifyDataSetChanged()
                        binding.progressBar.visibility = View.GONE

                    }
                    response.errorBody()?.let {
                        response.message()
                    }
                }
            }

            override fun onFailure(call: Call<ItemRepositorio>, t: Throwable) {
                Log.d("Erro", t.message.toString())
            }

        })
    }

    // Remover o OnItemClickListener e implementar o clique separado,
// passando a implementaçào para o adapter
    override fun onItemClick(position: Int) {
        val intencao = Intent(this, PullRequestsActivity::class.java).apply {
            putExtra(Constants.OWNER, adapterRepo.listaRepositorio[position].owner.login)
            putExtra(Constants.REPOSITORIO, adapterRepo.listaRepositorio[position].nomeRepo)
        }
        startActivity(intencao)

    }


}
