package com.example.dialogboxes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dialogboxes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.simpleAlertButton.setOnClickListener {
            showSimpleAlertDialog()
        }

        binding.listDialogButton.setOnClickListener {
            showListDialog()
        }

        binding.singleChoiceDialogButton.setOnClickListener {
            showSingleChoiceDialog()
        }

        binding.multipleChoiceDialogButton.setOnClickListener {
            showMultipleChoiceDialog()
        }

    }

    private fun showSimpleAlertDialog() {
        val builder = AlertDialog.Builder(this).apply {
            setTitle("Simple Dialog")
            setMessage("This is a simple dialog. Do you want to continue?")
            setPositiveButton("ok") { dialog, _ ->
                dialog.dismiss()
            }
            setNegativeButton("cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun showListDialog() {
        val items = arrayOf("item 1", "item 2", "item 3", "item 4")
        AlertDialog.Builder(this).apply {
            setTitle("List Dialog")
            setItems(items) { _, which ->
                val selectedItem = items[which]
                Toast.makeText(this@MainActivity, "Selected: $selectedItem",
                    Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }
            .show()
    }

    private fun showSingleChoiceDialog() {
        val items = arrayOf("item 1", "item 2", "item 3", "item 4")
        var selectedItemIndex = 0
        AlertDialog.Builder(this).apply {
            setTitle("Single Choice Dialog")
            setSingleChoiceItems(items, selectedItemIndex) { _, which ->
                selectedItemIndex = which
            }
            setPositiveButton("ok") { _, _ ->
                val selectedItem = items[selectedItemIndex]
                Toast.makeText(this@MainActivity, "Selected $selectedItem", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }
    }

    private fun showMultipleChoiceDialog() {
        val items = arrayOf("option 1", "option 2", "option 3", "option 4")
        val selectedItems = mutableListOf<Int>()
        AlertDialog.Builder(this).apply {
            setTitle("Multiple Choice Dialog")
            setMultiChoiceItems(items, null) { _, which, isChecked ->
                if (isChecked) {
                    selectedItems.add(which)
                } else {
                    selectedItems.remove(which)
                }
            }
            setPositiveButton("ok") { dialog, _ ->
//              val selectedOptions = selectedItems.map { items[it] }.joinToString(", ") this was the original code
                val selectedOptions = selectedItems.joinToString(", ") { items[it] } // this is the simplified code
                Toast.makeText(this@MainActivity, "Selected: $selectedOptions", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            setNegativeButton("cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }
            .show()
    }
}