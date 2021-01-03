package stephanie.com.desafioKotlin.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.adapter.PullRequestAdapter
import stephanie.com.desafioKotlin.modelo.ItemPullRequest
import stephanie.com.desafioKotlin.modelo.PullRequest
import stephanie.com.desafioKotlin.webService.InicializadorAPIPull
import stephanie.com.desafioKotlin.webService.InicializadorAPIRepo
import javax.security.auth.callback.Callback


class PullRequestsActivity : AppCompatActivity(), PullRequestAdapter.OnItemClickListener {
private val usuario_pull by lazy {
  InicializadorAPIPull.startPull()
}
    private val adapterPull = PullRequestAdapter(ArrayList(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        val recycler_pull_request = findViewById<RecyclerView>(R.id.recycler_pull_request)

        recycler_pull_request?.layoutManager = LinearLayoutManager(this)
        recycler_pull_request.setHasFixedSize(true)
        recycler_pull_request?.adapter = PullRequestAdapter(ArrayList(), this)

//       usuario_pull.getPulls().enqueue(object : retrofit2.Callback<ItemPullRequest>{
//
//       })
        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return false
    }

//    override fun OnItemClick(item: ItemPullRequest) {
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
