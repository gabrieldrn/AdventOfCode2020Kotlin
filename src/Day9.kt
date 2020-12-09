import java.io.File

object Day9 : AOCDay {

    private fun getInput() = File("resources/Day9Input.txt")

    private fun getSums(numbers: List<Long>) = mutableListOf<Long>().apply {
        numbers.forEachIndexed { index1, n1 ->
            numbers.forEachIndexed { index2, n2 ->
                if (index1 != index2) add(n1 + n2)
            }
        }
    }.toList()

    override fun part1(): Long {
        val input = getInput().readLines().map { it.toLong() }
        (25 until input.size).forEach { index ->
            if (input[index] !in getSums(input.subList(index - 25, index))) {
                return input[index]
            }
        }
        return 0L
    }

    override fun part2(): Long {
        val weakSum = part1()
        if (weakSum == 0L) return 0L
        val input = getInput().readLines().map { it.toLong() }
        input.forEachIndexed { i, n ->
            var sum = n
            (i + 1 until input.size).forEach { i2 ->
                sum += input[i2]
                if (sum == weakSum) {
                    val sublist = input.subList(i, i2)
                    return (sublist.minOrNull() ?: 0L) + (sublist.maxOrNull() ?: 0L)
                }
            }
        }
        return 0L
    }
}
