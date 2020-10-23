package com.fdj.skillstest.interface_adapter.team

import com.fdj.skillstest.interface_adapter.base.BasePresenter
import com.fdj.skillstest.interface_adapter.team.model.TeamUIModel
import com.fdj.skillstest.use_case.base.Result

interface TeamListContract {
    interface View {
        fun showTeamList(teamUIModelList: List<TeamUIModel>)
        fun showError(error: Result.Error)
        fun showLoader()
    }

    interface Presenter : BasePresenter<View> {
        fun initView(search: String)
    }
}