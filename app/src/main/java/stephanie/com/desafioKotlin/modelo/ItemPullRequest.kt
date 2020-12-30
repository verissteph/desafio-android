package stephanie.com.desafioKotlin.modelo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

data class ItemPullRequest(
        val pull_request_titulo:String,
        val pull_request_descricao:String,
        val foto_usuario_pull_request:Int,
        val username_pull_request:String,
        val pull_request_nome:String,
        val pull_request_date:String,
        val pull_request_url:String,
)
