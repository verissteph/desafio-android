package stephanie.com.desafioKotlin.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.adapter.PullRequestAdapter
import stephanie.com.desafioKotlin.modelo.ItemPullRequest


class PullRequestsActivity: AppCompatActivity() {
    private val itemPullRequest = geraLista(100)
    private val adapter = PullRequestAdapter(itemPullRequest)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)
       val recycler_pull_request = findViewById<RecyclerView>(R.id.recycler_pull_request)
        recycler_pull_request?.adapter = adapter
        recycler_pull_request?.layoutManager = LinearLayoutManager(this)
        recycler_pull_request.setHasFixedSize(true)


        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home->finish()
        }
        return false
    }

    private fun geraLista(size:Int):List<ItemPullRequest>{
        val lista = ArrayList<ItemPullRequest>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_usuario
                else -> R.drawable.ic_usuario
            }
            val item = ItemPullRequest("O preço da vitoria $i",
            "um filme que retrata que para alguns vale tudo para alcaçar os objetivos v$i",
            240,
            "alanaHong",
            "filme",
            "20/12/2020")
            lista+=item
        }
            return lista
    }
}
