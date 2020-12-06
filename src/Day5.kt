import java.io.File
import kotlin.math.ceil
import kotlin.math.floor

object Day5 : AOCDay {

    private fun getInput() = File("resources/Day5Input.txt")

    private fun getSeatsIds() = mutableListOf<Int>().apply {
        getInput().forEachLine {
            add(Integer.parseInt(it.replace('F', '0').replace('B', '1').replace('L', '0').replace('R', '1'), 2))
        }
    }.toIntArray()

    override fun part1() = getSeatsIds().maxOrNull() ?: 0

    override fun part2() = getSeatsIds().run {
        ((minOrNull() ?: 0)..(maxOrNull() ?: 0)).forEach {
            if (!this.contains(it)) return it
        }
        -1
    }
}
