package stephanie.com.desafioKotlin.PullRequest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.Utils.Constants
import stephanie.com.desafioKotlin.databinding.ActivityPullRequestsBinding
import stephanie.com.desafioKotlin.modelo.PullRequest
import stephanie.com.desafioKotlin.webService.Inicializador


class PullRequestsActivity : AppCompatActivity(), PullRequestAdapter.OnItemClickListener {
    val listaPull = ArrayList<PullRequest>()
    private val adapterPull = PullRequestAdapter(listaPull, this)
    var owner = ""
    var repositorio = ""
    lateinit var binding: ActivityPullRequestsBinding
    lateinit var viewModel: PullRequestViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val linearLayout = LinearLayoutManager(this)
        binding.apply {
            recyclerPullRequest.layoutManager = linearLayout
            recyclerPullRequest.setHasFixedSize(true)
            recyclerPullRequest.adapter = adapterPull
        }

        viewModel = ViewModelProvider(this).get(PullRequestViewModel::class.java)

        //pegando dados da outra activity e recuperando
        owner = intent.getStringExtra(Constants.OWNER).toString()
        repositorio = intent.getStringExtra(Constants.REPOSITORIO).toString()
        getPulls(owner, repositorio)

        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = repositorio
        }

    }


    fun getPulls(
        owner: String,
        repositorio: String,
    ) {

        val api = Inicializador.start()
        binding.progressBar.visibility = View.VISIBLE
        val chamada = api.getPulls(owner, repositorio)
        chamada.enqueue(object : Callback<List<PullRequest>> {

            override fun onResponse(
                call: Call<List<PullRequest>>,
                response: Response<List<PullRequest>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapterPull.listaPullRequest.addAll(it)
                        adapterPull.notifyDataSetChanged()
                        binding.progressBar.visibility = View.GONE
                    }
                } else {
                    Toast.makeText(
                        this@PullRequestsActivity,
                        "Erro na resposta da API",
                        Toast.LENGTH_SHORT
                    ).show()
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
        val intencao = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(adapterPull.listaPullRequest[position].user.urlPull)
        }
        startActivity(intencao)


    }


}
