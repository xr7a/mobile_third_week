package ru.desyatov.old_melnik_mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val toolbar: Toolbar = findViewById(R.id.detailToolbar)
        toolbar.setNavigationIcon(R.drawable.arrow_back)
        toolbar.setNavigationOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val title = intent.getStringExtra("title")
        val subtitle = intent.getStringExtra("subtitle")
        val image = intent.getIntExtra("imageResId", 0)

        val imageView: ImageView = findViewById(R.id.imageView2)
        val titleTextView: TextView = findViewById(R.id.textView)
        val subtitleTextView: TextView = findViewById(R.id.textView2)

        imageView.setImageResource(image)
        titleTextView.text = title
        subtitleTextView.text = subtitle


    }
}