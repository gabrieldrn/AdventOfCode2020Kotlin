import java.io.File

object Day2 : AOCDay {

    private fun getInput() = File("resources/Day2Input.txt")

    override fun part1(): Int {
        var count = 0
        getInput().forEachLine {
            val range = (it.substringBefore('-').toInt()..it.substringAfter('-').substringBefore(' ').toInt())
            val character = it.substringAfter(' ').first()
            if (it.substringAfter(": ").count { c -> c == character } in range) count++
        }
        return count
    }

    override fun part2(): Int {
        var count = 0
        getInput().forEachLine {
            val firstCharPos = it.substringBefore('-').toInt() - 1
            val secondCharPos = it.substringAfter('-').substringBefore(' ').toInt() - 1
            val character = it.substringAfter(' ').first()
            val password = it.substringAfter(": ")
            if ((password[firstCharPos] == character).xor(password[secondCharPos] == character)) count++
        }
        return count
    }
}