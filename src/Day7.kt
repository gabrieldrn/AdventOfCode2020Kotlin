import java.io.File

object Day7 : AOCDay {

    private val bagsMap = mutableMapOf<String, Int>()

    private fun getInput() = File("resources/Day7Input.txt")

    override fun part1() : Any {
        val matrix = getAdjacencyMatrix()
        val parents = mutableListOf(bagsMap.getValue("shiny gold"))
        val visited = mutableListOf<Int>()
        while (parents.isNotEmpty()) {
            val p = parents.removeFirst()
            if (p !in visited) matrix.indices.forEach {
                if (matrix[it][p] > 0) parents.add(it)
            }
            visited.add(p)
        }
        return visited.apply { removeFirst() }.distinct().count()
    }

    override fun part2() : Int {
        val bagsMap2 = mutableMapOf<String, Map<String, Int>>()
        getInput().forEachLine {
            parseLine(it).let { p -> bagsMap2[p.first] = p.second.toMap() }
        }
        fun getStack(color: String) : Int {
            var total = 1
            if (bagsMap2.isNotEmpty()) {
                bagsMap2.getValue(color).forEach {
                    total += bagsMap2.getValue(color).getValue(it.key) * getStack(it.key)
                }
            }
            return total
        }
        return getStack("shiny gold") - 1
    }

    private fun getAdjacencyMatrix() : Array<Array<Int>> {
        val input = getInput()
        val len = input.readLines().count()
        val matrix = Array(len) { Array(len) { 0 } }
        val parses = mutableListOf<Pair<String, List<Pair<String, Int>>>>()
        bagsMap.clear()
        input.forEachLine {
            val parse = parseLine(it)
            parses.add(parse)
            bagsMap[parse.first] = bagsMap.size
        }
        parses.forEach { p ->
            p.second.forEach { matrix[bagsMap.getValue(p.first)][bagsMap.getValue(it.first)] = it.second }
        }
        return matrix
    }

    private val noise = arrayOf("bags", "contain", "bag,", "bag.", "bags,", "bags.", "no", "other")

    // (bag name, [(weight, bag name), ...])
    private fun parseLine(line: String) : Pair<String, List<Pair<String, Int>>> = with(line) {
        val words = split(' ').filter { it !in noise }.toCollection(ArrayDeque())
        return "${words.removeFirst()} ${words.removeFirst()}" to words.chunked(3).map {
            "${it[1]} ${it[2]}" to it[0].toInt()
        }
    }
}