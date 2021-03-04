import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import util.exampleOf
import java.util.concurrent.TimeUnit

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


    exampleOf("skip") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(

            publishSubject
                // Returns completable
                .skip(2)
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

    exampleOf("take") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(

            publishSubject
                // Returns completable
                .take(2)
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

    exampleOf("skipWhile") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(

            publishSubject
                // Returns completable
                .skipWhile {
                    it < 50 ||
                            it > 60
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

    exampleOf("takeWhile") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(

            publishSubject
                // Returns completable
                .takeWhile {
                    it < 50 ||
                            it > 60
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

    exampleOf("skipUntil") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        val trigger = PublishSubject.create<Unit>()

        subscription.add(
            publishSubject
                // Returns completable
                .skipUntil(trigger)
                .subscribeBy {
                    println(it)
                }

        )


        publishSubject.onNext(10)
        publishSubject.onNext(20)
        publishSubject.onNext(30)

        trigger.onNext(Unit)

        publishSubject.onNext(40)
        publishSubject.onNext(50)
        publishSubject.onNext(60)
        publishSubject.onNext(70)

        publishSubject.onComplete()

    }

    exampleOf("takeUntil") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        val trigger = PublishSubject.create<Unit>()

        subscription.add(
            publishSubject
                // Returns completable
                .takeUntil(trigger)
                .subscribeBy {
                    println(it)
                }

        )


        publishSubject.onNext(10)
        publishSubject.onNext(20)
        publishSubject.onNext(30)

        trigger.onNext(Unit)

        publishSubject.onNext(40)
        publishSubject.onNext(50)
        publishSubject.onNext(60)
        publishSubject.onNext(70)

        publishSubject.onComplete()

    }

    exampleOf("distinctUntilChanged") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(
            publishSubject
                // Returns completable
                .distinctUntilChanged()
                .subscribeBy {
                    println(it)
                }

        )


        publishSubject.onNext(10)
        publishSubject.onNext(20)
        publishSubject.onNext(20)
        publishSubject.onNext(30)
        publishSubject.onNext(40)
        publishSubject.onNext(40)
        publishSubject.onNext(50)
        publishSubject.onNext(60)
        publishSubject.onNext(70)
        publishSubject.onNext(40)

        publishSubject.onComplete()

    }


    exampleOf("distinct") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(
            publishSubject
                // Returns completable
                .distinct()
                .subscribeBy {
                    println(it)
                }

        )


        publishSubject.onNext(10)
        publishSubject.onNext(20)
        publishSubject.onNext(20)
        publishSubject.onNext(30)
        publishSubject.onNext(40)
        publishSubject.onNext(40)
        publishSubject.onNext(50)
        publishSubject.onNext(60)
        publishSubject.onNext(70)
        publishSubject.onNext(40)

        publishSubject.onComplete()

    }


    exampleOf("debounceTime") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(
            publishSubject
                // Returns completable
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeBy {
                    println(it)
                }

        )

        Thread.sleep(1500)
        publishSubject.onNext(10)
        Thread.sleep(1500)
        publishSubject.onNext(20)
        Thread.sleep(1500)
        publishSubject.onNext(20)
        Thread.sleep(1500)
        publishSubject.onNext(30)
        Thread.sleep(1500)
        publishSubject.onNext(40)
        Thread.sleep(10)
        publishSubject.onNext(40)
        Thread.sleep(10)
        publishSubject.onNext(50)
        Thread.sleep(10)
        publishSubject.onNext(60)
        Thread.sleep(1500)
        publishSubject.onNext(70)
        Thread.sleep(10)
        publishSubject.onComplete()

    }


    exampleOf("throttleFirst") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(
            publishSubject
                // Returns completable
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribeBy {
                    println(it)
                }

        )

        Thread.sleep(1500)
        publishSubject.onNext(10)
        Thread.sleep(1500)
        publishSubject.onNext(20)
        Thread.sleep(1500)
        publishSubject.onNext(20)
        Thread.sleep(1500)
        publishSubject.onNext(30)
        Thread.sleep(1500)
        publishSubject.onNext(40)
        Thread.sleep(10)
        publishSubject.onNext(40)
        Thread.sleep(10)
        publishSubject.onNext(50)
        Thread.sleep(10)
        publishSubject.onNext(60)
        Thread.sleep(1500)
        publishSubject.onNext(70)
        Thread.sleep(10)
        publishSubject.onComplete()

    }

    exampleOf("throttleLatest") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(
            publishSubject
                // Returns completable
                .throttleLatest(1000, TimeUnit.MILLISECONDS)
                .subscribeBy {
                    println(it)
                }

        )

        Thread.sleep(1500)
        publishSubject.onNext(10)
        Thread.sleep(1500)
        publishSubject.onNext(20)
        Thread.sleep(1500)
        publishSubject.onNext(20)
        Thread.sleep(1500)
        publishSubject.onNext(30)
        Thread.sleep(1500)
        publishSubject.onNext(40)
        Thread.sleep(10)
        publishSubject.onNext(40)
        Thread.sleep(10)
        publishSubject.onNext(50)
        Thread.sleep(10)
        publishSubject.onNext(60)
        Thread.sleep(1500)
        publishSubject.onNext(70)
        Thread.sleep(10)
        publishSubject.onComplete()

    }

    exampleOf("first") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(
            publishSubject
                // Returns completable
                .first(/* default value*/0)
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


    exampleOf("last") {
        val subscription = CompositeDisposable()

        val publishSubject = PublishSubject.create<Int>()

        subscription.add(
            publishSubject
                // Returns completable
                .last(/* default value*/0)
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