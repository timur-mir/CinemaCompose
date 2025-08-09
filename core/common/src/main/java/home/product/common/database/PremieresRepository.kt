package home.product.common.database

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PremieresRepository@Inject constructor( val dao:PremieresDao)  {
    @OptIn(DelicateCoroutinesApi::class)
    val job=Job()
    private val scope =  CoroutineScope(Dispatchers.IO+job)

    suspend fun savePremieres(data:List<PremieresEntity>){
        scope.launch { dao.insertPremieres(data)}
    }
//    suspend fun savePremiere(data:PremieresEntity){
//        dao.insertPremiere(data)
//    }

    suspend fun getAllPremieres()=withContext(scope.coroutineContext){
        dao.getPremieres()
    }

    suspend fun existItem(id:Int)=withContext(scope.coroutineContext)
    { dao.exists(id)}
}