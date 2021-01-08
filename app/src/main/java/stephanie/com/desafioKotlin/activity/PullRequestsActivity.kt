package stephanie.com.desafioKotlin.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.Utils.Constants
import stephanie.com.desafioKotlin.adapter.PullRequestAdapter
import stephanie.com.desafioKotlin.modelo.PullRequest
import stephanie.com.desafioKotlin.webService.InicializadorAPIPull


class PullRequestsActivity : AppCompatActivity(), PullRequestAdapter.OnItemClickListener {
    val listaPull = ArrayList<PullRequest>()
    private val adapterPull = PullRequestAdapter(listaPull, this)
    var owner = ""
    var repositorio = ""
    lateinit var recyclerPullRequest: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)
        recyclerPullRequest = findViewById(R.id.recycler_pull_request)
        recyclerPullRequest.layoutManager = LinearLayoutManager(this)
        recyclerPullRequest.setHasFixedSize(true)
        recyclerPullRequest.adapter = adapterPull

        //pegando dados da outra activity e recuperando
        owner = intent.getStringExtra(Constants.OWNER).toString()
        repositorio = intent.getStringExtra(Constants.REPOSITORIO).toString()
        getPulls(owner, repositorio)

        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = repositorio

    }

    fun getPulls(
        owner: String,
        repositorio: String,
    ) {
        val api = InicializadorAPIPull.startPull()
        val chamada = api.getPulls(owner, repositorio)
        chamada.enqueue(object : Callback<List<PullRequest>> {

            override fun onResponse(
                call: Call<List<PullRequest>>,
                response: Response<List<PullRequest>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {


                     adapterPull.listaPullRequest.addAll(it)
//                        val warning = findViewById<View>(R.id.warning_not_pull)
//                        if(adapterPull.itemCount == 0){
//                            warning.visibility = View.GONE
//                        }else{
//                            warning.visibility = View.VISIBLE
//                        }
                     adapterPull.notifyDataSetChanged()                    }
                }
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                Log.d("Erro de chamada api", t.message.toString())
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return false
    }


    override fun OnItemClick(position: Int) {
        val intencao = Intent(Intent.ACTION_VIEW)
        intencao.data = Uri.parse(adapterPull.listaPullRequest[position].user.url_pull)
        startActivity(intencao)


    }


}
