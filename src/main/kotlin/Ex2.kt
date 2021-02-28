import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable
import util.*

fun main(args: Array<String>) {
    exampleOf("creating observables") {
        val mostPopular: Observable<String> = Observable.just(Season1)
        // Type can be automatically inferred by the compiler.
        val fanFav = Observable.just(Season3, Season4, Season10)

        // List involved operations.
        val fanFavByList = Observable.just(listOf(Season3, Season4, Season10))
        val fanFavByIterable = Observable.fromIterable(listOf(Season3, Season4, Season10))
        val fanFavByFromList = listOf(Season3, Season4, Season10).toObservable()

    }
}