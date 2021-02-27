<h1>Learn RX</h1>

<p>Hey What's up everybody. I am Joseph James, In this project you can get a heads up to Rx-kotlin and use it in your Android Apps.
So, What is Rx-Kotlin? Why would you want to use it in your project? </p>

<p>Rx-Kotlin is an asynchronous programming library that is based on using observables. Observables are sequences of data or events that you can
 react to, such as data coming back from a web service, or even taps by the user. you can refer to the 
marble diagrams 
for getting a better insight about Rx's event
based programming. One thing that you should keep in your mind is that, everything is a sequence in Rx</p>

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

<strong>General idea of upstream and downstream</strong> - [Ex1.kt](https://github.com/iamjosephmj/learn-rx/blob/master/src/main/kotlin/Ex1.kt)
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
This part emits an event <b>"This is my first Rx App"</b> of type String. 
2. Downstream<br>

```Kotlin
   subscribe {
    print("Observed Result: \"$it\"")
}
```

This will collect the event from the upstream and prints them.<br>

Next up, it's time for you to roll up your sleeves and get some more hands on with bread 
and butter of RxKotlin 
</p>
