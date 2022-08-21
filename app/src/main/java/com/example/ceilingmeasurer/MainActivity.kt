package com.example.ceilingmeasurer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ceilingmeasurer.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            val fragment = PlanFragment.newInstance(binding.widthEt.text.toString(), binding.heightEt.text.toString())
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
//            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}