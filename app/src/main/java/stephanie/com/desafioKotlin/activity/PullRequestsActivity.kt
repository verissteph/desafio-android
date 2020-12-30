//package stephanie.com.desafioKotlin.activity
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.util.Log
//import android.view.MenuItem
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import kotlinx.coroutines.*
//import stephanie.com.desafioKotlin.R
//import stephanie.com.desafioKotlin.adapter.PullRequestAdapter
//import stephanie.com.desafioKotlin.modelo.*
//import stephanie.com.desafioKotlin.webService.EndpointRepo
//
//
//class PullRequestsActivity : AppCompatActivity(), PullRequestAdapter.OnItemClickListener {
//    // private val itemPullRequest = getData()
//    // private val adapter = PullRequestAdapter(itemPullRequest)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_pull_requests)
//        val recycler_pull_request = findViewById<RecyclerView>(R.id.recycler_pull_request)
//        recycler_pull_request?.layoutManager = LinearLayoutManager(this)
//        recycler_pull_request.setHasFixedSize(true)
//        recycler_pull_request?.adapter = PullRequestAdapter(this)
//
//
//        getData() { pullRequestsData ->
//            if (pullRequestsData != null) (recycler_pull_request?.adapter as? PullRequestAdapter)?.updateList(pullRequestsData)
//        }
//
//
//        setSupportActionBar(findViewById(R.id.toolBar))
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> finish()
//        }
//        return false
//    }
//
//
//    private var job: Job? = null
//    private fun getData(callback: (List<ItemPullRequest>?) -> Unit) {
//        val criador = intent.extras?.getString("criador").toString()
//        val repositorio = intent.extras?.getString(
//            "repositorio"
//        ).toString()
//        val URL_BASE = "https://api.github.com/$criador/$repositorio"
//        val retrofitClient =
//            NetworkingUtils.getRetrofitInstance(URL_BASE)
//        val repoService = retrofitClient.create(EndpointRepo::class.java)
//        job?.cancel()
//        job = null
//        job = CoroutineScope(Dispatchers.IO).launch {
//            val response = repoService.getPulls(criador, repositorio)
//            if (response.isSuccessful) {
//                Log.i("resposta:", " ${response.body()}")
//
//                withContext(Dispatchers.Main) {
//                    callback(response.body())
//                          }
//
//            } else {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        this@PullRequestsActivity,
//                        "${response.errorBody()}",
//                        Toast.LENGTH_LONG
//                    ).show()
//                    callback(null)
//                }
//            }
//        }
//    }
//
//
//    override fun OnItemClick(item: ItemPullRequest) {
//        val url = item.pull_request_url
//        val intencao = Intent(Intent.ACTION_VIEW)
//        intencao.data = Uri.parse(url)
//        startActivity(intencao)
//    }
//
//
//}
