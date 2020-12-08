import java.io.File

object Day4 : AOCDay {

    private val passportFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    private val emptyFields: MutableMap<String, String> get() = passportFields.associateBy { "" }.toMutableMap()

    private fun getInput() = File("resources/Day4Input.txt")

    override fun part1(): Int {
        var validPassports = 0
        var fields = mutableListOf<String>()
        fun checkFields() { if (fields.containsAll(passportFields)) validPassports++ }

        getInput().forEachLine { line ->
            if (line.isEmpty()) {
                checkFields()
                fields = mutableListOf()
            } else line.split(' ').forEach {
                fields.add(it.substringBefore(':'))
            }
        }
        checkFields() //last line
        return validPassports
    }

    override fun part2(): Int {
        var validPassports = 0
        var fields = emptyFields
        fun checkFields() { if (fields.areFieldsValid) validPassports++ }

        getInput().forEachLine { line ->
            if (line.isEmpty()) {
                checkFields()
                fields = emptyFields //reset
            } else line.split(' ').forEach {
                fields[it.substringBefore(':')] = it.substringAfter(':')
            }
        }
        checkFields() //last line
        return validPassports
    }

    private val MutableMap<String, String>.areFieldsValid
        get() = isByrValid && isIyrValid && isEyrValid && isHgtValid && isHclValid && isEclValid && isPidValid

    private val MutableMap<String, String>.isByrValid
        get() = get("byr")?.run { isNotEmpty() && toInt() in 1920..2002 } ?: false

    private val MutableMap<String, String>.isIyrValid
        get() = get("iyr")?.run { isNotEmpty() && toInt() in 2010..2020 } ?: false

    private val MutableMap<String, String>.isEyrValid
        get() = get("eyr")?.run { isNotEmpty() && toInt() in 2020..2030 } ?: false

    private val MutableMap<String, String>.isHgtValid
        get() = get("hgt")?.run {
            val value = substring(0, length - 2)
            when (substring(length - 2, length)) {
                "cm" -> value.toInt() in 150..193
                "in" -> value.toInt() in 59..76
                else -> false
            }
        } ?: false

    private val MutableMap<String, String>.isHclValid
        get() = get("hcl")?.contains(Regex("#([0-9]|[a-f]){6}")) ?: false

    private val MutableMap<String, String>.isEclValid
        get() = get("ecl") in arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    private val MutableMap<String, String>.isPidValid
        get() = get("pid")?.run { length == 9 && try { toInt(); true } catch (e: NumberFormatException) { false } } ?: false
}