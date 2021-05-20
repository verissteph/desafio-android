package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName

data class UserPullRequest(
    @SerializedName("login")
    val nomePull: String,
    @SerializedName("avatar_url")
    val fotoPull: String,
    @SerializedName("html_url")
    val urlPull: String,
)