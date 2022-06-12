package com.example.onboardingnav.feature_onboarding.presentation.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.onboardingnav.R
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.databinding.FragmentPersonalInfoBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonalInfoFragment : Fragment() {

    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PersonalInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collectLatest {
                    when(it) {
                        is UiEvent.Success -> {
                            findNavController().navigate(R.id.action_personalInfoFragment_to_newPinFragment)
                        }
                        is UiEvent.ShowSnackbar -> {
                            Snackbar.make(binding.root, it.message.asString(requireContext()), Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        setupViewListeners()
    }

    private fun setupViewListeners() {
        binding.nextBtn.setOnClickListener {
            viewModel.onNextClick()
        }

        binding.firstnameEt.doAfterTextChanged {
            viewModel.firstName = it.toString()
        }

        binding.lastnameEt.doAfterTextChanged {
            viewModel.lastName = it.toString()
        }

        binding.telephoneEt.doAfterTextChanged {
            viewModel.telephoneNo = it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}