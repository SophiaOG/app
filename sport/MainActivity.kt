package com.example.sport
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.LinearLayout
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun isEventListEmpty(eventList: RecyclerView): Boolean {
        val adapter = eventList.adapter
        return adapter?.itemCount != 0

    }

    private fun setupMonthView(monthContainer:LinearLayout, eventList : RecyclerView) {
        val expandButton = monthContainer.findViewById<Button>(R.id.button)
        val isEvent = isEventListEmpty(eventList)
        if (isEvent) {
            expandButton.visibility = View.VISIBLE
            setupExpandButton(expandButton, eventList)
        }
        else {
            expandButton.visibility = View.GONE

        }
    }
    private fun setupExpandButton(button: Button, eventList: RecyclerView) {
        button.setOnClickListener{
            if (eventList.visibility == View.GONE) {
                eventList.visibility = View.VISIBLE
                button.text = "v"
            }
            else {
                eventList.visibility = View.GONE
                button.text = ">"
            }
        }

    }
}
