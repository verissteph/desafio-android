package stephanie.com.desafioKotlin.PullRequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import stephanie.com.desafioKotlin.modelo.PullRequest
import stephanie.com.desafioKotlin.webService.Endpoint

class PullRequestViewModel(private val chamada: Endpoint) : ViewModel() {

    var _listaPullLiveData: MutableLiveData<List<PullRequest>> = MutableLiveData()
    val listaPullLiveData: LiveData<List<PullRequest>> = _listaPullLiveData

    //passar o getPulss() pra ca, achar uma maneira de fazer o loading funfar


}