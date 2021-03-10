import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import util.exampleOf

fun main(args: Array<String>) {
    exampleOf("startWith") {
        val compositeDisposable = CompositeDisposable()

        val observable1 = Observable.just(2, 3, 4, 5)

        val observable2 = observable1.startWith(Observable.just(1))
        compositeDisposable.add(

            observable2.subscribeBy {
                println(it)
            }
        )

    }

    exampleOf("concat") {
        val compositeDisposable = CompositeDisposable()

        val observable1 = Observable.just(2, 3, 4, 5)

        val observable2 = Observable.concat(Observable.just(1), observable1)
        compositeDisposable.add(

            observable2.subscribeBy {
                println(it)
            }
        )
    }


    exampleOf("concatWith") {
        val compositeDisposable = CompositeDisposable()

        val observable1 = Observable.just(2, 3, 4, 5)

        compositeDisposable.add(

            observable1
                .concatWith(Observable.just(6))
                .subscribeBy {
                    println(it)
                }
        )
    }

    exampleOf("mergeWith") {
        val compositeDisposable = CompositeDisposable()

        val sub1 = PublishSubject.create<Int>()
        val sub2 = PublishSubject.create<Int>()

        compositeDisposable.add(
            sub1
                .mergeWith(sub2)
                .subscribeBy {
                    println(it)
                }
        )

        sub1.onNext(1)
        sub2.onNext(2)
        sub1.onNext(3)
        sub2.onNext(4)
        sub1.onNext(5)
        sub2.onNext(6)

        sub1.onComplete()
        sub2.onNext(7)
        sub2.onComplete()

    }

    exampleOf("combineLatest") {

        val disposable = CompositeDisposable()

        val subject1 = PublishSubject.create<String>()
        val subject2 = PublishSubject.create<Int>()

        disposable.add(

            Observable.combineLatest(subject1, subject2, { one, two ->
                "$one and $two"
            })
                .subscribeBy {
                    println(it)
                }

        )

        subject1.onNext("one")
        subject2.onNext(1)
        subject1.onNext("two")
        subject2.onNext(2)

    }

    exampleOf("zip") {
        val disposable = CompositeDisposable()

        val subject1 = PublishSubject.create<String>()
        val subject2 = PublishSubject.create<Int>()

        disposable.add(

            Observable.zip(subject1, subject2, { one, two ->
                "$one and $two"
            })
                .subscribeBy {
                    println(it)
                }
        )

        subject1.onNext("one")
        subject2.onNext(1)
        subject1.onNext("two")
        subject2.onNext(2)

        subject1.onComplete()
        subject2.onComplete()
    }

    exampleOf("amb") {
        val disposable = CompositeDisposable()

        val subject1 = PublishSubject.create<String>()
        val subject2 = PublishSubject.create<String>()

        disposable.add(

            subject1.ambWith(subject2)
                .subscribeBy {
                    println(it)
                }
        )

        subject1.onNext("one")
        subject2.onNext("1")
        subject1.onNext("two")
        subject2.onNext("2")
    }
}