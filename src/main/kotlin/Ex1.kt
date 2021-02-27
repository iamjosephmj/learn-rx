import io.reactivex.rxjava3.core.Observable

fun main(args: Array<String>) {
    Observable.just("This is my first Rx App")
        .subscribe {
            print("Observed Result: \"$it\"")
        }
}