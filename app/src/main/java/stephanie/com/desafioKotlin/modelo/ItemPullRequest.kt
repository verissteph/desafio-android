package stephanie.com.desafioKotlin.modelo

data class ItemPullRequest(
        val pull_request_titulo:String,
        val pull_request_descricao:String,
        val foto_usuario_pull_request:Int,
        val username_pull_request:String,
        val pull_request_nome:String,
        val pull_request_date:String,
)