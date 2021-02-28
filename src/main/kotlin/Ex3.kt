import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import util.*

fun main(args: Array<String>) {
    exampleOf("Subscription Example") {
        val observableOnlySub = Observable.just(Season1, Season2, Season3)
        val observable = Observable.just(Season1, Season2, Season3, Season6)

        observableOnlySub.subscribe {
            println(it)
        }

        observable.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {
                println(it)
            },
            onComplete = {
                println("completed")
            }
        )
    }
    exampleOf("Empty") {
        val observable = Observable.empty<Unit>()

        observable.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {
                println(it)
            },
            onComplete = {
                println("completed")
            }
        )
    }

    exampleOf("Never") {
        val observable = Observable.never<Any>()

        observable.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {
                println(it)
            },
            onComplete = {
                println("completed")
            }
        )
    }

    exampleOf("Disposable") {
        val observable = Observable.never<Any>()

        val sub = observable.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {
                println(it)
            },
            onComplete = {
                println("completed")
            }
        )

        sub.dispose()
    }

    exampleOf("CompositeDisposable") {
        val compositeDisposable = CompositeDisposable()

        val observable1 = Observable.never<Any>()
        val observable2 = Observable.never<Any>()
        val observable3 = Observable.never<Any>()

        compositeDisposable.add(observable1.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {
                println(it)
            },
            onComplete = {
                println("completed")
            }
        ))
        compositeDisposable.add(observable2.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {
                println(it)
            },
            onComplete = {
                println("completed")
            }
        ))

        compositeDisposable.add(observable3.subscribeBy(
            onNext = {
                println(it)
            },
            onError = {
                println(it)
            },
            onComplete = {
                println("completed")
            }
        ))

        compositeDisposable.dispose()
    }

}