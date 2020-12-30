package stephanie.com.desafioKotlin.webService


import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import stephanie.com.desafioKotlin.modelo.Repositorio

class NetworkingUtils {
    //inicia o retrofit pra aplicaçao
    companion object{
        fun getRetrofitInstance(path:String) : Retrofit {
            val URL_BASE = "https://api.github.com/"
            val retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
            return retrofit

        }
    }
}