package com.example.musicapp


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        btn_sign_up.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        loginLayout.setOnTouchListener(object : OnSwipeListener(this) {
            init {
                setDragHorizontal(true)
                setExitScreenOnSwipe(true)
                setAnimationDelay(500)
            }

            override fun onSwipeLeft(distance: Float) {
                val intent = Intent(this@MainActivity, ListActivity::class.java)
                // start your next activity
                startActivity(intent)
            }

            override fun onSwipeRight(distance: Float) {
                val intent = Intent(this@MainActivity, ListActivity::class.java)
                // start your next activity
                startActivity(intent)

            }
        }
        )


    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {

    }

}
