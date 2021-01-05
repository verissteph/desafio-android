package stephanie.com.desafioKotlin.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.Utils.Constants
import stephanie.com.desafioKotlin.adapter.PullRequestAdapter
import stephanie.com.desafioKotlin.modelo.Owner
import stephanie.com.desafioKotlin.modelo.PullRequest
import stephanie.com.desafioKotlin.webService.InicializadorAPIPull


class PullRequestsActivity : AppCompatActivity(),PullRequestAdapter.OnItemClickListener {
    private val usuario_pull by lazy {
        InicializadorAPIPull.startPull()
    }
    private val adapterPull = PullRequestAdapter(ArrayList(), this)
    private val listaPull = ArrayList<PullRequest>()
    val recycler_pull_request = findViewById<RecyclerView>(R.id.recycler_pull_request)
    var owner = ""
    var repositorio = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)


        recycler_pull_request?.layoutManager = LinearLayoutManager(this)
        recycler_pull_request.setHasFixedSize(true)
        recycler_pull_request?.adapter = adapterPull

        owner = intent.getStringExtra(Constants.OWNER).toString()
        repositorio = intent.getStringExtra(Constants.REPOSITORIO).toString()


//        usuario_pull.getPulls(Constants.OWNER,Constants.REPOSITORIO).enqueue(object : Callback <PullRequest>{
//            override fun onResponse(
//                call: Call<PullRequest>,
//                response: Response<PullRequest>
//            ) {
//                if (response.isSuccessful){
//                    response.body()?.let {
//                        recycler_pull_request.adapter = PullRequestAdapter(listaPull,this@PullRequestsActivity)
//                   listaPull.addAll(listaPull)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<PullRequest>, t: Throwable) {
//               Log.d("Erro",t.message.toString())
//            }
//
//        })

        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getPulls(owner,repositorio)
    }

    fun getPulls(
        owner: String,
        repositorio: String,
    ) {
        val api = InicializadorAPIPull.startPull()
        val chamada = api.getPulls(owner, repositorio)
        chamada.enqueue(object : Callback<PullRequest> {

            override fun onResponse(call: Call<PullRequest>, response: Response<PullRequest>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        recycler_pull_request.adapter =
                            PullRequestAdapter(listaPull, this@PullRequestsActivity)
                        listaPull.addAll(listaPull)
                    }
                }
            }

            override fun onFailure(call: Call<PullRequest>, t: Throwable) {
                Log.d("Erro de chamada api",t.message.toString())
            }

        })
    }//aq

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return false
    }

//    override fun OnItemClick(item: PullRequest) {
//        val url = item.pull_request_url
//        val intencao = Intent(Intent.ACTION_VIEW)
//        intencao.data = Uri.parse(url)
//        startActivity(intencao)
//    }

    override fun OnItemClick(position: Int) {
//        val intencao = Intent(Intent.ACTION_VIEW)
//        intencao.data = Uri.parse()
        Toast.makeText(this, "clicando", Toast.LENGTH_LONG).show()
    }


}
