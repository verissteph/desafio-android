package stephanie.com.desafioKotlin.webService


import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import stephanie.com.desafioKotlin.modelo.Endpoint
import stephanie.com.desafioKotlin.modelo.Repositorio

class NetworkingUtils {
    //inicia o retrofit pra aplicaçao
    companion object{
        fun getRetrofitInstance(path:String) : Retrofit {
            val URL_BASE = "https://api.github.com/"
            val retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit

//          val  requestRepositorios  =  retrofit.create(Endpoint::class.java).getRepo()
//           requestRepositorios.enqueue(object : Callback<List<Repositorio>>{
//               override fun onResponse(
//                   call: Call<List<Repositorio>>,
//                   response: Response<List<Repositorio>>
//               ) {
//                   if(!response.isSuccessful){
//                       Log.i("erro","${response.code()}")
//                   }else{
//                        val listaRepo = response.body(); //ta me devolvendo o corpo vazio!!!
//                       for (item: Repositorio in listaRepo){
//                           Log.i("erro","${item.copy()}")
//
//                       }
//
//                   }
//               }
//
//               override fun onFailure(call: Call<List<Repositorio>>, t: Throwable) {
//                Log.e("erro","algo de errado nao esta certo, meu amor: ${t.message}")
//               }
//
//           })
//                return retrofit
        }
    }
}