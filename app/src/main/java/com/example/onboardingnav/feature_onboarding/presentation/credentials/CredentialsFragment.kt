package com.example.onboardingnav.feature_onboarding.presentation.credentials

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
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.R
import com.example.onboardingnav.databinding.FragmentCredentialsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CredentialsFragment : Fragment() {

    private var _binding: FragmentCredentialsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CredentialsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCredentialsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collectLatest {
                   when(it) {
                       is UiEvent.Success -> {
                           findNavController().navigate(R.id.action_credentialsFragment_to_personalInfoFragment)
                       }
                       is UiEvent.ShowSnackbar -> {
                           Snackbar.make(binding.root, it.message.asString(requireContext()), Snackbar.LENGTH_LONG).show()
                       }
                       else -> {}
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

        binding.emailEt.doAfterTextChanged {
            viewModel.email = it.toString()
        }

        binding.passwordEt.doAfterTextChanged {
            viewModel.password = it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}