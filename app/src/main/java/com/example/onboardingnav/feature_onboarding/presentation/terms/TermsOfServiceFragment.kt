package com.example.onboardingnav.feature_onboarding.presentation.terms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.onboardingnav.R
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.databinding.FragmentTermsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TermsOfServiceFragment : Fragment() {

    private var _binding: FragmentTermsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TermsOfServiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTermsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            viewModel.onNextClick()
        }

        binding.agreeCb.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isAccepted = isChecked
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collectLatest {
                    when(it) {
                        is UiEvent.Success -> {
                            findNavController().navigate(R.id.action_termsFragment_to_credentialsFragment)
                        }
                        is UiEvent.ShowSnackbar -> {
                            Snackbar.make(binding.root, it.message.asString(requireContext()), Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}