import java.io.File

object Day6 : AOCDay {

    private fun getInput() = File("resources/Day6Input.txt")

    override fun part1(): Int {
        var count = 0
        var questions = ""
        fun countQuestions() { count += questions.toCharArray().distinct().count() }
        getInput().forEachLine {
            questions += it
            if (it.isEmpty()) {
                countQuestions()
                questions = ""
            }
        }
        countQuestions() //Last line
        return count
    }

    override fun part2(): Int {
        var count = 0
        val questions = mutableListOf<Set<Char>>()
        fun countQuestions() = with(questions) {
            var res = first()
            var i = 0
            while (res.isNotEmpty() && ++i < count()) res = res intersect get(i)
            count += res.count()
            clear()
        }
        getInput().forEachLine {
            if (it.isNotEmpty()) questions.add(it.toSet()) else countQuestions()
        }
        countQuestions() //Last line
        return count
    }
}
