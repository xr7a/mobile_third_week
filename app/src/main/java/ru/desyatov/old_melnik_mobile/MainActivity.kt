package ru.desyatov.old_melnik_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val items = listOf(
            Item("title 1", "sub title 1", R.drawable.ic_total_poxyi),
            Item("title 2", "sub title 2", R.drawable.ic_total_poxyi),
            Item("title 3", "sub title 3", R.drawable.ic_total_poxyi),
            Item("title 4", "sub title 4", R.drawable.ic_total_poxyi),
            Item("title 5", "sub title 5", R.drawable.ic_total_poxyi)
        )

        val linearLayoutContainer: LinearLayout = findViewById(R.id.linearLayoutContainer)

        for (item in items) {
            val itemView =
                layoutInflater.inflate(R.layout.item_layout, linearLayoutContainer, false)

            val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
            val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView)
            val imageView: ImageView = itemView.findViewById(R.id.imageView)

            titleTextView.text = item.title
            subtitleTextView.text = item.subtitle
            imageView.setImageResource(item.imageResId)
            itemView.setOnClickListener {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("subtitle", item.subtitle)
                intent.putExtra("imageResId", item.imageResId)
                startActivity(intent)
            }

            linearLayoutContainer.addView(itemView)
        }
    }
}