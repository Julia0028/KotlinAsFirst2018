@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.pow
import lesson3.task1.isPrime


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    val result = mutableListOf<Double>()
    for (i in 0 until v.size) {
        val element = v[i] * v[i]
        result.add(element)
    }
    return sqrt(result.sum())
}


/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = when {
    list.isNotEmpty() -> list.sum() / list.size
    else -> 0.0
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val middle = mean(list)
    for (i in 0 until list.size) {
        val element = list[i] - middle
        list[i] = element
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    val result = mutableListOf<Double>()
    for (i in 0 until a.size) {
        val element = a[i] * b[i]
        result.add(element)
    }
    return result.sum()
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    val result = mutableListOf<Double>()
    if (p.isNotEmpty()) {
        for (i in 1 until p.size) {
            val element = x.pow(i) * p[i]
            result.add(element)
        }
        return result.sum() + p.first()
    }
    return 0.0
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var number = n
    val list = mutableListOf<Int>()
    if (isPrime(n)) list.add(n)
    for (i in 2..n / 2) {
        while (number % i == 0) {
            list.add(i)
            number /= i
        }
        if (number == 1) break
    }
    return list
}


/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val list = factorize(n)
    return list.joinToString(separator = "*")
}


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var number = n
    while (number > 0) {
        list.add(number % base)
        number /= base
    }
    return if (list.isNotEmpty()) list.reversed()
    else listOf(0)
}


/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val list = convert(n, base)
    val str = StringBuilder()
    for (i in 0 until list.size) {
        if (list[i] > 9) str.append('a' - 10 + list[i])
        else str.append(list[i])
    }
    return str.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var sum = 0
    for (i in 0 until digits.size) {
        val result = digits[i] * base.toDouble().pow(digits.size - 1 - i).toInt()
        sum += result
    }
    return sum
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Int>()
    for (i in 0 until str.length) {
        if (str[i].toInt() >= 'a'.toInt()) list.add((str[i].toInt() - 'a'.toInt()) + 10)
        else list.add(str[i].toInt() - '0'.toInt())

    }
    return decimal(list, base)
}


/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val rome = arrayOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val arab = arrayOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    val result = StringBuilder()
    var number = n
    var arrayNumber = arab.size - 1
    // если в число входит послед элемент массива arab, учитываем, нет - берем след с конца
    while (number > 0) {
        while (arab[arrayNumber] > number) arrayNumber -= 1
        result.append(rome[arrayNumber])
        number -= arab[arrayNumber]
    }
    return result.toString()
}


/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

//С помощью функции digit вытаскиваю определенную цифру из n. Если n больше 1000, маюсь с прибавлением к строке
// "тысяч" и прибавляю соответствующее слово вместо цифры, если нет, просто заменяю цифру
fun digit(n: Int, k: Int): Int = n % (10.0.pow(k)).toInt() / (10.0.pow(k - 1)).toInt()

fun hundreds(n: Int): String = when (n) {
    1 -> "сто "
    2 -> "двести "
    3 -> "триста "
    4 -> "четыреста "
    5 -> "пятьсот "
    6 -> "шестьсот "
    7 -> "семьсот "
    8 -> "восемьсот "
    9 -> "девятьсот "
    else -> ""
}

fun ten(n: Int): String = when (n) {
    2 -> "двадцать "
    3 -> "тридцать "
    4 -> "сорок "
    5 -> "пятьдесят "
    6 -> "шестьдесят "
    7 -> "семьдесят "
    8 -> "восемьдесят "
    9 -> "девяносто "
    else -> ""
}

fun ten1(n: Int): String = when (n) {
    1 -> "одиннадцать "
    2 -> "двенадцать "
    3 -> "тринадцать "
    4 -> "четырнадцать "
    5 -> "пятнадцать "
    6 -> "шестнадцать "
    7 -> "семнадцать "
    8 -> "восемнадцать "
    9 -> "девятнадцать "
    else -> "десять "
}

fun one(n: Int): String = when (n) {
    1 -> ""
    2 -> ""
    3 -> "три "
    4 -> "четыре "
    5 -> "пять "
    6 -> "шесть "
    7 -> "семь "
    8 -> "восемь "
    9 -> "девять "
    else -> ""
}

fun russian(n: Int): String {
    val str = StringBuilder()
    if (n > 1000) {
        var num = digit(n, 6)
        str.append(hundreds(num))
        num = digit(n, 5)
        if (num != 1) {
            str.append(ten(num))
            val ed = digit(n, 4)
            if (ed == 0) str.append("тысяч ")
            else {
                str.append(one(ed))
                str.append(
                        when (ed) {
                            1 -> "одна тысяча "
                            2 -> "две тысячи "
                            3, 4 -> "тысячи "
                            else -> "тысяч "
                        }
                )
            }
        }
        if (num == 1) str.append(ten1(digit(n, 4)), "тысяч ")
    }
    var num = digit(n, 3)
    str.append(hundreds(num))
    num = digit(n, 2)
    if (num != 1) {
        str.append(ten(num))
        num = digit(n, 1)
        if (num == 1) str.append("один")
        if (num == 2) str.append("два")
        str.append(one(num))
    } else str.append(ten1(digit(n, 1)))
    return str.toString().trim()
}
