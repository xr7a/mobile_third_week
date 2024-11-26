package ru.desyatov.old_melnik_mobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.provider.FontsContractCompat.Columns
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.desyatov.old_melnik_mobile.ui.theme.OldmelnikmobileTheme
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageUrls = listOf(
            "https://povarmag.ru/upload/iblock/99b/99b82cf2afba20f30f3fd09a37fe613e.jpeg",
            "https://www.u-gago.ru/images/product_images/info_images/209_0.jpg",
            "https://avatars.mds.yandex.net/i?id=487399ad0df143f94fa0e89f1f85633b_l-5305969-images-thumbs&n=13",
            "https://cdn2.may24.ru/upload/iblock/318/31806aa8da092d1588f4e7e05f005893.jpg",
            "https://bereg-nv.ru/upload/iblock/72b/ut379ld96xzzj2ogadfikj3a4srovt1c.jpg"
        )

        val items = List(100) { index ->
            Item(
                title = "Melnik ${index + 1}",
                subtitle = "Subtitle ${index + 1}",
                imageResId = imageUrls[Random.nextInt(imageUrls.size)]
            )
        }

        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Old Melnik") },

                            )
                    },
                    content = { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        )
                        {
                            val context = LocalContext.current
                            ItemList(items) { selectedItem ->
                                val intent = Intent(context, DetailActivity::class.java)
                                intent.putExtra("title", selectedItem.title)
                                intent.putExtra("subtitle", selectedItem.subtitle)
                                intent.putExtra("imageResId", selectedItem.imageResId)

                                startActivity(intent)
                            }
                        }

                    }
                )

            }
        }
    }
}

@Composable
fun ItemList(items: List<Item>, onItemClick: (Item) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items.size) { index ->
            ItemRow(item = items[index], onClick = { onItemClick(items[index]) })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemRow(item: Item, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = item.imageResId,
            contentDescription = "Loaded Image",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 16.dp),
        )
        Column {
            Text(text = item.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = item.subtitle, fontSize = 14.sp, color = Color.Gray)
        }
    }
}