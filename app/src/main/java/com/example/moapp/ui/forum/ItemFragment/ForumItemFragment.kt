package com.example.moapp.ui.forum.ItemFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moapp.MoApplication
import com.example.moapp.R
import com.example.moapp.databinding.FragmentForumItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForumItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForumItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentForumItemBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val forumItemViewModel =
            ViewModelProvider(this).get(ForumItemFragmentViewModel::class.java)

        _binding = FragmentForumItemBinding.inflate(inflater, container, false)
        val root: View = binding.root

        forumItemViewModel.initData()
        val layoutManager = LinearLayoutManager(MoApplication.context)
        val recyclerView : androidx.recyclerview.widget.RecyclerView = root.findViewById(R.id.forumRecyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = ForumItemFragmentViewModel.ForumItemInfoAdapt(forumItemViewModel.forumItemList)
        recyclerView.adapter = adapter
        // Inflate the layout for this fragment
        // 设置间隔
        val dividerHeight = 4 // 间隔高度，单位dp
        recyclerView.addItemDecoration(
            ForumItemFragmentViewModel.DividerItemDecoration(
                MoApplication.context,
                dividerHeight
            )
        )
        return root

    }

}