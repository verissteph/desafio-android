package stephanie.com.desafioKotlin.PullRequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import stephanie.com.desafioKotlin.webService.Endpoint

class PullViewModelFactory(private val chamada:Endpoint): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PullRequestViewModel::class.java)) {
            return PullRequestViewModel(chamada,init = null,adapter = null) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}

