package com.delaiglesia.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.delaiglesia.navigationcomponent.databinding.FragmentTermsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TermsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TermsFragment : Fragment() {
    private lateinit var binding: FragmentTermsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_terms, container, false)
        return binding.root
    }
}