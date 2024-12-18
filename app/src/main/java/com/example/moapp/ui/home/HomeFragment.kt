package com.example.moapp.ui.home

import android.content.Intent
import android.graphics.Typeface
import android.media.midi.MidiOutputPort
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moapp.MoApplication
import com.example.moapp.R
import com.example.moapp.databinding.FragmentHomeBinding
import com.example.moapp.ui.forumIndex.ForumIndexActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val homeForum = root.findViewById<Button>(R.id.homeForum)
        val homeAppreciation = root.findViewById<Button>(R.id.homeAppreciation)
        val homeRead = root.findViewById<Button>(R.id.homeRead)
        val homeWrite = root.findViewById<Button>(R.id.homeWrite)

        homeForum.setOnClickListener {
            val intent = Intent(MoApplication.context , ForumIndexActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}