package net.iessochoa.grupof.practicafinalandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentLoginBinding
import net.iessochoa.grupof.practicafinalandroid.model.Login
import net.iessochoa.grupof.practicafinalandroid.model.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: FragmentLoginBinding
    private val viewModel : LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener{

            runBlocking {
                var log = viewModel.checkLogin(binding.username.text.toString())

                if (log == null){
                    Toast.makeText(context, "That username does not exist.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, ("Hello, " + log!!.user), Toast.LENGTH_LONG).show()
                    val accion = LoginFragmentDirections.actionLoginFragmentToPlaylistFragment()
                    findNavController().navigate(accion)
                }
            }
        }
    }

}