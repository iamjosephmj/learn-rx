<p align="center">
  <img src="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/rx-kotlin.png" />
</p>

# Learn RX

[![License MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=flat)]()
[![Public Yes](https://img.shields.io/badge/Public-yes-green.svg?style=flat)]()

## Heads up

<p>Hey What's up everybody. I am Joseph James, In this project you can get a heads up to Rx-kotlin and use it in your Android Apps.

## Table of contents

* [Introduction](#Introduction)
* [General idea about Observables](#General-idea-about-Observables)
* [Upstream and Downstream](#upstream-and-downstream-ex1kt)
    * [Upstream](#Upstream)
    * [Downstream](#Downstream)
* [Creating Observable](#Creating-Observable-ex2kt)
* [Creating Subscription](#Creating-Subscription-ex3kt)
* [Using Create operator](#Using-Create-operator-ex4kt)
    * [Single](#Single)
    * [Completable](#Completable)
    * [Maybe](#Maybe)
* [Subject and non ending sequences of streams](#Subject-and-non-ending-sequences-of-streams-ex5kt)
    * [Publish Subject](#Publish-Subject)
    * [Behaviour Subject](#Behaviour-Subject)
    * [Replay](#Replay)
* [Filtering Operators](#Operators-ex6kt)
    * [ignoreElements()](#ignoreElements)
    * [elementAt()](#elementAt)
    * [filter()](#filter)
    * [skip()](#skip)
    * [skipWhile()](#skipWhile)
    * [take()](#take())
    * [takeWhile()](#takeWhile)
    * [skipUntil()](#skipUntil)
    * [takeUntil()](#takeUntil)
    * [distinctUntilChanged()](#distinctUntilChanged)
    * [distinct()](#distinct)
    * [debounceTime()](#debounceTime)
    * [share()](#share)
* [Schedulers](#Schedulers)
    * [How to specify schedulers](#How-to-specify-schedulers)
* [Transforming Operators](#transforming-operators-ex7kt)
    * [Map](#Map)
    * [Flat Map](#FlatMap)
    * [Switch Map](#SwitchMap)
* [Combining Operators](#Combining-operators-ex8kt)
    * [startWith](#startWith)
    * [combine](#combine)
    * [combineWith](#combineWith)
    * [merge](#merge)
    * [combineLatest](#combineLatest)

## Introduction

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

## General idea about Observables

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

## Upstream and Downstream <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex1.kt">`Ex1.kt`</a>

```Kotlin
  // Upstream
Observable.just("This is my first Rx App")
    // Downstream 
    .subscribe {
        print("Observed Result: \"$it\"")
    }
```

<p>This program consists of two parts:<br>
</p>

#### Upstream

```Kotlin

Observable.just("This is my first Rx App")

```

<p>
This part emits an event <b>"This is my first Rx App"</b> of type String.<br>
</p>

#### Downstream

```Kotlin

subscribe {
    print("Observed Result: \"$it\"")
}
```

<p>
This will collect the event from the upstream and prints them.<br>
Next up, it's time for you to roll up your sleeves and get some more hands on with bread and butter of RxKotlin
</p>

## Creating Observable <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex2.kt">`Ex2.kt`</a>

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

## Creating Subscription <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex3.kt">`Ex3.kt`</a>

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

## Using Create operator <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex4.kt">`Ex4.kt`</a>

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
</p>

#### Single

Can emit one Error or one Completed event (you can see the implementation of single in combination with Retrofit)

#### Completable

Can return one Error or one Completed event

#### Maybe

Can emit either one Next/Error/Completed event

#### Creating a Single

<p>

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

## Subject and non ending sequences of streams <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex5.kt">`Ex5.kt`</a>

<p>
Subjects are comprised of two parts :
<br>
1. <b>Observable</b> (just like that you have seen before)
<br>
2. <b>Observer</b> : This can receive new events, upon receiving new events it will be passed to the subscribers.
<br>
They are kind of like a Yin and Yang combination of Observable and Observer, This gives us capability of 
working with observables that are not finite sequences. New elements can be added to the Subject at 
runtime, and they will be emitted to Subscribers.
</p>
<p>
There are 3 types of subjects that we use:
<br>

### Publish Subject

This starts as an empty sequence and emits only new next events to its subscribers. In other words, Elements added to
PublishSubject before the subscription will not be received by the subscriber.

```Kotlin
  val compositeDisposable = CompositeDisposable()
val seasonBroadcast = PublishSubject.create<String>()
seasonBroadcast.onNext(Season1)
seasonBroadcast.onNext(Se ason2)
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

```

### Behaviour Subject

Sometimes, you want the new subscribers to receive the most recent next event. even if they subscribe after that event
was originally emitted. For this we can use BehaviourSubject. They start with an initial value, and they will replay the
latest value to the new subscribers.They are <b>Stateful</b> (you can access the latest state anytime)

```Kotlin
  val compositeDisposable = CompositeDisposable()

val sendBroadcast = BehaviorSubject.createDefault(Season1)

/*
*  You can access the behaviour subject value in the below way.
*  This can particularly be helpful in UI related stuff.
 */
println("value of the Subject is :${sendBroadcast.value}")

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
```

### Replay

What if you want to replay more than just one event other than the latest value... ReplaySubject comes to the rescue. It
starts empty, but is initialized with ab buffer size, It will replay upto that bufferSize to the new subscribers. We
should not make the buffer size larger, because it will be held in the memory for the life of the subject.

```Kotlin
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
```

</p>

## Operators <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex6.kt">`Ex6.kt`</a>

<p>
Operators are the building blocks of Rx, which you can use to filter, transform, process and react to events emitted by observables. You 
can chain this operators to perform complex operations in a very succinct and understandable way when you go back review that 
code later.

we can start by looking into filtering operators, which allow you to process some events but ignore others. Then we will
move on to transforming operators, which allow you to manipulate events and data that are emitted by an observable in
order to prepare it for subscribers.

<b>Filtering Operator</b><br>
In a nutshell, this applies conditional constraints to next events to only pass through to subscribers the elements you
want.

### ignoreElements()

![ignoreElements](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/ignore-elements.png)

As shown in the marble diagram, ignoreElements() will ignore next events. However, it will allow through stop events, In
other words Completed or Error events. Allowing through stop events are usually implied in marble diagrams. We are just
explicitly calling it out this time because that's all ignoreElements will let through.
<br>

### elementAt()

![elementAt](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/elementat.png)

This will filter next events except the one at the specified index. This marble diagram depicts using elementAt() to
only return the 3rd next event element and ignore the rest.
<br>

### filter()

![filter](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/filter.png)

RxKotlin also has a filter operator for observable sequence that works similarly to kotlin's filter function for
collections. It takes a predicate to apply to each element to determine if the element should be allowed through or not.
In this marble diagram it will allow to pass the elements that are greater than 10 only.
<br>

### skip()

![skip](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/skip.png)

This will skip the count of elements that you pass for its parameter and then allow all forthcoming elements through.
<br>

### skipWhile()

![skipWhile](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/skipWhile.png)

This will apply a predicate and skip elements up until the predicate fails and then all future elements through. In
other words, it stops skipping once the predicate fails.
<br>

### take()

![take](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/take.png)

This work in a way opposite to skip. Take will wait for it, take the count of elements up to and including the number
you provided for its parameter, then stop taking any additional elements.
<br>

### takeWhile()

![takeWhile](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/takeWhile.png)

This will only take elements while a condition resolves to true and then stop taking any more elements.<br>
note: it is different from filter operator. Once the condition is false, it stops taking any more elements.
<br>

However, so far, Filtering has been based on static conditions. There are also filtering operators that let you
dynamically filter elements based on some other Observables.
<br>

### skipUntil()

![skipUntil](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/skipUntil.png)

This will skip an element until a second Observable triggers the skipUntil operator top stop skipping.
<br>

### takeUntil()

![takeUntil](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/takeUntil.png)

This will keep taking elements until a second observable triggers it to stop taking.
<br>

### distinctUntilChanged()

![distinctUntilChanged](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/distinctUntilChanged.png)

This operator prevents contiguous duplicate to get through, so the second one in this marble diagram gets through
because the previous element was different.
<br>

### distinct()

![distinct](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/distinct.png)

This operator prevents duplicate elements to pass through
<br>

### debounceTime()

![debounceTime](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/debounceTime.png)

Debouncing is something that we had heard while doing UI - button clicks. yes, this is the same thing!
This operator has 2 parameters, timeout and time unit. Timeout is the window for which the filtering will be blocked and
will only take the last element in the sequence. If you can take a look at the marble diagram, you can see that debounce
time is 10, This first element is passed in. then we have 2,3,4,5 that are in an overlapping duration if we consider a
10ms time window, bacause of this, 2,3,4 will be discarded and only 5 will be filtered in.
<br>

### share()

This operator helps us to share an observable among multiple subscribers.

<br>

This covers the important operators for android use cases, there are a bit more self-explanatory operator
implementations in
<a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex6.kt">`Ex6.kt`</a>
</p>

## Schedulers

By default in Rx-Kotlin(Android), Observables and the operators work on the same thread as where the subscription
occurs, which is typically on the MainThread. Schedulers provide an abstraction for managing threads for changing that
default behaviour. A scheduler is a context where a process takes place.

### Advantages

* Abstract thread management.
* Easy to use.

### How to specify schedulers

#### Observe On

This directs where events are received after it is called. In a Chain of operators, what comes after a call to ObserveOn
will be performed on the Scheduler that is specified

#### Subscribe On

This will direct where an entire subscription is performed, regardless of where it is called.

<br>
To get a better understanding of Scheduler lets consider this marble diagram, so that it will help you visualize how 
it works in a real life use-case
<br>
<br>
<p align="center">
<img src="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/schedulers.png"/>
</p>
<br>
I'm starting out on the main thread, which is represented by the bottom green timeline. 
I'll call ObserveOn and specify purpleScheduler. Imagine that I am going to do some intensive work here. 
So I am specifying a background thread to receive the events on for a map operation, in this case. 
Then I will call SubscribeOn and specify orangeScheduler. I am specifying where I want subscription to be created. 
their handlers executed, and where they will be disposed of. By using the subscribeOn operator here, It 
will direct subscriptions for the entire chain, so that means that I am no longer in the MainThread, it is going to be 
the thread established by the orangeScheduler. I am still observing on the purpleScheduler, nothing's changed there. And finally 
I'll use observeOn again to receive the transformed events from the map operation back on the mainThread. 

## Transforming Operators <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex7.kt">`Ex7.kt`</a>

You have already established the foundation of your RxKotlin skyscraper of skills and have added the first floor in
learning about filtering operators. Let's keep building upward. You will use transforming operator all the time to prep
data coming from an observable. There are parallels between transforming operator in RxKotlin and the kotlin standard
library, such as map and flatMap.

### Map

RxKotlin's Map operator works just like kotlin's standard map, except it operates on an observable sequences instead of
a normal collection.

![Map](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/map.png)

In the marble diagram, map takes a lambda that multiples each element by 10. see the coding example
in <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex7.kt">`Ex7.kt`</a>
for getting more insights.<br>

Now let's venture into a something bit more elusive at first. Before I show the marble diagram let me just say that some
of these operators have elicited more than their fair share of questions, and groans and moans from newcomers to
RxKotlin. They may seem complex at first, but they are very easy to understand... lets dive in...

### FlatMap

![flatMap](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/flatmap.png)

let's walk through the marble diagram. The easiest way to follow what's happening in this marble diagram is to take each
path from the source observable, the top line, all the way to the target observable that will deliver elements to the
subscriber, the bottom line. the source observable is of type "O" that has a value property that itself is an observable
of type Int. It's value property's initial value is the number of the object ie. O1 -> 1 , O2 -> 2 and O3 -> 3. Starting
with O1, flatmap receives object and reaches into access it's value property and multiply it by 10. It the projects the
transformed elements from O1 into new Observable. The first one below flatmap just for O1, and that Observable is
flattened down to the target observable that will deliver elements to the subscriber, the bottom line. <br>

This is the general principle of flatMap<br>

`Observable -> Transform -> Project -> Falttened to a target observable`

### SwitchMap

It is similar to flatmap, but it only produces value from most recent Observable sequence. SwitchMap is actually a
combination of two operators, map and switch. Here is switch map's marble diagram.

![switchMap](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/switchMap.png)

O1 is received by switchMap. It transforms its value to 10, projects it onto a new Observable for O1, and flattens it
down to the target observable, just like before. But, then switchMap receives O2 and it does its thing, switching to O2
observable because it is now the latest... and so on... The result is that the target observable only receives element
from the latest observable.

## Combining Operators <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex8.kt">`Ex8.kt`</a>

RxKotlin is about working with asynchronous sequences, which will often make order out of chaos. There is a lot you can
accomplish by combining observables.

### StartWith()

![startWith](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/startWith.png)

StartWith() prepends a sequence of values onto an observable that subscribes are guaranteed to receive first before any
other elements.

### combine()

![combine](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/concat.png)

It turns out that the startWith is actually just a simplified variant of the contact operator. Concat joins two
observables together and combines their elements in the order the observables are specified.

### combineWith()

![combineWith](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/concat.png)

This is similar to concat, but the only difference is that concatWith waits for the first observable to complete to
start emitting the second one.

### merge()

![merge](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/merge.png)

Merge will do as it says, interspersing elements from the combined observables as their elements are emitted. Like with
concat there is a merge with instance method.

### combineLatest()

This is a static method on observable that will take the latest from each of the
source observables whenever any source observable emits and pass those latest elements to
a lambda for you to specify how to combine them.

![combineLatest](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/resources/combineLatest.png)

This will emit the combined element everytime either source emits, So you might not get what 
you had expected here. Another thing to be aware of is that the combineLatest will wait until all 
source observables emit an initial value.

** there are also certain variants of combine latest that will let you combine upto 8 source 
observables. Now you might be wondering what the use case is... You can observe several text fields 
top combine their value everytime text is emitted into one of the text field.













