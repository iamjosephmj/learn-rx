import io.reactivex.rxjava3.core.Observable
import util.*

fun main(args: Array<String>) {
    exampleOf("creating observables") {
        val mostPopular: Observable<String> = Observable.just(Season1)
        // Type can be automatically inferred by the compiler.
        val fanFav = Observable.just(Season3, Season4, Season10)
        val fanFavByList = Observable.just(listOf(Season3, Season4, Season10))
        val fanFavByIterable = Observable.fromIterable(listOf(Season3, Season4, Season10))
    }
}