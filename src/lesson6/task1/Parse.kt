@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */

fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    val mapMonths = mapOf("января" to 1, "февраля" to 2, "марта" to 3, "апреля" to 4, "мая" to 5,
            "июня" to 6, "июля" to 7, "августа" to 8, "сентября" to 9, "октября" to 10, "ноября" to 11, "декабря" to 12)
    return try {
        if (parts.size != 3) throw NumberFormatException("Description")
        val month = mapMonths[parts[1]] ?: throw NumberFormatException("Description")
        val day = parts[0].toInt()
        val year = parts[2].toInt()
        if (day !in 1..daysInMonth(month, year)) throw NumberFormatException("Description")
        String.format("%02d.%02d.%d", day, month, year)
    } catch (e: NumberFormatException) {
        ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    val mapMonths = mapOf(1 to "января", 2 to "февраля", 3 to "марта", 4 to "апреля", 5 to "мая",
            6 to "июня", 7 to "июля", 8 to "августа", 9 to "сентября", 10 to "октября", 11 to "ноября", 12 to "декабря")
    return try {
        if (parts.size != 3) throw NumberFormatException("Description")
        val month = mapMonths[parts[1].toInt()] ?: throw NumberFormatException("Description")
        val day = parts[0].toInt()
        val year = parts[2].toInt()
        if (day !in 1..daysInMonth(parts[1].toInt(), year)) throw NumberFormatException("Description")
        String.format("%d %s %d", day, month, year)
    } catch (e: NumberFormatException) {
        ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    return try {
        if (phone.all { it in "+-()1234567890 " })
            phone.filter { it in "+1234567890" }
        else throw NumberFormatException("Description")
    } catch (e: NumberFormatException) {
        ""
    }
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val parts = jumps.split(" ")
    val list = mutableListOf<Int>()
    return try {
        if (jumps.any { it in "1234567890" }) {
            for (part in parts) if (part !in listOf("-", "%")) list.add(part.toInt())
        } else throw NumberFormatException("Description")
        val res = list.sorted()
        res.last()
    } catch (e: NumberFormatException) {
        -1
    }
}


/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val list = mutableListOf<Int>()
    return try {
        if (jumps.all { it in "1234567890+%- " }) {
            val parts = jumps.split(" ")
            for (i in 0 until parts.size - 1 step 2) if (parts[i + 1] == "+") list.add(parts[i].toInt())
            list.max()!!
        } else throw NumberFormatException("Description")
    } catch (e: NumberFormatException) {
        -1
    }
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val list = mutableListOf<String>()
    if (!expression.all { it in "1234567890+- " }) throw IllegalArgumentException()
    if (expression.startsWith('+') || expression.startsWith('-')) throw IllegalArgumentException()
    val parts = expression.split(" ")
    for (i in 0 until parts.size - 1 step 2) {
        if (parts[i] !in listOf("+", "-") && parts[i].toInt() >= 0)
            list.add(parts[i]) else throw IllegalArgumentException()
        if (parts[i + 1] in listOf("+", "-")) list.add(parts[i + 1]) else throw IllegalArgumentException()
    }
    if (parts.last().toInt() >= 0) list.add(parts.last())
    if (list.size != parts.size) throw IllegalArgumentException()
    var sum = list.first().toInt()
    for (i in 1 until list.size - 1 step 2) {
        if (list[i] == "+") sum += list[i + 1].toInt()
        if (list[i] == "-") sum += -list[i + 1].toInt()
    }
    return sum
}


/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val parts = str.toLowerCase().split(" ")
    var sum = 0
    var t = 0
    return try {
        for (i in 0 until parts.size - 1) {
            if (parts[i] == parts[i + 1]) sum = t
            else t += parts[i].length + 1
        }
        if (sum == 0) throw NumberFormatException("Description")
        else sum
    } catch (e: NumberFormatException) {
        -1
    }
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val part = description.split("; ")
    val map = mutableMapOf<String, Double>()
    var max = 0.0
    var res = ""
    try {
        for (parts in part) {
            val t = parts.split(" ")
            map[t.first()] = t.last().toDouble()
        }

        for ((key, value) in map) {
            if (value >= max) {
                max = value
                res = key
            }
        }
        return res
    } catch (e: NumberFormatException) {
        return ""
    }
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */

//В задаче ниже в качестве ключа для map я делаю разность кода нужного символа (I,V,X,L...) и кода "А"
// из ASCLL соответсвенно. Значение - римская цифра в десятичной системе счисления
fun fromRoman(roman: String): Int {
    var sum = 0
    val map = mutableMapOf(8 to 1, 21 to 5, 23 to 10, 11 to 50, 2 to 100, 3 to 500, 12 to 1000)
    return try {
        if (roman.all { it in "IVXLCDM" }) {
            for (i in 0 until roman.length - 1) {
                sum += if (map[roman[i].toInt() - 'A'.toInt()]!! < map[roman[i + 1].toInt() - 'A'.toInt()]!!)
                    -map[roman[i].toInt() - 'A'.toInt()]!!
                else map[roman[i].toInt() - 'A'.toInt()]!!
            }
            sum += map[roman.last().toInt() - 'A'.toInt()]!!
            sum
        } else throw NumberFormatException("Description")
    } catch (e: NumberFormatException) {
        -1
    }
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
