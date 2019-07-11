package com.ryan.kotlinsample.basic

import com.ryan.kotlinsample.lll

fun testCollectionMain(): Unit {

    val l1 = listOf<String>()
    val ml1 = mutableListOf<String>()

    val s1 = setOf<String>()
    val ms1 = mutableSetOf<String>()

    val m1 = mapOf<String, String>()
    val mm1 = mutableMapOf<String, String>()

    mutableMapOf<String, String>("1" to "2")
    mapOf<String, String>("1" to "2").apply {
    }

    val l2 = emptyList<String>()
    val s2 = emptySet<String>()
    val m2 = emptyMap<String, String>()


    val l3 = List<String>(10) {
        "No.$it"
    }

//    val s3 = Set<String>
    val s3 = HashSet<String>(10)

//    val m3 = Map<String,String>
    val m3 = HashMap<String, String>(10)

    l1.toSet()


    for (i in 1.rangeTo(10) step 2) {

    }

    for (i in 1..10) {

    }

    for (i in 1.downTo(10)) {

    }

    for (i in 1.until(10)) {

    }

    val range = 11..100
    val progression = 11..100 step 2
    lll("${range.javaClass}")
    lll("${progression.javaClass}")

    range.first
    range.last


    val sq1 = range.asSequence()
    for (i in sq1) {
//        lll("i = $i")
    }

    val sq2 = sequenceOf(1, 2, 3, 4)

    val sq3 = generateSequence(1) {
        return@generateSequence it
    }

    val sq4 = sq2.takeWhile {
        return@takeWhile it < 4
    }

    sq4.forEach {
        lll("it = $it")
    }


    val numbers = sequence {
        yield(1).also { lll("获取1") }
        yield(2).also { lll("获取2") }
        yield(3).also { lll("获取3") }
        yieldAll(sq1).also { lll("获取另外sequence") }
    }

    numbers.filter {
        it > 2
    }

    val list1 = 1.rangeTo(10).toMutableList()
    list1.sortWith(Comparator<Int> { p0, p1 -> p1 - p0 })

    lll("list1 = $list1")

    val result = list1.map {
        it * 2
    }

    lll("list1 = $result")


    val colors = listOf<String>("red", "yellow", "blue")
    val size = listOf<String>("big", "medium", "small", "empty")
    val result1 = colors zip size
    lll("result=  $result1")

    lll("${colors zip size zip colors}")

    val animals = listOf("fox", "bear", "wolf")

    lll(colors.zip(animals) { color, animal -> "The ${animal.capitalize()} is $color" })

    val numbers1 = listOf("one", "two", "three", "four")
    lll(numbers1.associateWith { it.length })


    val r1 = numbers1.associateBy {
        lll("it = $it")
        it.length
    }
    lll("r1 = $r1")


    val r2 = numbers1.associate {
        return@associate Pair<String, Int>(it, it.length)
    }

    lll("r2 = $r2")

    val containers = listOf(
        listOf("one", "two", "three"),
        listOf("four", "five", "six"),
        listOf("seven", "eight")
    )
    lll(containers)

    lll(containers.flatten())

    val t1 = listOf<Map<String, String>>(
        mapOf("one" to "1", "two" to "2"),
        mapOf("three" to "3", "four" to "4")
    )

    lll(t1)
    lll(t1.flatMap {
        val returnList: MutableList<String> = mutableListOf()
        for (entry in it) {
            returnList.add(entry.key)
            returnList.add(entry.value)
        }
        return@flatMap returnList
    })

    val numberss = listOf("one", "two", "three", "four")

    lll(numberss)
    lll(numberss.joinToString())

    val listString = StringBuffer("The list of numbers: ")
    numberss.joinTo(listString, separator = "|", prefix = "?prefix?", postfix = "?postfix?")
    lll(listString)


    val n1 = (1..100).toList()
    lll(n1.joinToString(limit = 10, truncated = "<...>"))


    val n2 = listOf("one", "two", "three", "four")
    lll(n2.joinToString { "Element: ${it.toUpperCase()}" })

    val (x1, x2) = n1.partition {
        it > 50
    }
    lll("x1 = $x1")
    lll("x2 = $x2")
    val r7 = n1.any {
        it > 100
    }


    val tt1 = listOf<String>("1", "2", "#2", "333", "4444", "#")
    val tt2 = listOf<String>("#")

    lll(tt1 - tt2)

    lll(tt1 - "#")

    val r6 = tt1.groupBy {
        it.length
    }

    val rr1 = tt1.groupingBy {
        it.first()
    }


    lll(rr1.eachCount())
//    lll(rr1.aggregate())
//    lll(rr1.eachCount())

    val fruits = listOf("cherry", "blueberry", "citrus", "apple", "apricot", "banana", "coconut")

    val evenFruits = fruits.groupingBy { it.first() }
        .fold({ key, _ -> key to mutableListOf<String>() },
            { _, accumulator, element ->
                accumulator.also { (_, list) -> if (element.length % 2 == 0) list.add(element) }
            })

    val sorted = evenFruits.values.sortedBy { it.first }
    lll(sorted) // [(a, []), (b, [banana]), (c, [cherry, citrus])]


    val nn2 = listOf("one", "two", "three", "four", "five", "six", "seven", "one")

    lll(nn2.slice(1..2))
    lll(nn2.slice(2..3))
    lll(nn2.slice(listOf(0, 1, 2)))
    lll(nn2.slice(listOf(0)))
    lll(nn2.take(3))
    lll(nn2.take(10))

    lll(nn2.takeLast(1))

    lll(nn2.takeWhile {
        it.startsWith("o") || it.startsWith("t")
    })

    lll(nn2.dropWhile {
        it.length <= 3
    })


    lll(nn2.chunked(3))

    lll(nn2.chunked(3) {
        it.sumBy {
            it.length
        }
    })


    val nn3 = listOf("one", "two", "three", "four", "five")
    lll(
        "nn3 = " + nn3.windowed(3)
    )


    lll(nn3.windowed(3, partialWindows = false))
    lll(nn3.windowed(3, partialWindows = true))


    lll(nn3.zipWithNext())

    nn3.elementAt(1)
    nn3.get(1)
    nn3[1]
    val x = nn3.elementAtOrNull(1)

    nn3.first()
    nn3.first {
        it.length > 3
    }
    nn3.firstOrNull() {
        it.length > 3
    }
    nn3.find {
        it.length > 3
    }
    nn3.findLast {
        it.length > 3
    }
    nn3.last()


    val x11 = Comparator { t1: String, t2: String -> 1 }

    nn2.sortedBy {
        it.length
    }

    nn3.sortedWith(compareBy {
        1
    })

    lll(nn3.sorted())

    val nn4 = listOf<Int>(1, 2, 34, 6, 87, 89, 9, 56, 678, 98, 2)
    lll("nn4 = $nn4")
    lll("nn4 after sorted = ${nn4.sorted()}")
    lll("nn4 reverser = ${nn4.reversed()}")

    val nn6 = mutableListOf<String>("one", "two", "three", "four")
    val reversedNumbers = nn6.asReversed()
    lll(reversedNumbers)
    nn6[0] = "changedOne"
    lll("nn6 = $nn6")
    lll("reversedNumbers = $reversedNumbers")

    lll(nn6.max())
    lll(nn6.min())

    lll("x > y ${"axabc" > "byabd"}")

    lll(nn6.maxBy {
        it.length
    })

    lll(nn6.minBy {
        it.length
    })

    lll(nn6.sumBy {
        lll("$it = ${it.length}")
        it.length
    })

    val q1 = intArrayOf(1, 2, 3, 4, 5, 6)
    lll(q1.fold(initial = 100) { acc, next ->
        lll("acc = $acc")
        lll("next = $next")
        acc + next
    })

    lll(q1.reduce { acc, next ->
        lll("acc = $acc")
        lll("next = $next")
        acc + next
    })

    val q2 = mutableListOf<String>("!", "@", "3", "2", "1")


    val xxx = q2.remove("!")
    val xxx2 = q2 - "1"


    lll(q2)
    lll(q2.removeAll(listOf("3", "2", "1123123")))
    lll(q2)

    q2.retainAll(listOf("1"))
    lll(q2)

    val q3 = listOf<Int>(1, 234, 34, 45, 46, 87, 98, 231)

    val q3_result = q3.sorted()
    lll("q2.sort() = ${q3_result}")
    lll("binary search = ${q3_result.binarySearch(231)}")

    val productList = listOf(
        Product("WebStorm", 49.0),
        Product("AppCode", 99.0),
        Product("DotTrace", 129.0),
        Product("ReSharper", 149.0)
    )

    lll(productList.binarySearch(Product("AppCode", 99.0),
        compareBy<Product> { it.price }.thenBy { it.name })
    )


    lll(productList.sortedWith(compareBy() {
        it.price
    }))
}

data class Product(val name: String, val price: Double)
