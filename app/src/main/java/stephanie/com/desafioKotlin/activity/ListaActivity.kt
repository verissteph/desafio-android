package stephanie.com.desafioKotlin.activity

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.adapter.RepositorioAdapter
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.NetworkingUtils


class ListaActivity : AppCompatActivity(), RepositorioAdapter.OnItemClickListener {

//    private val itemRepositorio = geraLista(150)
//   private val adapter = RepositorioAdapter(itemRepositorio,this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        val recycler_repositorio = findViewById<RecyclerView>(R.id.recycler_repositorio)

        recycler_repositorio?.layoutManager = LinearLayoutManager(this)
        recycler_repositorio.setHasFixedSize(true)
        recycler_repositorio.adapter =
            RepositorioAdapter(this) // solucao para o erro: No adapter attached skipping layout

        getData() { repositoriosData ->
            if (repositoriosData != null) {
                (recycler_repositorio?.adapter as? RepositorioAdapter)?.updateList(repositoriosData)
            }
        }
    }

//    private fun geraLista(size: Int): List<ItemRepositorio> {
//        val lista = ArrayList<ItemRepositorio>()
//        for (i in 0..size) {
//            val drawable = when (i % 3) {
//                0 -> R.drawable.ic_usuario
//                else -> R.drawable.ic_usuario
//            }
//            val item = ItemRepositorio(
//                "Netflix $i",
//                "a netflix é muito boa para acalmar",
//                "300",
//                "20",
//                "10",
//                "3",
//                drawable,
//                "verissteph",
//                "verissteph/netflix"
//            )
//            lista += item
//        }
//        return lista
//    }

    override fun onItemClick(item: ItemRepositorio) {
        val intencao = Intent(this, PullRequestsActivity::class.java)
        startActivity(intencao)


    }

    private var job: Job? = null
    private fun getData(callback: (List<ItemRepositorio>?) -> Unit) {
        val URL_BASE = "https://api.github.com/"
        val retrofitClient =
            NetworkingUtils.getRetrofitInstance(URL_BASE)
        val repoService = retrofitClient.create(Endpoint::class.java)
        job?.cancel()
        job = null
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repoService.getRepo()
            if (response.isSuccessful) {
                Log.i("resposta", "${response.body()}")
                //AQUI ESTA O B.O
                withContext(Dispatchers.Main) {
                    callback(response.body()?.items?.transfomeEmListaItemRepositorio())
                }

            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ListaActivity,
                        "erro ${response.errorBody()}",
                        Toast.LENGTH_LONG
                    ).show()
                    callback(null)
                }
            }
        }
    }
}