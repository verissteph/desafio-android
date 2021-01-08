package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName
import java.util.*

data class PullRequest(
    @SerializedName("title")
    val titulo_pull: String,
    @SerializedName("body")
    val corpo_pull: String,
    @SerializedName("created_at")
    val criacao_pull: String,
    @SerializedName("user")
    val user: UserPullRequest,
)