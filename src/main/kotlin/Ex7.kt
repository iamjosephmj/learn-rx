import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import util.exampleOf

fun main(args: Array<String>) {


    exampleOf("map") {

        val disposable = CompositeDisposable()

        disposable.add(
            Observable.just("1", "2", "3", "4")
                .map {
                    it.toInt()
                }
                .subscribeBy {
                    println(it)
                }
        )

    }

    exampleOf("flatMap") {
        /*
        * this will be very useful when you combine API calls.
         */
        val disposable = CompositeDisposable()

        data class O(
            val data: BehaviorSubject<Int>
        )

        val sub1 = BehaviorSubject.createDefault(10)

        val sub2 = BehaviorSubject.createDefault(10)

        val mainOb = PublishSubject.create<O>()

        disposable.add(
            mainOb
                .flatMap {
                    it.data
                }
                .subscribeBy {
                    println(it)
                }
        )

        mainOb.onNext(O(sub1))

        sub1.onNext(10)
        sub1.onNext(30)

        mainOb.onNext(O(sub2))

        sub1.onNext(10)
        sub1.onNext(30)

        sub2.onNext(100)
        sub2.onNext(300)


    }

    exampleOf("switchMap") {
        /*
        * this will be very useful when you combine API calls.
        */
        val disposable = CompositeDisposable()

        data class O(
            val data: BehaviorSubject<Int>
        )

        val sub1 = BehaviorSubject.createDefault(10)

        val sub2 = BehaviorSubject.createDefault(10)

        val mainOb = PublishSubject.create<O>()

        disposable.add(
            mainOb
                .switchMap {
                    it.data
                }
                .subscribeBy {
                    println(it)
                }
        )

        mainOb.onNext(O(sub1))

        sub1.onNext(10)
        sub1.onNext(30)

        mainOb.onNext(O(sub2))

        sub1.onNext(10)
        sub1.onNext(30)

        sub2.onNext(100)
        sub2.onNext(300)


    }
}