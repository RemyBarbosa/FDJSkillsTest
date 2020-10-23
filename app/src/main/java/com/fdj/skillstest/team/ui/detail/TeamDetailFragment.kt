package com.fdj.skillstest.team.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.fdj.skillstest.R
import com.fdj.skillstest.application.HomeActivity
import com.fdj.skillstest.interface_adapter.team.TeamDetailContract
import com.fdj.skillstest.interface_adapter.team.model.TeamUIDetailModel
import com.fdj.skillstest.use_case.base.Result
import com.fdj.skillstest.util.hide
import com.fdj.skillstest.util.loadImage
import com.fdj.skillstest.util.show
import kotlinx.android.synthetic.main.fragment_team_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TeamDetailFragment : Fragment(), TeamDetailContract.View {

    private val presenter: TeamDetailContract.Presenter by inject { parametersOf(this) }

    private val args: TeamDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
        presenter.initView(args.teamName)
        (requireActivity() as HomeActivity).title = args.teamName
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun showTeamDetail(teamUIDetailModel: TeamUIDetailModel) {
        progress_bar.hide()
        team_detail_banner.loadImage(teamUIDetailModel.bannerUrl)
        team_detail_country.text = teamUIDetailModel.country
        team_detail_league.text = teamUIDetailModel.league
        team_detail_description.text = teamUIDetailModel.description
        team_detail_layout.show()
    }

    override fun showError(error: Result.Error) {
        progress_bar.hide()
        team_detail_error.show()
        team_detail_error.text = error.toString()
    }

    override fun showLoader() {
        progress_bar.show()
    }
}
