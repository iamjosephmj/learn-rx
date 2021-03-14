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