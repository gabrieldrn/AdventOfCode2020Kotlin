import java.io.File

object Day8 : AOCDay {

    private fun getInput() = mutableListOf<Pair<Pair<Instruction, Int>, Boolean>>().apply {
        fun getInst(line: String) = with(line.split(' ')) {
            when (get(0)) {
                "acc" -> Instruction.ACC; "jmp" -> Instruction.JMP; else -> Instruction.NOP
            } to Integer.parseInt(get(1))
        }
        File("resources/Day8Input.txt").forEachLine {
            add(getInst(it) to false)
        }
    }

    private var accumulator = 0

    private fun runInstructions(instructions: MutableList<Pair<Pair<Instruction, Int>, Boolean>>): Int {
        var index = 0
        accumulator = 0
        while (index < instructions.size) {
            val instruction = instructions[index]
            if (instruction.second) return accumulator
            instructions[index] = instruction.first to true
            when (instruction.first.first) {
                Instruction.ACC -> { accumulator += instruction.first.second; index++ }
                Instruction.JMP -> index += instruction.first.second
                Instruction.NOP -> index++
            }
        }
        return -1
    }

    override fun part1() = runInstructions(getInput())

    override fun part2(): Int {
        var index = 0
        do {
            val instructions = getInput()
            val invertedInstruction = when (instructions[index].first.first) {
                Instruction.ACC -> Instruction.ACC; Instruction.NOP -> Instruction.JMP; Instruction.JMP -> Instruction.NOP
            }
            instructions[index] = (invertedInstruction to instructions[index].first.second) to instructions[index].second
            index++
        } while (runInstructions(instructions) != -1)
        return accumulator
    }

    private enum class Instruction { ACC, NOP, JMP }
}
