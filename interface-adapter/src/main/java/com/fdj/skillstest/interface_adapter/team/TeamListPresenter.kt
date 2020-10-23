package com.fdj.skillstest.interface_adapter.team

import com.fdj.skillstest.use_case.base.Result
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TeamListPresenter(
    private val teamManager: TeamManager
) : TeamListContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    private var view: TeamListContract.View? = null

    override fun initView(search: String) {
        view?.showLoader()
        launch {
            val teamListResult = teamManager.getTeamList(search)
            if (teamListResult is Result.Success) {
                withContext(Dispatchers.Main) {
                    view?.showTeamList(teamListResult.data)
                }
            } else {
                if (teamListResult is Result.Error) {
                    withContext(Dispatchers.Main) {
                        view?.showError(teamListResult)
                    }
                }
            }
        }
    }

    override fun takeView(view: TeamListContract.View) {
        this.view = view
    }

    override fun destroy() {
        view = null
        job.cancel()
    }
}