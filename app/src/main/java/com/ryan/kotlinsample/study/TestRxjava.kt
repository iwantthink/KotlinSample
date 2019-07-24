package com.ryan.kotlinsample.study

import android.annotation.SuppressLint
import android.view.View
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.observables.GroupedObservable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.io.IOException
import java.lang.Error
import java.util.*
import java.util.concurrent.TimeUnit


class TestRxjava {

    fun main(args: Array<String>): Unit {

//        testInterval()
//        testObservableCreate()
//        testTransformation()
//        testComposition()
//        testAssist()
//        testProcessError()
//        testCondition()
//        testTransfromation2()
//        testScheduler()

        testBackPressure()

        val observer = object : Observer<String> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

        val subscriber = object : Subscriber<String> {
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(s: Subscription?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }


    }

    @SuppressLint("CheckResult")
    private fun testBackPressure() {
//        Flowable.range(0, Int.MAX_VALUE)
//            .observeOn(Schedulers.computation())
//            .subscribe {
//                compute(it)
//            }
//        Observable.range(0, 10)
//            .subscribe(object : Observer<Int> {
//                override fun onComplete() {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onNext(t: Int) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onError(e: Throwable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//            })

//        Flowable.range(0, 10)
//            .subscribe(object : Subscriber<Int> {
//                override fun onComplete() {
//
//                }
//
//                override fun onSubscribe(s: Subscription?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onNext(t: Int?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onError(t: Throwable?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//            })


        System.out.println("start")
//        val source = PublishProcessor.create<Int>()
//        source.observeOn(Schedulers.computation())
//            .subscribe({
//                compute(it)
//                System.out.println("onNext - $it")
//            }, {
//                System.out.println("onError - $it")
//            })
//
//        repeat(1_000_000) {
//            source.onNext(it)
//        }


        val flowable = Flowable.create<Int>({ emitter ->
            repeat(10) {
                System.out.println("send $it")
                emitter.onNext(it)
            }
            emitter.onComplete()

            // 不限制缓存池大小，可以无限添加
//        }, BackpressureStrategy.BUFFER)
            // 缓存池如果满了，就丢弃掉新产生的事件
//        }, BackpressureStrategy.DROP)
            // 缓存池超出限度，抛出异常
//        }, BackpressureStrategy.ERROR)
            // 不指定任何背压策略, 通过下游指定,onBackpressureBuffer()等
//        }, BackpressureStrategy.MISSING)
            //与Drop相似，但是不同点是 无论缓存池状态如何，都会将最后一条数据强行放入缓存池
        }, BackpressureStrategy.LATEST)


        val subscriber = object : Subscriber<Int> {
            override fun onComplete() {
                System.out.println("onComplete")
            }

            override fun onSubscribe(s: Subscription?) {
                s?.request(Long.MAX_VALUE)
                System.out.println("onSubscribe")

            }

            override fun onNext(t: Int?) {
                System.out.println("onNext t = $t")
            }

            override fun onError(t: Throwable?) {
                System.out.println("onError $t")
            }
        }

        flowable.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(subscriber)

    }

    fun compute(i: Int): Unit {
        Thread.sleep(500)
        System.out.println("compute = $i")
    }


    @SuppressLint("CheckResult")
    private fun testScheduler() {

        val ob1 = Observable.range(0, 10)
        // 指定Observable 运行的线程
        ob1.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                System.out.println("onNext result =$it")
            }
    }

    @SuppressLint("CheckResult")
    private fun testTransfromation2() {
        val ob1 = Observable.range(0, 10)
        ob1.toList().subscribe { list ->
            System.out.println("list = ${list::class}")
        }

        ob1.toSortedList()
            .subscribe { result ->
                System.out.println("toSortedList result = $result")
            }

        ob1.toMap { t: Int ->
            // 返回值就是key
            t * 2
        }.subscribe { result ->
            System.out.println("toMap result = $result")
        }


        ob1.to {
            it.count()
        }.subscribe { result ->
            System.out.println("to result = $result")

        }


    }

