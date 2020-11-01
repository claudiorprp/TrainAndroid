package pt.atp.bobi.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import pt.atp.bobi.R
import java.lang.IllegalStateException

class IntentConfirmationFragment(private val intent: Intent,
                                 private val parentView: View?) : DialogFragment() {

    private lateinit var snackBar: Snackbar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.intent_confirmation)
                    .setPositiveButton(R.string.confirmation
                    ) { _, _ ->
                        startActivity(intent)
                    }
                    .setNegativeButton(R.string.negation
                    ) { _, _ ->
                        if(parentView != null){
                            showSnackbar(parentView)
                        } else {
                            AlertDialog.Builder(it)
                                    .setMessage(R.string.operation_cancelled)
                                    .create()
                                    .show()
                        }
                    }
            //Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun showSnackbar(parentView: View){
        snackBar = Snackbar.make(
                parentView,
                R.string.operation_cancelled,
                Snackbar.LENGTH_SHORT)

        snackBar.show()
    }


}