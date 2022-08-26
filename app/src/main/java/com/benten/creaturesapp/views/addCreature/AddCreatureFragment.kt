package com.benten.creaturesapp.views.addCreature

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.benten.creaturesapp.databinding.FragmentAddCreatureBinding
import com.benten.creaturesapp.model.AttributeStore
import com.benten.creaturesapp.model.AttributeValue
import com.benten.creaturesapp.views.avatars.AvatarChoserBottomSheet

class AddCreatureFragment : Fragment(), AvatarChooser {
    private var _binding: FragmentAddCreatureBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCreatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureSpinnerAdapters()
        configureSpinnerListeners()
        binding.avatarImageView.setOnClickListener {
            AvatarChoserBottomSheet.newInstance()
                .show(childFragmentManager, AvatarChoserBottomSheet.AVATAR_BOTTTOM_SHEET_KEY)
        }
    }

    private fun configureSpinnerAdapters() {
        binding.intelligence.adapter = ArrayAdapter<AttributeValue>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE
        )
        binding.strength.adapter = ArrayAdapter<AttributeValue>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item, AttributeStore.STRENGTH
        )
        binding.endurance.adapter = ArrayAdapter<AttributeValue>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item, AttributeStore.ENDURANCE
        )
    }

    private fun configureSpinnerListeners() {
        binding.intelligence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    requireContext(),
                    AttributeStore.INTELLIGENCE[position].name,
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // TODO: handle selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // TODO: handle selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun chooseAvatar(avatar: Int) {
        binding.avatarImageView.setImageResource(avatar)
        binding.tapLabel.isVisible = false
    }
}