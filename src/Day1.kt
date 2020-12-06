import java.io.File

object Day1 : AOCDay {

    private fun getInput() = mutableListOf<Int>().apply {
        File("resources/Day1Input.txt").forEachLine {
            add(it.toInt())
        }
    }.toIntArray()

    override fun part1(): String {
        val input = getInput()
        input.forEach { number1 ->
            input.forEach { number2 ->
                if (number1 != number2 && number1 + number2 == 2020) {
                    return "$number1 + $number2 = 2020 ==> $number1 * $number2 = ${number1 * number2}"
                }
            }
        }
        return "Not found :("
    }

    override fun part2(): String {
        val input = getInput()
        input.forEach { number1 ->
            input.forEach { number2 ->
                input.forEach { number3 ->
                    if (number1 != number2 && number2 != number3 && number1 + number2 + number3 == 2020) {
                        return "$number1 + $number2 + $number3 = 2020 ==> $number1 * $number2 * $number3 = ${number1 * number2 * number3}"
                    }
                }
            }
        }
        return "Not found :("
    }
}