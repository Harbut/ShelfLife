package den.harbut.shelflife.utils.history

interface Action {
    fun apply()
    fun undo()
}
