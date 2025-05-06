package kz.step.jsonplaceholder.presentation.curr_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ToDosFragment : Fragment() {

    private val args: ToDosFragmentArgs by navArgs()

    private val vm: ToDosViewModel by viewModel {
        parametersOf(args.userId)
    }

    private lateinit var rvTodos: RecyclerView
    private lateinit var adapter: TodosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_to_dos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecycler()
        observeViewModel()
    }

    private fun initViews(view: View) {
        rvTodos = view.findViewById(R.id.rv_todos)
    }

    private fun setupRecycler() {
        adapter = TodosAdapter(layoutInflater)
        rvTodos.layoutManager = LinearLayoutManager(requireContext())
        rvTodos.adapter = adapter
        rvTodos.addItemDecoration(
            SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        )
    }

    private fun observeViewModel() {
        vm.todosLiveData.observe(viewLifecycleOwner) { todos ->
            adapter.submitList(todos)
        }
    }
}
