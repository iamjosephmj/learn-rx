/*
* Learn-Rx
*
* Copyright (c) 2021 Joseph James
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/
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

    exampleOf("reduce") {
        val subscription = CompositeDisposable()
        // Only the final result will be printed, refer to the marble diagram.
        subscription.add(
            Observable.fromIterable(listOf(1, 2, 3, 4, 5))
                .reduce { a, b ->
                    a + b
                }
                .subscribeBy {
                    println(it)
                }
        )
    }


    exampleOf("scan") {
        val subscription = CompositeDisposable()
        // Only the final result will be printed, refer to the marble diagram.
        subscription.add(
            Observable.fromIterable(listOf(1, 2, 3, 4, 5))
                .scan { a, b ->
                    a + b
                }
                .subscribeBy {
                    println(it)
                }
        )
    }

    exampleOf("zip + scan") {
        val subscription = CompositeDisposable()


        val ob1 = Observable.fromIterable(listOf(1, 2, 3, 4, 5))
        val ob2 = Observable.fromIterable(listOf("a", "b", "c", "d", "e"))

        val ob3 = ob2.scan { t1, t2 ->
            "$t1 + $t2"
        }

        subscription.add(
            Observable.zip(ob1, ob2, ob3) { one, two, three ->
                Triple(one, two, three)
            }
                .subscribeBy {
                    println(it.toString())
                }

        )
    }


}