    @SuppressLint("CheckResult")
    private fun testCondition() {
        val ob1 = Observable.range(0, 10)
        // 判断源数据中数据是否全部 >5
        val allResult = ob1.all {
            it > 5
        }
        // 获取all的判断结果
        allResult.subscribe { t1 ->
            System.out.println(" all = $t1")
        }

        ob1.any {
            it > 5
        }.subscribe { t2 ->
            System.out.println(" any = $t2")
        }


        ob1.contains(9)
            .subscribe { result ->
                System.out.println(" contains = $result")
            }
        ob1.isEmpty().subscribe { result ->
            System.out.println(" isEmpty = $result")
        }

        val ob2 = Observable.range(0, 11)
        val ob3 = Observable.range(0, 10)

        Observable.sequenceEqual(ob1, ob2)
            .subscribe { result ->
                System.out.println(" sequenceEqual = $result")

            }

        Observable.sequenceEqual(ob1, ob3)
            .subscribe { result ->
                System.out.println(" sequenceEqual = $result")

            }


        Observable.amb(
            Arrays.asList(
                Observable.create<Int> { emitter ->
                    Thread.sleep(500)
                    emitter.onNext(1)
                    emitter.onNext(11)
                    emitter.onNext(111)
                    emitter.onComplete()
                },
                Observable.create<Int> { emitter ->
                    Thread.sleep(1000)
                    emitter.onNext(999)
                    emitter.onNext(9999)
                    emitter.onNext(99999)
                    emitter.onComplete()
                },
                Observable.create {
                    Thread.sleep(3333)
                    it.onNext(8888)
                    it.onNext(88888)
                    it.onNext(888888)
                }
            )

        ).subscribe(object : Observer<Int> {
            override fun onComplete() {
                System.out.println("amb onComplete ")
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println("amb onSubscribe ")
            }

            override fun onNext(t: Int) {
                System.out.println("amb onNext $t")
            }

            override fun onError(e: Throwable) {
                System.out.println("amb onError ")
            }


        })


        Observable.empty<String>()
            .defaultIfEmpty("default value")
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    System.out.println("defaultIfEmpty onComplete ")
                }

                override fun onSubscribe(d: Disposable) {
                    System.out.println("defaultIfEmpty onSubscribe ")
                }

                override fun onNext(t: String) {
                    System.out.println("defaultIfEmpty onNext $t")
                }

