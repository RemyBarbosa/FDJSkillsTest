package com.fdj.skillstest.team.ui.list

import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.*
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fdj.skillstest.R
import com.fdj.skillstest.application.HomeActivity
import com.fdj.skillstest.interface_adapter.team.TeamListContract
import com.fdj.skillstest.interface_adapter.team.model.TeamUIModel
import com.fdj.skillstest.team.ui.adapter.TeamListAdapter
import com.fdj.skillstest.use_case.base.Result
import com.fdj.skillstest.util.hide
import com.fdj.skillstest.util.hideKeyboard
import com.fdj.skillstest.util.show
import kotlinx.android.synthetic.main.fragment_team_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TeamListFragment : Fragment(), TeamListContract.View {

    private val presenter: TeamListContract.Presenter by inject { parametersOf(this) }

    private var currentSelection: String? = null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.team_list_fragment_menu, menu)
        initSearchView(menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
        (requireActivity() as HomeActivity).title = getString(R.string.app_name)
        currentSelection?.let { presenter.initView(it) }
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun showTeamList(teamUIModelList: List<TeamUIModel>) {
        progress_bar.hide()
        if (team_list.adapter == null) {
            team_list.adapter = TeamListAdapter { teamUIModel: TeamUIModel ->
                val action = TeamListFragmentDirections.actionTeamListFragmentToTeamrDetailFragment(
                    teamUIModel.name
                )
                findNavController().navigate(action)
            }
        }
        team_list.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        (team_list.adapter as TeamListAdapter).teamList = teamUIModelList.toMutableList()
    }

    override fun showError(error: Result.Error) {
        progress_bar.hide()
        team_list_error.show()
        team_list_error.text = error.toString()
    }

    override fun showLoader() {
        progress_bar.show()
    }

    private fun initSearchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.search)
        searchView.findViewById<AutoCompleteTextView>(R.id.search_src_text).threshold = 1

        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(android.R.id.text1)
        val cursorAdapter = SimpleCursorAdapter(
            context,
            R.layout.support_simple_spinner_dropdown_item,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        val suggestions = listOf("French Ligue 1")


        searchView.suggestionsAdapter = cursorAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                query?.let {
                    suggestions.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(query, true))
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                }

                cursorAdapter.changeCursor(cursor)
                return true
            }
        })

        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                hideKeyboard()
                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                val selection =
                    cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                searchView.setQuery(selection, false)

                currentSelection = selection
                presenter.initView(selection)
                return true
            }
        })
    }
}
