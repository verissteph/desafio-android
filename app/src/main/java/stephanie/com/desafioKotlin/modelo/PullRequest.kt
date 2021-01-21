package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName
import java.util.*

data class PullRequest(
    @SerializedName("title")
    val tituloPull: String,
    @SerializedName("body")
    val corpoPull: String,
    @SerializedName("created_at")
    val criacaoPull: String,
    @SerializedName("user")
    val user: UserPullRequest,
)