package pt.atp.bobi

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private var untilFinished = 10000L
    private lateinit var snackBar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        snackBar = Snackbar.make(
                findViewById(R.id.countdown),
                "Done",
                Snackbar.LENGTH_SHORT)
    }

    override fun onResume() {
        super.onResume()

        startCountDownTimer(untilFinished)
    }

    override fun onPause() {
        super.onPause()

        timer.cancel()
    }

    private fun startCountDownTimer(time: Long) {
        timer = object: CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                untilFinished = millisUntilFinished
                findViewById<TextView>(R.id.countdown).text = "Second remaining: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                findViewById<TextView>(R.id.countdown).text = "Done"
                snackBar.show()
            }

        }

        timer.start()
    }
}