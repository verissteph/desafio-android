package stephanie.com.desafioKotlin.PullRequest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
    //lateinit var viewModel: PullRequestViewModel

    private val viewModel: PullRequestViewModel by viewModels {
        PullViewModelFactory(Inicializador.start())
    }



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

    //    viewModel = ViewModelProvider(this).get(PullRequestViewModel::class.java)


        //pegando dados da outra activity e recuperando
        owner = intent.getStringExtra(Constants.OWNER).toString()
        repositorio = intent.getStringExtra(Constants.REPOSITORIO).toString()
//        getPulls(owner, repositorio)
         viewModel.get(owner,repositorio)

        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = repositorio
        }
        observeViewModel()
    }
    private fun observeViewModel(){
        viewModel.listaPullLiveData.observe(this){
            when(it){
                is PullState.Success ->{
                    adapterPull.adicionaRepositorio(it.list,this)
                }
                is PullState.Error ->{
                    mostraErro(it.msgError)
                }
                is PullState.Wait->{
                    adapterPull.loading(this)
                }
            }
        }
    }

    fun mostraErro(recursoErro:Int){
        AlertDialog.Builder(this)
            .setMessage(recursoErro)
            .show()

    }

    fun loading(){
        binding.progressBar.visibility = View.VISIBLE
    }

    fun invisibleLoading(){
        binding.progressBar.visibility = View.GONE
    }
//

//    fun getPulls(
//        owner: String,
//        repositorio: String,
//    ) {
//
//        val api = Inicializador.start()
//        binding.progressBar.visibility = View.VISIBLE
//        val chamada = api.getPulls(owner, repositorio)
//        chamada.enqueue(object : Callback<List<PullRequest>> {
//
//            override fun onResponse(
//                call: Call<List<PullRequest>>,
//                response: Response<List<PullRequest>>
//            ) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        adapterPull.listaPullRequest.addAll(it)
//                        adapterPull.notifyDataSetChanged()
//                        binding.progressBar.visibility = View.GONE
//                    }
//                } else {
//                    Toast.makeText(
//                        this@PullRequestsActivity,
//                        "Erro na resposta da API",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
//                Log.d("Erro de chamada api", t.message.toString())
//            }
//
//        })
//    }

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
