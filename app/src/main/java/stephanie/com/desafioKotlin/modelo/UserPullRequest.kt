package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName

data class UserPullRequest(
    @SerializedName("login")
    val nome_pull: String,
    @SerializedName("avatar_url")
    val foto_pull: String,
    @SerializedName("html_url")
    val url_pull: String,
)