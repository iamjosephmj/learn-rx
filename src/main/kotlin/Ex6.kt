import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import util.exampleOf

fun main(args: Array<String>) {

    exampleOf("ignoreElements") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(

            publishSubject
                // Returns completable
                .ignoreElements()
                .subscribeBy {
                    println("completed")
                }

        )


        publishSubject.onNext(1)
        publishSubject.onNext(2)
        publishSubject.onNext(3)

        publishSubject.onComplete()
    }

    exampleOf("elementAt") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(

            publishSubject
                // Returns completable
                .elementAt(2)
                .subscribeBy {
                    println(it)
                }

        )


        publishSubject.onNext(1)
        publishSubject.onNext(2)
        publishSubject.onNext(3)

        publishSubject.onComplete()
    }

    exampleOf("filter") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(

            publishSubject
                // Returns completable
                .filter {
                    it > 20
                }
                .subscribeBy {
                    println(it)
                }

        )


        publishSubject.onNext(10)
        publishSubject.onNext(20)
        publishSubject.onNext(30)
        publishSubject.onNext(40)
        publishSubject.onNext(50)
        publishSubject.onNext(60)
        publishSubject.onNext(70)

        publishSubject.onComplete()

    }

}