                override fun onError(e: Throwable) {
                    System.out.println("defaultIfEmpty onError ")
                }
            })


    }

    @SuppressLint("CheckResult")
    private fun testProcessError() {

        val ob1 = Observable.create<String> { emitter ->
            emitter.onNext("1")
//            emitter.onError(Exception("Something wrong"))
            emitter.onError(Error("Something Error"))
            emitter.onNext("2")
            emitter.onNext("3")
            emitter.onNext("4")
            emitter.onComplete()
        }

        // 在错误发生时，进行拦截，并返回一个新的值 传给onNext
        ob1.onErrorReturn {
            "translate ${it.message}"
        }.subscribe({
            System.out.println("onErrorReturn - onNext item = $it")
        }, {
            System.out.println("onErrorReturn - onError $it")
        }, {
            System.out.println("onErrorReturn - onComplete")
        })

        // 与onErrorReturn 相似，都会在发生错误之后，阻止剩余的事件
        // 并使用新的数据返回
        ob1.onErrorResumeNext(
            Observable.just("backup data")
        ).subscribe({
            System.out.println("onErrorResumeNext - onNext item = $it")
        }, {
            System.out.println("onErrorResumeNext - onError $it")
        }, {
            System.out.println("onErrorResumeNext - onComplete")
        })

        // 该方法只能捕捉Exception!!!
        ob1.onExceptionResumeNext(
            Observable.just("new data")
        ).subscribe({
            System.out.println("onExceptionResumeNext - onNext item = $it")
        }, {
            System.out.println("onExceptionResumeNext - onError $it")
        }, {
            System.out.println("onExceptionResumeNext - onComplete")
        })

        // 遇到错误之后 重新执行！！！
        // 是从头开始
        // 第一次执行不需要计算到重试中去
        ob1.retry(3)
            .subscribe({
                System.out.println("retry - onNext item = $it")
            }, {
                System.out.println("retry - onError $it")
            }, {
                System.out.println("retry - onComplete")
            })

    }

    @SuppressLint("CheckResult")
    private fun testAssist() {
        val ob1 = Observable.range(0, 10)
        val ob2 = Observable.intervalRange(
            0,
            10,
            0,
            500,
            TimeUnit.MILLISECONDS
        )
        System.out.println("delayFunction - ${System.currentTimeMillis()}")

        // 深度订制每个事件的延迟
        ob1.delay {
            Thread.sleep(500)
            Observable.just(it)
        }.subscribeOn(IoScheduler()).subscribe({
            System.out.println("delayFunction - ${System.currentTimeMillis()}")
            System.out.println("delayFunction - onNext item = $it")
        }, {
            System.out.println("delayFunction - onError")
        }, {
            System.out.println("delayFunction - onComplete")
        })

        // 仅在开始事件之前延迟，后续没有影响
        System.out.println("delay - ${System.currentTimeMillis()}")
        ob1.delay(1000, TimeUnit.MILLISECONDS)
            .subscribe({
                System.out.println("delay - ${System.currentTimeMillis()}")
                System.out.println("delay - onNext item = $it")
            }, {
                System.out.println("delay - onError")
            }, {
                System.out.println("delay - onComplete")
            })


        Observable.range(0, 4)
            .doOnEach { integerNotification ->
                // onNext调用之前被调用
                System.out.println("doOnEach : " + integerNotification.value)
            }
            .doOnComplete {
                // onComplete调用之前被调用
                System.out.println("doOnComplete")
            }
            .doFinally {
                // onComplete调用之后被调用
                System.out.println("doFinally")
            }
            .doAfterNext { i ->
                // onNext调用之后被调用
                System.out.println("doAfterNext " + i!!)
            }
            .doOnSubscribe { disposable ->
                // 订阅时之前调用
                System.out.println("doOnSubscribe")
            }
            .doOnTerminate {
                // 终止前调用
                System.out.println("doOnTerminate")
            }
            .subscribe(
                { i ->
                    System.out.println("onNext :" + i!!)
                },
                {
                    System.out.println("onError ")
                },
                {
                    System.out.println("onComplete ")
                }

            )


        Observable.create<String> { emitter ->
            System.out.println("Thread = ${Thread.currentThread()}")
            emitter.onNext("Thread 1")
            emitter.onNext("Thread 2")
            emitter.onComplete()
        }.subscribeOn(IoScheduler())
            .observeOn(Schedulers.computation())
            .subscribe {
                System.out.println("onNext Thread = ${Thread.currentThread()}")
                System.out.println("onNext = $it")
            }

        ob2.timeout(400, TimeUnit.MILLISECONDS)
            .subscribe(
                { i ->
                    System.out.println("timeout onNext :" + i!!)
                },
                {
                    System.out.println("timeout onError ")
                },
                {
                    System.out.println("timeout onComplete ")
                }

            )
    }

    @SuppressLint("CheckResult")
    private fun testComposition() {
        val ob1 = Observable.range(0, 10)
        val ob3 = Observable.range(10, 10)
        val ob2 = Observable.intervalRange(
            100,
            10,
            0,
            500,
            TimeUnit.MILLISECONDS
        )
        ob1.startWith(999).subscribe({
            System.out.println("startWith - onNext item = $it")
        }, {
            System.out.println("startWith - onError")
        }, {
            System.out.println("startWith - onComplete")
        })

//        System.out.println("startWith = ${System.currentTimeMillis()}")
//        ob2.startWith(999).subscribe({
//            System.out.println("startWith = ${System.currentTimeMillis()}")
//            System.out.println("startWith - onNext item = $it")
//        }, {
//            System.out.println("startWith - onError")
//        }, {
//            System.out.println("startWith - onComplete")
//        })

        // 往源数据中添加一个数组
        ob1.startWithArray(9, 9, 9, 9, 9)
            .subscribe({
                System.out.println("startWithArray - onNext item = $it")
            }, {
                System.out.println("startWithArray - onError")
            }, {
                System.out.println("startWithArray - onComplete")
            })

        ob1.mergeWith(ob3)
            .subscribe({
                System.out.println("mergeWith - onNext item = $it")
            }, {
                System.out.println("mergeWith - onError")
            }, {
                System.out.println("mergeWith - onComplete")
            })

//        ob1.mergeWith {
//            it.onNext(123)
//            it.onNext(456)
//            it.onNext(789)
//        }.subscribe({
//            System.out.println("mergeWith - onNext item = $it")
//        }, {
//            System.out.println("mergeWith - onError")
//        }, {
//            System.out.println("mergeWith - onComplete")
//        })

        Observable.merge(
            Observable.intervalRange(
                0,
                10,
                0,
                100,
                TimeUnit.MILLISECONDS
            ),
            Observable.intervalRange(
                0,
                10,
                0,
                200,
                TimeUnit.MILLISECONDS
            )
        )
            .subscribe({
                System.out.println("merge - onNext item = $it")
            }, {
                System.out.println("merge - onError - error = ${it}")
            }, {
                System.out.println("merge - onComplete")
            })

        // mergeDelayError 会先发送完所有merge的事件，再发送error事件
        // merge 在遇到error会直接停止其余的事件
        Observable.mergeDelayError(
            ob1, ob3,
            Observable.error(Exception("抛出异常"))
        )
            .subscribe({
                System.out.println("mergeDelayError - onNext item = $it")
            }, {
                System.out.println("mergeDelayError - onError - error = $it")
            }, {
                System.out.println("mergeDelayError - onComplete")
            })


        Observable.concat(ob2, ob3)
            .subscribe({
                System.out.println("concat - onNext item = $it")
            }, {
                System.out.println("concat - onError - error = $it")
            }, {
                System.out.println("concat - onComplete")
            })

        // 将源数据，按照 传入的BiFunction 进行处理，返回值组成新的Observable
        Observable.zip(ob1,
            ob3,
            BiFunction<Int, Int, Any> { t1, t2 -> t1 + t2 })
            .subscribe({
                System.out.println("zip - onNext item = $it")
            }, {
                System.out.println("zip - onError - error = $it")
            }, {
                System.out.println("zip - onComplete")
            })


        // 成对匹配，没有成对则会等待
        Observable.zip(
            ob1,
            ob2,
            BiFunction<Int, Long, Any> { t1, t2 ->
                return@BiFunction t1 + t2
            }
        ).subscribe({
            System.out.println("zip2 - onNext item = $it")
        }, {
            System.out.println("zip2 - onError - error = $it")
        }, {
            System.out.println("zip2 - onComplete")
        })

        Observable.combineLatest(ob1,
            ob3,
            BiFunction<Int, Int, Int> { i1, i2 ->
                System.out.println("combineLatest i1 = $i1, i2 = $i2")
                return@BiFunction i1 + i2
            }).subscribe({
            System.out.println("combineLatest - onNext item = $it")
        }, {
            System.out.println("combineLatest - onError - error = $it")
        }, {
            System.out.println("combineLatest - onComplete")
        })


    }

    @SuppressLint("CheckResult")
    private fun testTransformation() {
        // 对数据进行转变
        Observable.intervalRange(
            0,
            3,
            0,
            0,
            TimeUnit.SECONDS
        ).map {
            "result $it"
        }.subscribe(
            {
                // 执行在Rx提供的线程池中
                System.out.println("CurrentThread = ${Thread.currentThread().name}")
                System.out.println("map - onNext  it = $it")
            },
            {
                System.out.println("map - onError")

            },
            {
                System.out.println("map - onComplete")

            })


        Observable.range(
            0,
            3
        ).cast(
            Any::class.java
        ).subscribe(
            {
                // 执行在Rx提供的线程池中
                System.out.println("CurrentThread = ${Thread.currentThread().name}")
                System.out.println("cast - onNext  it = $it")
            },
            {
                System.out.println("cast - onError")

            },
            {
                System.out.println("cast - onComplete")

            })


        Observable.fromArray(
            1, 2, 3, 4, 5
        ).flatMap {
            Observable.just("flatMap $it")
        }.subscribe(
            {
                // 执行在Rx提供的线程池中
                System.out.println("CurrentThread = ${Thread.currentThread().name}")
                System.out.println("flatMap - onNext  it = $it")
            },
            {
                System.out.println("flatMap - onError")

            },
            {
                System.out.println("flatMap - onComplete")

            })

        Observable.fromArray(
            "1", "2",
            "#"
        ).concatMap {
            Observable.just("concatMap $it")
        }.subscribe(
            {
                // 执行在Rx提供的线程池中
                System.out.println("CurrentThread = ${Thread.currentThread().name}")
                System.out.println("concatMap - onNext  it = $it")
            },
            {
                System.out.println("concatMap - onError")

            },
            {
                System.out.println("concatMap - onComplete")

            })

        Observable.range(
            0,
            5
        ).flatMapIterable {
            val array = arrayListOf<String>()
            it.rangeTo(5).forEach { item ->
                array.add("item = $item ,it = $it")
            }
            listOf(array)
        }.subscribe(
            {
                // 执行在Rx提供的线程池中
                System.out.println("CurrentThread = ${Thread.currentThread().name}")
                System.out.println("flatMapIterable - onNext  it = ${it.size}")
            },
            {
                System.out.println("flatMapIterable - onError")

            },
            {
                System.out.println("flatMapIterable - onComplete")

            })

        Observable.range(0, 5)
            .buffer(2)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("CurrentThread = ${Thread.currentThread().name}")
                    System.out.println("buffer - onNext  it = $it")
                },
                {
                    System.out.println("buffer - onError")

                },
                {
                    System.out.println("buffer - onComplete")

                })

        // 根据groupBy返回值将原始数据进行分组
        val ob1: Observable<GroupedObservable<Int, String>> =
            Observable.just("", "1", "22", "333", "44", "5")
                .groupBy {
                    return@groupBy it.length
                }

        ob1.subscribe(
            {
                // 执行在Rx提供的线程池中
                System.out.println("CurrentThread = ${Thread.currentThread().name}")
                System.out.println("groupBy - onNext  it = $it")
                it.subscribe(
                    {
                        System.out.println("CurrentThread = ${Thread.currentThread().name}")
                        System.out.println("groupBy inner - onNext")

                    },
                    {
                        System.out.println("groupBy inner - onError")

                    }, {
                        System.out.println("groupBy inner - onComplete")

                    })
            },
            {
                System.out.println("groupBy - onError")

            },
            {
                System.out.println("groupBy - onComplete")

            })


        Observable.range(0, 5)
            .scan { t1: Int, t2: Int ->
                System.out.println("t1 = $t1")
                System.out.println("t2 = $t2")
                t1 + t2
            }.subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("CurrentThread = ${Thread.currentThread().name}")
                    System.out.println("scan - onNext  it = $it")
                },
                {
                    System.out.println("scan - onError")

                },
                {
                    System.out.println("scan - onComplete")

                })


        // 与buffer相似，但是是将值生成Observable 在组装
        Observable.range(0, 10)
            .window(3)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("CurrentThread = ${Thread.currentThread().name}")
                    System.out.println("window - onNext  it = $it")
                },
                {
                    System.out.println("window - onError")

                },
                {
                    System.out.println("window - onComplete")

                })


        Observable.range(0, 10)
            .filter loop@{
                return@loop it % 2 == 0
            }
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("CurrentThread = ${Thread.currentThread().name}")
                    System.out.println("filter - onNext  it = $it")
                },
                {
                    System.out.println("filter - onError")

                },
                {
                    System.out.println("filter - onComplete")

                })

        Observable.range(0, 10)
            .elementAt(0)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("CurrentThread = ${Thread.currentThread().name}")
                    System.out.println("elementAt - onNext  it = $it")
                },
                {
                    System.out.println("elementAt - onError")

                },
                {
                    System.out.println("elementAt - onComplete")

                })

        val ob2 = Observable.range(0, 10)

        ob2.first(11).subscribe(
            { t: Int ->

                System.out.println("first - onSuccess  t=$t")
            },
            {
                System.out.println("first - onError")

            }
        )
        Observable.empty<Int>()
            .first(111)
            .subscribe { t1, t2 ->
                System.out.println("first - t1 = $t1  t2 = $t2")

            }


        val ob3 = Observable.just(1, 2, 3, 4, 4, 4, 5, 6, 6, 6)
        // 去除
        ob3.distinct()
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("CurrentThread = ${Thread.currentThread().name}")
                    System.out.println("distinct - onNext  it = $it")
                },
                {
                    System.out.println("distinct - onError")

                },
                {
                    System.out.println("distinct - onComplete")

                })


        ob3.skip(2)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("CurrentThread = ${Thread.currentThread().name}")
                    System.out.println("skip - onNext  it = $it")
                },
                {
                    System.out.println("skip - onError")

                },
                {
                    System.out.println("skip - onComplete")

                })

        ob3.take(3)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("CurrentThread = ${Thread.currentThread().name}")
                    System.out.println("take - onNext  it = $it")
                },
                {
                    System.out.println("take - onError")

                },
                {
                    System.out.println("take - onComplete")

                })

        // 忽略元素，不执行onNext
        ob3.ignoreElements()
            .subscribe(
                {
                    System.out.println("subscribe - onError")

                },
                {
                    System.out.println("subscribe - onComplete")

                })


        // throttleFirst
        // 一短时间的 连续操作 只响应 第一次的
        // 1s 内 触发 2次 ，只取第一个值
        val ob4 = Observable.intervalRange(
            0,
            10,
            0,
            500,
            TimeUnit.MILLISECONDS
        )
        System.out.println("Time = ${System.currentTimeMillis()}")

        ob4.subscribe(
            {
                // 执行在Rx提供的线程池中
                System.out.println("Normal Time = ${System.currentTimeMillis()}")
                System.out.println("Normal - onNext  it = $it")
            },
            {
                System.out.println("Normal - onError")

            },
            {
                System.out.println("Normal - onComplete")

            })

        ob4.throttleFirst(1, TimeUnit.SECONDS)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("throttleFirst Time = ${System.currentTimeMillis()}")
                    System.out.println("throttleFirst - onNext  it = $it")
                },
                {
                    System.out.println("throttleFirst - onError")

                },
                {
                    System.out.println("throttleFirst - onComplete")

                })

        ob4.throttleLast(1, TimeUnit.SECONDS)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("throttleLast Time = ${System.currentTimeMillis()}")
                    System.out.println("throttleLast - onNext  it = $it")
                },
                {
                    System.out.println("throttleLast - onError")

                },
                {
                    System.out.println("throttleLast - onComplete")

                })

        // 如果俩个事件的间隔小于 timeout
        // 则前一个事件不会发送给观察者
        Observable.create<String> { emitter ->
            emitter.onNext("1")
            Thread.sleep(500)
            emitter.onNext("2")
            Thread.sleep(250)
            emitter.onNext("3")
            Thread.sleep(301)
            emitter.onNext("4")
            emitter.onNext("5")
        }
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("debounce = ${System.currentTimeMillis()}")
                    System.out.println("debounce - onNext  it = $it")
                },
                {
                    System.out.println("debounce - onError")

                },
                {
                    System.out.println("debounce - onComplete")

                })


        ob4.sample(1, TimeUnit.SECONDS)
            .subscribe(
                {
                    // 执行在Rx提供的线程池中
                    System.out.println("sample = ${System.currentTimeMillis()}")
                    System.out.println("sample - onNext  it = $it")
                },
                {
                    System.out.println("sample - onError")

                },
                {
                    System.out.println("sample - onComplete")

                })

    }

    @SuppressLint("CheckResult")
    private fun testObservableCreate() {

        Observable.create(ObservableOnSubscribe<String> { emitter: ObservableEmitter<String> ->
            emitter.onNext("开始")
            emitter.onNext("结束")
            emitter.onComplete()
//            emitter.onError(Exception("错误"))
        }).subscribe(System.out::println)

        // 延迟返回，当被观察者订阅时才创建
        val ob1 = Observable.defer {
            return@defer Observable.range(0, 5)
        }

        ob1.subscribe(System.out::println)
        ob1.subscribe(System.out::println)


        Observable.empty<String>().subscribe(
            {
                System.out.println("empty - onNext")
            },
            {
                System.out.println("empty - onError")

            },
            {
                // 执行
                System.out.println("empty - onComplete")

            })

        // 啥都不执行
        Observable.never<String>().subscribe(
            {
                System.out.println("never - onNext")
            },
            {
                System.out.println("never - onError")

            },
            {
                System.out.println("never - onComplete")

            })

        // 会交给onError处理，如果没有提供onError 会抛出运行时异常!
        Observable.error<Exception>(IOException()).subscribe(
            {
                System.out.println("error - onNext")
            },
            {
                // 执行
                System.out.println("error - onError")

            },
            {
                System.out.println("error - onComplete")

            })

        Observable.fromArray(arrayOf("1", "2", "#")).subscribe(
            {
                System.out.println("fromArray - onNext")
            },
            {
                System.out.println("fromArray - onError")

            },
            {
                System.out.println("fromArray - onComplete")

            })

        Observable.fromCallable {
            return@fromCallable "callable result"
        }.subscribe(
            {
                System.out.println("fromCallable - onNext")
            },
            {
                System.out.println("fromCallable - onError")

            },
            {
                System.out.println("fromCallable - onComplete")

            })


        Observable.just("abc").subscribe(
            {
                System.out.println("just - onNext")
            },
            {
                System.out.println("just - onError")

            },
            {
                System.out.println("just - onComplete")

            })

//        Observable.create<String> {
//            it.onNext("1")
//            it.onComplete()
//        }.subscribeOn(IoScheduler()).repeat(3).subscribe(
//            {
//                System.out.println("repeat - onNext")
//            },
//            {
//                System.out.println("repeat - onError")
//
//            },
//            {
//                System.out.println("repeat - onComplete")
//
//            })

        // repeat 会重复 之前的序列 onNext
        // 但是onComplete 不会
        Observable.range(0, 3).repeat(5).subscribe(
            {
                System.out.println("repeat - onNext it =  $it")
            },
            {
                System.out.println("repeat - onError")

            },
            {
                System.out.println("repeat - onComplete")

            })

        System.out.println("current time = ${System.currentTimeMillis()}")
        Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(
            {
                System.out.println("timer - onNext it =  $it")
                System.out.println("current time = ${System.currentTimeMillis()}")
            },
            {
                System.out.println("timer - onError")
                System.out.println("current time = ${System.currentTimeMillis()}")

            },
            {
                System.out.println("timer - onComplete")
                System.out.println("current time = ${System.currentTimeMillis()}")

            })

    }

    private fun testInterval() {

        val ob2 = Observable.interval(
            10,
            TimeUnit.SECONDS,
            IoScheduler()
        ).subscribe {
            System.out.println("it = $it")
        }

        ob2.dispose()

        val ob3 = Observable.intervalRange(
            10,
            5,
            0,
            10,
            TimeUnit.SECONDS
        ).subscribe(System.out::println)


        val ob4 = Observable.range(
            0,
            5
        ).subscribe(System.out::println)
    }
}