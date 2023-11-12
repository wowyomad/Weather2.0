package by.bsuir.vadzim.weather20.ui_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text

@Composable
fun RoundedCard(icon: ImageVector? = null, text: String, onClick: () -> Unit = {}) {

    val shape = RoundedCornerShape(50.dp)

    Card(
        modifier = Modifier
            .height(80.dp)
            .padding(horizontal = 120.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = shape
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically

            ) {
                if (icon != null) {
                    Image(
                        modifier = Modifier
                            .weight(0.5f),
                        imageVector = icon, contentDescription = text
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = text
                    )
                }
                else {
                    Text(text = text)
                }


            }
        }
    }


}