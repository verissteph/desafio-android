package stephanie.com.desafioKotlin.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.adapter.PullRequestAdapter
import stephanie.com.desafioKotlin.modelo.*
import stephanie.com.desafioKotlin.webService.EndpointRepo


class PullRequestsActivity : AppCompatActivity(), PullRequestAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)
        val recycler_pull_request = findViewById<RecyclerView>(R.id.recycler_pull_request)
        recycler_pull_request?.layoutManager = LinearLayoutManager(this)
        recycler_pull_request.setHasFixedSize(true)
        recycler_pull_request?.adapter = PullRequestAdapter(this)

        setSupportActionBar(findViewById(R.id.toolBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return false
    }

    override fun OnItemClick(item: ItemPullRequest) {
        val url = item.pull_request_url
        val intencao = Intent(Intent.ACTION_VIEW)
        intencao.data = Uri.parse(url)
        startActivity(intencao)
    }


}
