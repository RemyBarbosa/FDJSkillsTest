package com.fdj.skillstest.interface_adapter.team

import com.fdj.skillstest.use_case.base.Result
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TeamDetailPresenter(
    private val teamManager: TeamManager
) : TeamDetailContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    private var view: TeamDetailContract.View? = null

    override fun initView(name: String) {
        view?.showLoader()
        launch {
            val teamResult = teamManager.getTeam(name)
            if (teamResult is Result.Success) {
                withContext(Dispatchers.Main) {
                    view?.showTeamDetail(teamResult.data)
                }
            } else {
                if (teamResult is Result.Error) {
                    withContext(Dispatchers.Main) {
                        view?.showError(teamResult)
                    }
                }
            }
        }
    }

    override fun takeView(view: TeamDetailContract.View) {
        this.view = view
    }

    override fun destroy() {
        view = null
        job.cancel()
    }
}