package stephanie.com.desafioKotlin.PullRequest

import stephanie.com.desafioKotlin.databinding.ActivityPullRequestsBinding
import stephanie.com.desafioKotlin.modelo.PullRequest
//parece com o Enum, mas nela pode ter varias instancias.
sealed class PullState{
    data class Success(val list: List<PullRequest>) : PullState()
    data class Error(val msgError: Int) : PullState()
    data class Wait(val load:PullRequestsActivity) :PullState()
}
