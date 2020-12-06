import java.io.File

object Day3 : AOCDay {

    private fun getInput() = File("resources/Day3Input.txt")

    private fun getTreeEncounters(lines: List<String>, rightShift: Int, downShift: Int = 1) : Int {
        var pos = 0
        var treesCount = 0
        val lineLength = lines[0].length
        for (i in 0 until lines.count() step downShift) {
            if (lines[i][pos] == '#') treesCount++
            pos += rightShift
            if (pos >= lineLength) pos -= lineLength
        }
        return treesCount
    }

    override fun part1() = getTreeEncounters(getInput().readLines(), 3)

    override fun part2() : Long {
        val lines = getInput().readLines()
        var encountersTimes = 1L
        arrayOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2).forEach {
            encountersTimes *= getTreeEncounters(lines, it.first, it.second)
        }
        return encountersTimes
    }
}