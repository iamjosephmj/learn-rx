<h1>Learn RX</h1>

<p>Hey What's up everybody. I am Joseph James, In this project you can get a heads up to Rx-kotlin and use it in your Android Apps.
So, What is Rx-Kotlin? Why would you want to use it in your project? </p>

<p>Rx-Kotlin is an asynchronous programming library that is based on using observables. Observables are sequences of data or events that you can
 react to, such as data coming back from a web service, or even taps by the user. you can refer to the 
marble diagrams for getting a better insight about Rx's event
based programming. One thing that you should keep in your mind is that, everything is a sequence in Rx</p>

[marble diagrams playground](https://rxmarbles.com/#mapTo)

<p>As an Android developer, you are no stranger to writing asynchronous and concurrent code in order to keep your app 
snappy and responsive and your user's happy. We are talking about handling things like reacting to:</p>

- Button taps
- Keyboard animation
- Downloading data
- Processing images
- Writing to disk …and lot more.

<p>And there are a lot of alternative in Android SDK like Thread, Runnable, AsyncTask, WorkManager ..etc. This
 is really where Rx-Kotlin comes into play. It allows you to write:</p>

- Declarative
- Functional and Reactive
- Consistent patterns and operators
- Handles mutable state ( Android developers will love this :) )
- Compositional
- Decoupled

<p>The learning curve for Rx is quite steep, this is the reason why developers often chooses other alternatives. The Rx-Kotlin is 
primarily based on two patterns Observable and Observer aka Publisher/subscriber, We will be creating observable sequences and observe 
them in a reactive manner. The primary base type in Rx-Kotlin in <strong>Observable<*></strong> which emits an event containing 
elements. Subscribers can react to each event emitted. Actually, an Observable doesn't emit anything unless it has at least one 
Subscriber.</p>

<p>Observables are Typed, we cannot have multiple types emitted but the same observable. Every time an emitter publishes a new element 
the Subscribers will have an opportunity to do something with the emitted value or react to that event in some other way such as displaying errors(in Android perspective). Each will observable should be terminated 
at some point which will make it stop emitting any more events. The above mentioned is the normal termination of an 
observable, there is an another way by which the observables can be terminated which is when an unexpected error occurring.
</p>

<p>So to recap of what we had seen in observables.</p>

- Next Function () => which emits value
- Complete Function() => to flag the observables as completed.
- Error Function() => to flag if an error has occurred.

<p>Now you have seen a glimpse what event based programming is. Now lets dive into the code</p>

<strong>General idea of upstream and
downstream</strong> [Ex1.kt](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex1.kt)

```Kotlin
  // Upstream
Observable.just("This is my first Rx App")
    // Downstream 
    .subscribe {
        print("Observed Result: \"$it\"")
    }
```

<p>This program consists of two parts:<br>
1. Upstream<br>

```Kotlin
   Observable.just("This is my first Rx App")
```

This part emits an event <b>"This is my first Rx App"</b> of type String.<br>
</p>
<p>
2. Downstream<br>

```Kotlin
   subscribe {
    print("Observed Result: \"$it\"")
}
```

This will collect the event from the upstream and prints them.<br>

Next up, it's time for you to roll up your sleeves and get some more hands on with bread and butter of RxKotlin
</p>

<strong>Creating
Observable</strong> [Ex2.kt](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex2.kt)

<p>
For explaining things in a much simpler manner, lets take the example of Game of thrones :).<br>

1. Creating observable that generates single event with .just()

```Kotlin
val mostPopular: Observable<String> = Observable.just(Season1)
```

2. Creating observable that generates multiple events with .just()

```Kotlin
val fanFav: Observable<String> = Observable.just(Season3, Season4, Season10)
```

3. Creating observable that generates multiple events with .just() on a List

```Kotlin        
val fanFavByList = Observable.just(listOf(Season3, Season4, Season10))
```

4. Creating observable that generates multiple events with .fromIterable

```Kotlin        
/* 
 * Note : the type of the fanFavByIterable will be Observable<String!>! , 
 * dont get this confused with the above one. 
*/

val fanFavByIterable = Observable.fromIterable(listOf(Season3, Season4, Season10))
```

you can also do this with the help of extension functions that rx gives us on lists.

```Kotlin        

val fanFavByIterableAlternative = listOf(Season3, Season4, Season10).toObservable()
```

</p>

<strong>Creating
Subscription</strong> [Ex3.kt](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex3.kt)

<p>
Subscription is one of the prime idea behind generation of observables, there may be certain 
actions that we need to do when an event is generated.

A plain Subscription will look like

```Kotlin
val observable = Observable.just(Season1, Season2, Season3)

observable.subscribe {
    println(it)
}
```

A Subscription doesn't always end up in happy path, there can be error states to, This is how we do it.

```Kotlin
 val observable = Observable.just(Season1, Season2, Season3, Season6)

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
```

the error methods throws a Throwable, now this has one more method named onCompleted. The onCompleted method is for
flagging the subscriber that the Observable had completed its event emission and will no longer emit values.
</p>
<p>
Now you had seen examples of how to emit observables, Sometimes you will want an 
observable that emits no elements, just a completed event... That's Absurd!<br>
We can do that by using .empty(). You have noticed that each observable comes with a type, as 
we have .empty() with no elements, the compiler cannot infer the types. In this case, we can use unit.

```Kotlin
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
```

</p>
<p>
As you can see the from the above example, there were no events generated. but you can 
see a completed event. There is one way by which you can emit nothing using the never operator.

```Kotlin
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
```

</p>
<p>
Now, what does a subscription return -> Disposable

if you want to cancel a subscription at a point of time, you can use .dispose()
function.

```Kotlin
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
```

The key idea behind the observables are to avoid memory leaks.

what if you have multiple disposable to be disposed, RxKotlin has a method to handle this issue -- CompositeDisposable

```Kotlin
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
```

</p>

<strong>Using Create
operator</strong> [Ex4.kt](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex4.kt)

<p>
we can use .create() operator for creating an observable

```Kotlin

val observable = Observable.create<String> { emitter ->
    // onNext() can be called any number of times.
    emitter.onNext("Event 1")
    emitter.onNext("Event 2")
    emitter.onNext("Event 3")
    emitter.onComplete()
}
observable.subscribeBy(
    onNext = {
        print(it)
    },
    onError = {

    },
    onComplete = {

    }
)


//Error condition

val observable = Observable.create<String> { emitter ->
    emitter.onNext("Event 1")
    emitter.onNext("Event 2")
    // This will only be triggered once.
    emitter.onError(Error.MyError())
    emitter.onNext("Event 3")
    emitter.onComplete()

}
observable.subscribeBy(
    onNext = {
        print(it)
    },
    onError = {
        print(it)
    },
    onComplete = {

    }
)
```

There are some other reactive elements other than Observables, they are:
<br>
1. <b>Single</b> : Can emit one Error or one Completed event (you can see the implementation of single in combination with Retrofit)
<br>   
2. <b>Completable</b>: Can return one Error or one Completed event
3. <b>Maybe</b>: Can emit either one Next/Error/Completed event
<br>
<br>
<b>Creating a Single</b>
   
```Kotlin
        val single = Single.create<String> { emitter ->
            emitter.onSuccess("Completed Successfully")
        }
        single.subscribeBy(
            onSuccess = {
                println(it)
            },
            onError = {

            }
        )
```   
</p>

