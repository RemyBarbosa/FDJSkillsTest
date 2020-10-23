package com.fdj.skillstest.interface_adapter.team

import com.fdj.skillstest.interface_adapter.base.BasePresenter
import com.fdj.skillstest.interface_adapter.team.model.TeamUIDetailModel
import com.fdj.skillstest.use_case.base.Result

interface TeamDetailContract {
    interface View {
        fun showTeamDetail(teamUIDetailModel: TeamUIDetailModel)
        fun showError(error: Result.Error)
        fun showLoader()
    }

    interface Presenter : BasePresenter<View> {
        fun initView(name: String)
    }
}