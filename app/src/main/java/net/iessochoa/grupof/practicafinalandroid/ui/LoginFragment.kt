package net.iessochoa.grupof.practicafinalandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.runBlocking
import net.iessochoa.grupof.practicafinalandroid.R
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentLoginBinding
import net.iessochoa.grupof.practicafinalandroid.model.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

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

            val user = binding.username.text.toString()
            val password = binding.password.text.toString()

            if(user.isBlank() || password.isBlank()) {
                Toast.makeText(context, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            runBlocking {
                val log = viewModel.checkLogin(user)

                if (log == null){
                    viewModel.createLogin(user, password)
                    Toast.makeText(context, "New user created:$user", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPlaylistFragment(user))
                } else {

                    if(log.password == password && log.user == user){
                        Toast.makeText(context, ("Hello, " + log.user), Toast.LENGTH_LONG).show()
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPlaylistFragment(log.user))

                    } else
                        Toast.makeText(context,  getString(R.string.password_error) , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}