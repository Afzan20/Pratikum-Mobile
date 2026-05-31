package com.example.afzan_apps.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.afzan_apps.Home.Pertemuan_10.TenthActivity
import com.example.afzan_apps.Home.Pertemuan_9.NinthActivity
import com.example.afzan_apps.R
import com.example.afzan_apps.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnP9.setOnClickListener {
            val i= Intent(requireContext(), NinthActivity::class.java)
            startActivity(i)
        }

        binding.btnP10.setOnClickListener {
            val i= Intent(requireContext(), TenthActivity::class.java)
            startActivity(i)
        }
    }
}