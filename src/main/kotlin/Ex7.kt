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