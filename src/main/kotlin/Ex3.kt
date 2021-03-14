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