package pt.atp.bobi

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import pt.atp.bobi.fragments.IntentConfirmationFragment

private const val REQUEST_IMAGE_CAPTURE = 100

class MainActivity : AppCompatActivity() {

    private var navConfirm : IntentConfirmationFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        findViewById<Button>(R.id.button).setOnClickListener{
            openNativeCamera()
        }

        findViewById<Button>(R.id.button2).setOnClickListener{
            openDetailsActivity(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.imageView).setImageBitmap(imageBitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun openNativeCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun openDetailsActivity(view: View) {
        val intent = Intent(this, DetailsActivity::class.java)
        navConfirm = navConfirm ?: IntentConfirmationFragment(intent, view)

        navConfirm?.show(supportFragmentManager, "NoticeDialogFragment")
    }
}