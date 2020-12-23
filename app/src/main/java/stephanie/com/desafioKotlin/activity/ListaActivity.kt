package stephanie.com.desafioKotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.adapter.RepositorioAdapter
import stephanie.com.desafioKotlin.modelo.ItemRepositorio



class ListaActivity : AppCompatActivity(),RepositorioAdapter.OnItemClickListener {

    private val itemRepositorio = geraLista(150)
    private val adapter = RepositorioAdapter(itemRepositorio,this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
      //  getData()
            val recycler_repositorio = findViewById<RecyclerView>(R.id.recycler_repositorio)
            recycler_repositorio?.layoutManager = LinearLayoutManager(this)
            recycler_repositorio?.adapter = adapter
            recycler_repositorio.setHasFixedSize(true)





    }

    private fun geraLista(size:Int) : List<ItemRepositorio> {
        val lista = ArrayList<ItemRepositorio>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_usuario
                else -> R.drawable.ic_usuario
            }
            val item = ItemRepositorio("Netflix $i",
                "a netflix é muito boa para acalmar",
                300,
                "20",
                10,
                "3",
                drawable,
                "verissteph",
                "verissteph/netflix")
            lista+=item
        }
       return lista
    }

    override fun onItemClick(position: Int) {
        val intencao = Intent(this, PullRequestsActivity::class.java)
        startActivity(intencao)


    }

//
//        private fun getData() {
//            val retrofitClient =
//                NetworkingUtils.getRetrofitInstance("https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1")
//            val endpoint = retrofitClient.create(Endpoint::class.java)
//            val callback = endpoint.getRepo()
//            callback.enqueue(object : Callback<List<Repositorio>> {
//                override fun onResponse(
//                    call: Call<List<Repositorio>>,
//                    response: Response<List<Repositorio>>
//                ) {
//                    response.body()?.forEach {
//                        textView.text = textView.text.toString()
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Repositorio>>, t: Throwable) {
//                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//                }
//
//            })
//        }
}