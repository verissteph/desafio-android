package stephanie.com.desafioKotlin.webService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InicializadorAPIPull {
    fun startPull() : EndpointPull{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EndpointPull::class.java)
    }
}