import io.reactivex.rxjava3.core.Observable
import util.exampleOf

fun main(args: Array<String>) {
    exampleOf("General idea of upstream and downstream") {
        Observable.just("This is my first Rx App")
            .subscribe {
                print("Observed Result: \"$it\"")
            }
    }
}