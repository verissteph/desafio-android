package stephanie.com.desafioKotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import stephanie.com.desafioKotlin.databinding.ActivityListaBinding


class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repositorios = listOf("repo 1","repo 2", "repo 3","repo 4")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, repositorios)
        val listaRepositorios = binding.listaRepositorios
        listaRepositorios.adapter = adapter


            binding.listaRepositorios.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
                val intencao = Intent(this,PullRequestsActivity::class.java)
                startActivity(intencao)
            }
    }
}