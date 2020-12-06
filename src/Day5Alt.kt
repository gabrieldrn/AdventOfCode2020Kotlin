import java.io.File
import java.lang.Integer.parseInt
import kotlin.math.ceil
import kotlin.math.floor

object Day5Alt : AOCDay {

    private fun getInput() = File("resources/Day5Input.txt")

    private fun parseSeatsIds() = mutableListOf<Int>().apply {
        getInput().forEachLine {
            var rowMin = 0.0
            var rowMax = 127.0
            var row = 0
            var colMin = 0.0
            var colMax = 7.0
            var col = 0
            it.substring(0, 7).forEachIndexed { i, c ->
                if (i == 6) {
                    row = (if (c == 'F') rowMin else rowMax).toInt()
                } else when (c) {
                    'F' -> rowMax = floor(rowMax - ((rowMax - rowMin) / 2)) //⌊max - ((max - min) / 2)⌋
                    'B' -> rowMin = ceil(((rowMax - rowMin) / 2) + rowMin)  //⌈((max - min) / 2) + min⌉
                }
            }
            it.substring(7).forEachIndexed { i, c ->
                if (i == 2) {
                    col = (if (c == 'L') colMin else colMax).toInt()
                } else when (c) {
                    'L' -> colMax = floor(colMax - ((colMax - colMin) / 2))
                    'R' -> colMin = ceil(((colMax - colMin) / 2) + colMin)
                }
            }
            add(row * 8 + col)
        }
    }

    override fun part1() = parseSeatsIds().maxOrNull() ?: 0

    override fun part2() = parseSeatsIds().run {
        ((minOrNull() ?: 0)..(maxOrNull() ?: 0)).forEach {
            if (!contains(it)) return it
        }
        -1
    }
}