package fragmenttest.test.ro.usersapp.presentation.usersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fragmenttest.test.ro.usersapp.R
import fragmenttest.test.ro.usersapp.databinding.UsersFragmentBinding
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModels()
    lateinit var binding: UsersFragmentBinding
    private val userAdapter: UsersAdapter by lazy {
        UsersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<UsersFragmentBinding>(
            inflater,
            R.layout.users_fragment,
            container,
            false
        ).apply {
            this.viewModel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = userAdapter
            addItemDecoration(
                DividerItemDecoration(activity, LinearLayout.VERTICAL).apply {
                    val drawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.list_item_divider)
                    setDrawable(requireNotNull(drawable))
                }
            )
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.apply {
                usersList.collectLatest {
                    userAdapter.addItems(it)
                }
            }
        }

        viewModel.getUsers()
    }

    companion object {
        const val TAG = "UsersFragment"

        fun newInstance() = UsersFragment()
    }
}