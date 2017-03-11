package io.reactivex.rxkotlin.examples.retrofit

import io.reactivex.Observable
import retrofit.RestAdapter
import retrofit.http.GET
import retrofit.http.Query

data class SearchResultEntry(val id : String, val latestVersion : String)
data class SearchResults(val docs : List<SearchResultEntry>)
data class MavenSearchResponse(val response : SearchResults)

interface MavenSearchService {
    @GET("/solrsearch/select?wt=json")
    fun search(@Query("q") s : String, @Query("rows") rows : Int = 20) : Observable<MavenSearchResponse>
}

fun main(args: Array<String>) {
    val service = RestAdapter.Builder().
            setEndpoint("http://search.maven.org").
            build().
            create(MavenSearchService::class.java)

    service.search("rxkotlin").
            flatMapIterable { it.response.docs }.
            doAfterTerminate { System.exit(0) }.   // we need this otherwise Rx's executor service will shutdown a minute after request completion
            subscribe { artifact ->
                println("${artifact.id} (${artifact.latestVersion})")
            }
}