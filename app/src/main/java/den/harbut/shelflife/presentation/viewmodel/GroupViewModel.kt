package den.harbut.shelflife.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import den.harbut.shelflife.domain.model.Group
import den.harbut.shelflife.domain.usecase.group.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val getGroupsByScreenUseCase: GetGroupsByPageUseCase,
    private val addGroupUseCase: AddGroupUseCase,
    private val updateGroupUseCase: UpdateGroupUseCase,
    private val deleteGroupUseCase: DeleteGroupUseCase
) : ViewModel() {

    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups: StateFlow<List<Group>> = _groups

    fun loadGroups(screenId: Long) {
        viewModelScope.launch {
            getGroupsByScreenUseCase(screenId).collect {
                _groups.value = it
            }
        }
    }

    fun addGroup(group: Group) {
        viewModelScope.launch {
            addGroupUseCase(group)
        }
    }

    fun updateGroup(group: Group) {
        viewModelScope.launch {
            updateGroupUseCase(group)
        }
    }

    fun deleteGroup(id: Long) {
        viewModelScope.launch {
            deleteGroupUseCase(id)
        }
    }
}
