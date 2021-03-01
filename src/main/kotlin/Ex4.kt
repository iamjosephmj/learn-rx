import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import util.exampleOf

sealed class Error {
    class MyError : Throwable()
}

fun main(args: Array<String>) {

    exampleOf("Example Of .create()") {
        val observable = Observable.create<String> { emitter ->
            emitter.onNext("Event 1")
            emitter.onNext("Event 2")
            emitter.onNext("Event 3")
            emitter.onComplete()

        }
        observable.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {

            },
            onComplete = {

            }
        )
    }

    exampleOf("Example Of .create() with error") {
        val observable = Observable.create<String> { emitter ->
            emitter.onNext("Event 1")
            emitter.onNext("Event 2")
            emitter.onError(Error.MyError())
            emitter.onNext("Event 3")
            emitter.onComplete()

        }
        observable.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {
                println(it)
            },
            onComplete = {

            }
        )
    }

    exampleOf("Single") {
        val single = Single.create<String> { emitter ->
            emitter.onSuccess("Completed Successfully")
//            emitter.onError(Error.MyError())
        }
        single.subscribeBy(
            onSuccess = {
                println(it)
            },
            onError = {

            }
        )
    }

}