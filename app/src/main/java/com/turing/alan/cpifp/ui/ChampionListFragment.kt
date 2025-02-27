package com.turing.alan.cpifp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.Champion
import com.turing.alan.cpifp.data.ChampionsRepository
import com.turing.alan.cpifp.data.InMemoryChampionsRepository
import com.turing.alan.cpifp.databinding.FragmentChampionListBinding

class ChampionListFragment : Fragment() {
    private val repository:ChampionsRepository = InMemoryChampionsRepository.getInstance()
    private lateinit var binding: FragmentChampionListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_task_list, container, false)
        binding = FragmentChampionListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ChampionListAdapter(::toChampionDetail)
        val rv = binding.championList
        rv.adapter = adapter
        (rv.adapter as ChampionListAdapter).submitList(repository.getChampions())
    }

    override fun onResume() {
        super.onResume()
        val rv = binding.championList
        (rv.adapter as ChampionListAdapter).submitList(repository.getChampions())
    }

    private fun toChampionDetail(champion: Champion) {
        val action = ChampionListFragmentDirections.actionChampionListFragmentToChampionDetailFragment(champion.id)
        findNavController().navigate(action)
    }
}