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
import com.delaiglesia.navigationcomponent.databinding.FragmentGetEmailBinding
import com.delaiglesia.navigationcomponent.databinding.FragmentGetNameBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GetEmailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GetEmailFragment : Fragment() {
    private lateinit var binding: FragmentGetEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_email, container, false)
        binding.submitButton.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editTextTextEmailAddress.text.toString())) {
                var nameInput = requireArguments().getString("name_input")
                val bundle: Bundle =
                    bundleOf(
                        "name_input" to nameInput,
                        "email_input" to binding.editTextTextEmailAddress.text.toString()
                    )
                it.findNavController().navigate(R.id.action_getEmailFragment_to_welcomeFragment, bundle)
            }else{
                Toast.makeText(activity, "Please insert your email", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


}