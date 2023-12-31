package com.ibrajix.roomdatabase.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ibrajix.roomdatabase.databinding.ActivityUserDetailsBinding
import com.ibrajix.roomdatabase.viewmodel.DataStoreViewModel
import com.ibrajix.roomdatabase.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding

    private val userViewModel: UserViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getUserDetails()

        handleClicks()

    }

    private fun handleClicks() {

        binding.btnClearRecord.setOnClickListener {

            //clear record from room database
            userViewModel.doDeleteSingleUserRecord()

            //remove the datastorage key
            dataStoreViewModel.setSavedKey(false)

            //go to main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }

    private fun getUserDetails() {

        this.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                userViewModel.doGetUserDetails()
                userViewModel.userDetails.collect { users ->

                    for (user in users) {
                        //set data into view
                        binding.txtName.text = user.name
                        binding.txtAge.text = user.age
                        binding.txtNumber.text = user.number
                    }

                }
            }
        }


    }


//    private fun handleClicks() {
//        binding.btnClearRecord.setOnClickListener {
//            // Clear record from room database
//            userViewModel.doDeleteSingleUserRecord()
//            // Remove the data storage key
//            dataStoreViewModel.setSavedKey(false)
//            // Go to the main activity
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun getUserDetails() {
//        this.lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                userViewModel.doGetUserDetails()
//                userViewModel.userDetails.collect { users ->
//                    // Update the RecyclerView adapter with the user details
//                    userAdapter.submitList(users)
//                }
//            }
//        }
//    }

}