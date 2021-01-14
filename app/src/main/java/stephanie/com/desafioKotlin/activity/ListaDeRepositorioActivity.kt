package stephanie.com.desafioKotlin.activity

// OPtimize imports do Android Studio
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.Utils.Constants
import stephanie.com.desafioKotlin.Utils.EndlessRecyclerViewScrollListener
import stephanie.com.desafioKotlin.adapter.RepositorioAdapter
import stephanie.com.desafioKotlin.databinding.ActivityListaBinding
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.InicializadorAPIRepo

// Avaliar uso feature by package

class ListaDeRepositorioActivity :
    AppCompatActivity(),
    // Remover o OnItemClickListener e implementar o clique separado, passando a implementaçào para o adapter
    RepositorioAdapter.OnItemClickListener {
    private val usuario by lazy { InicializadorAPIRepo.start() }
    private val adapterRepo = RepositorioAdapter(ArrayList(), this)

    lateinit var layoutManager: LinearLayoutManager
    lateinit var binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Considerar remover ou declarar variáveis apenas no escopo que é utilizado
        layoutManager = LinearLayoutManager(this)
        // binding.recyclerRepositorio.layoutManager = LinearLayoutManager(this)
        binding.recyclerRepositorio.layoutManager = layoutManager
        binding.recyclerRepositorio.setHasFixedSize(true)
        binding.recyclerRepositorio.adapter = adapterRepo
        binding.recyclerRepositorio.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(layoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                getRepo(page)
                load(true)
            }
        })

        getRepo(1)




    }

    // Incluir cenário de erro para o usuário
    private fun getRepo(page: Int) {
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

                    }
                    // O que acontece se o Github retornar erro?

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

    private fun load(l: Boolean) {
        if (l) binding.progressBar.visibility =
            View.VISIBLE else binding.progressBar.visibility = View.GONE
    }

    override fun onItemClick(position: Int) {
        val intencao = Intent(this, PullRequestsActivity::class.java)
        intencao.putExtra(Constants.OWNER, adapterRepo.listaRepositorio[position].owner.login)
        intencao.putExtra(Constants.REPOSITORIO, adapterRepo.listaRepositorio[position].nome_repo)
        startActivity(intencao)

    }


}
