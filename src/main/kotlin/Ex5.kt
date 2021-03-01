import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import util.*

fun main(args: Array<String>) {
    exampleOf("Publish Subject") {
        val compositeDisposable = CompositeDisposable()
        val seasonBroadcast = PublishSubject.create<String>()
        seasonBroadcast.onNext(Season1)
        seasonBroadcast.onNext(Season2)
        val subscriber1 = seasonBroadcast.subscribeBy {
            println("Subscription from subscriber 1 , data = $it")
        }

        compositeDisposable.add(subscriber1)

        seasonBroadcast.onNext(Season3)
        seasonBroadcast.onNext(Season4)
        seasonBroadcast.onNext(Season5)
        seasonBroadcast.onNext(Season6)
        seasonBroadcast.onNext(Season7)
        seasonBroadcast.onNext(Season8)
        seasonBroadcast.onNext(Season9)
        compositeDisposable.dispose()

        seasonBroadcast.onNext(Season10)

    }

    exampleOf("Publish Subject sudden completion") {
        val compositeDisposable = CompositeDisposable()
        val seasonBroadcast = PublishSubject.create<String>()
        seasonBroadcast.onNext(Season1)
        seasonBroadcast.onNext(Season2)
        seasonBroadcast.onComplete()
        val subscriber1 = seasonBroadcast.subscribeBy(onNext = {
            println("Subscription from subscriber 1 , data = $it")
        }, onComplete = {
            println("sudden completion")
        }
        )

        compositeDisposable.add(subscriber1)

        seasonBroadcast.onNext(Season3)
        seasonBroadcast.onNext(Season4)
        seasonBroadcast.onNext(Season5)
        seasonBroadcast.onNext(Season6)
        seasonBroadcast.onNext(Season7)
        seasonBroadcast.onNext(Season8)
        seasonBroadcast.onNext(Season9)
        compositeDisposable.dispose()

        seasonBroadcast.onNext(Season10)

    }

    exampleOf("Behaviour Subject") {
        val compositeDisposable = CompositeDisposable()

        val sendBroadcast = BehaviorSubject.createDefault(Season1)

        val sub1 = sendBroadcast.subscribeBy(onNext = {
            println(it)
        }, onComplete = {
            println("completed 1")
        })

        compositeDisposable.add(sub1)
        compositeDisposable.clear()

        sendBroadcast.onNext(Season2)
        val sub2 = sendBroadcast.subscribeBy(onNext = {
            println(it)
        }, onComplete = {
            println("completed 2")
        })

        compositeDisposable.add(sub2)
    }

    exampleOf("Behaviour Subject Sudden Completion") {
        val compositeDisposable = CompositeDisposable()

        val sendBroadcast = BehaviorSubject.createDefault(Season1)
        sendBroadcast.onComplete()
        val sub1 = sendBroadcast.subscribeBy(onNext = {
            println(it)
        }, onComplete = {
            println("completed 1")
        })

        compositeDisposable.add(sub1)
        compositeDisposable.clear()

        val sub2 = sendBroadcast.subscribeBy(onNext = {
            println(it)
        }, onComplete = {
            println("completed 2")
        })

        compositeDisposable.add(sub2)
    }
    exampleOf("Replay subject") {
        val compositeDisposable = CompositeDisposable()

        val subject = ReplaySubject.create<String>(2)

        subject.onNext("event 1")
        subject.onNext("event 2")

        compositeDisposable.add(
            subject.subscribeBy {
                println(it)
            }
        )

        subject.onNext("event 3")

    }

}