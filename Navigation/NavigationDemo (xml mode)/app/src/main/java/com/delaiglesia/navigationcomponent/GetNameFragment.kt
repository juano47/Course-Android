package com.delaiglesia.navigationcomponent

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.delaiglesia.navigationcomponent.databinding.FragmentGetNameBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GetNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GetNameFragment : Fragment() {
    private lateinit var binding: FragmentGetNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_name, container, false)
        binding.nextButton.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editTextTextPersonName.text.toString())) {
                val bundle: Bundle =
                    bundleOf("name_input" to binding.editTextTextPersonName.text.toString())
                it.findNavController().navigate(R.id.action_getNameFragment_to_getEmailFragment, bundle)
            }else{
                Toast.makeText(activity, "Please insert your name", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}