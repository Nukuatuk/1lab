package com.example.a1lab


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    GalleryScreen()
                }
            }
        }
    }
}

@Composable
fun GalleryScreen() {
    // Коллекция в памяти (минимум 3)
    val artworks = remember {
        listOf(
            Artwork(
                imageRes = R.drawable.art_1,
                titleRes = R.string.art1_title,
                authorRes = R.string.art1_author,
                cdRes = R.string.art1_cd
            ),
            Artwork(
                imageRes = R.drawable.art_2,
                titleRes = R.string.art2_title,
                authorRes = R.string.art2_author,
                cdRes = R.string.art2_cd
            ),
            Artwork(
                imageRes = R.drawable.art_3,
                titleRes = R.string.art3_title,
                authorRes = R.string.art3_author,
                cdRes = R.string.art3_cd
            )
        )
    }

    // Состояние, сохраняемое при повороте
    var index by rememberSaveable { mutableIntStateOf(0) }

    val isFirst = index == 0
    val isLast = index == artworks.lastIndex
    val current = artworks[index]

    val cfg = LocalConfiguration.current
    val isLandscape = cfg.screenWidthDp > cfg.screenHeightDp

    // Скролл на случай маленьких экранов/шрифтов
    val scroll = rememberScrollState()

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scroll),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtworkImage(
                artwork = current,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtworkDescription(artwork = current)
                NavButtons(
                    isFirst = isFirst,
                    isLast = isLast,
                    onPrev = { if (!isFirst) index-- },
                    onNext = { if (!isLast) index++ }
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            ArtworkImage(artwork = current, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))
            ArtworkDescription(artwork = current)
            Spacer(Modifier.height(24.dp))
            NavButtons(
                isFirst = isFirst,
                isLast = isLast,
                onPrev = { if (!isFirst) index-- },
                onNext = { if (!isLast) index++ }
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ArtworkImage(artwork: Artwork, modifier: Modifier = Modifier) {
    // contentDescription из strings.xml (accessibility)
    val cd = stringResource(id = artwork.cdRes)

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = artwork.imageRes),
            contentDescription = cd,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.1f) // чтобы в портрете не занимало всю высоту
        )
    }
}

@Composable
private fun ArtworkDescription(artwork: Artwork) {
    Text(
        text = stringResource(id = artwork.titleRes),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )
    Text(
        text = stringResource(id = artwork.authorRes),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun NavButtons(
    isFirst: Boolean,
    isLast: Boolean,
    onPrev: () -> Unit,
    onNext: () -> Unit
) {
    val prevCd = stringResource(R.string.cd_prev_button)
    val nextCd = stringResource(R.string.cd_next_button)
    val prevText = stringResource(R.string.prev)
    val nextText = stringResource(R.string.next)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPrev,
            enabled = !isFirst,
            modifier = Modifier.semantics {
                contentDescription = prevCd
            }
        ) {
            Text(text = prevText)
        }

        Button(
            onClick = onNext,
            enabled = !isLast,
            modifier = Modifier.semantics {
                contentDescription = nextCd
            }
        ) {
            Text(text = nextText)
        }
    }
}