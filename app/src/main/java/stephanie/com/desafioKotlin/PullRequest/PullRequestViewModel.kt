package stephanie.com.desafioKotlin.PullRequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import stephanie.com.desafioKotlin.R
import stephanie.com.desafioKotlin.modelo.PullRequest
import stephanie.com.desafioKotlin.webService.Endpoint
import stephanie.com.desafioKotlin.webService.Inicializador

class PullRequestViewModel(private val chamada: Endpoint,
                           private val init: Inicializador?,
                           private val adapter: PullRequestAdapter?
                           ) : ViewModel() {
     var _listaPullLiveData: MutableLiveData<PullState> = MutableLiveData()
    val listaPullLiveData: LiveData<PullState> = _listaPullLiveData

    //passar o getPulss() pra ca, achar uma maneira de fazer o loading funfar

    //n ta funcionando o load :/

    fun get(
        owner: String,
        repositorio: String,
    ) {
        init?.start()
        adapter?.loading(PullRequestsActivity())
        chamada.getPulls(owner, repositorio)
            .enqueue(object : Callback<List<PullRequest>>{
                override fun onResponse(
                    call: Call<List<PullRequest>>,
                    response: Response<List<PullRequest>>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let{
                          _listaPullLiveData.postValue(PullState.Success(it))
                        }
                    }else{
                        _listaPullLiveData.postValue(PullState.Error(R.string.erro))
                    }
                }

                override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                    _listaPullLiveData.postValue(PullState.Error(R.string.erro_requi))
                }

            })


    }


}