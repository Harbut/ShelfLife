package den.harbut.shelflife.utils.history

class HistoryManager {

    private val undoStack = ArrayDeque<Action>()
    private val redoStack = ArrayDeque<Action>()

    fun perform(action: Action) {
        action.apply()
        undoStack.addLast(action)
        redoStack.clear()
    }

    fun undo() {
        if (undoStack.isNotEmpty()) {
            val action = undoStack.removeLast()
            action.undo()
            redoStack.addLast(action)
        }
    }

    fun redo() {
        if (redoStack.isNotEmpty()) {
            val action = redoStack.removeLast()
            action.apply()
            undoStack.addLast(action)
        }
    }

    fun clear() {
        undoStack.clear()
        redoStack.clear()
    }

    fun canUndo(): Boolean = undoStack.isNotEmpty()
    fun canRedo(): Boolean = redoStack.isNotEmpty()
}
