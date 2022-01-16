package fragmenttest.test.ro.usersapp.presentation.usersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import fragmenttest.test.ro.usersapp.R
import fragmenttest.test.ro.usersapp.databinding.UsersFragmentBinding

class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModels()
    lateinit var binding: UsersFragmentBinding

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
        setUp(view)
    }

    private fun setUp(view: View) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.apply {

            }
        }
    }

    companion object {
        const val TAG = "UsersFragment"

        fun newInstance() = UsersFragment()
    }
}