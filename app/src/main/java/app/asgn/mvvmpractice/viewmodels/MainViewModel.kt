package app.asgn.mvvmpractice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.asgn.mvvmpractice.models.PullsDataClass
import app.asgn.mvvmpractice.models.PullsDataClassItem
import app.asgn.mvvmpractice.repository.MainRepository
import app.asgn.mvvmpractice.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    private val _pullsInfo : MutableLiveData<Resource<PullsDataClass>> = MutableLiveData()
    val pullsInfo : LiveData<Resource<PullsDataClass>> = _pullsInfo

    private val _currentPullInfo: MutableLiveData<PullsDataClassItem> = MutableLiveData()
    val currentPullInfo: LiveData<PullsDataClassItem> = _currentPullInfo

    fun getPullsInfo(pullState : String){
        _pullsInfo.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.fetchPullList(pullState)
            if(!mainRepository.pullsInfoList.isNullOrEmpty()){
                _pullsInfo.postValue(Resource.success(mainRepository.pullsInfoList))
            }
            else {
                _pullsInfo.postValue(Resource.error("data not found", mainRepository.pullsInfoList))
            }
        }
    }

    fun setCurrentPullItem(item : PullsDataClassItem){
        _currentPullInfo.postValue(item)
    }

    fun getCurrentPullItem() : PullsDataClassItem {
        return _currentPullInfo.value!!
    }

}