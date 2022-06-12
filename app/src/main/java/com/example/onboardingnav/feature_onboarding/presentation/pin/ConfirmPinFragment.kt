package com.example.onboardingnav.feature_onboarding.presentation.pin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onboardingnav.R
import com.example.onboardingnav.core.util.UiEvent
import com.example.onboardingnav.databinding.FragmentConfirmPinBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConfirmPinFragment : Fragment() {

    private var _binding: FragmentConfirmPinBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConfirmPinViewModel by viewModels()
    private val args: ConfirmPinFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pin = args.pin
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentConfirmPinBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collectLatest {
                    when(it) {
                        is UiEvent.Success -> {
                            findNavController().navigate(R.id.action_confirmPinFragment_to_homeFragment)
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

        binding.pinInclude.pinInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(e: Editable?) {
                viewModel.confirmedPin = e.toString()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}