package com.example.a1lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                BusinessCardScreen()
            }
        }
    }
}

@Composable
fun BusinessCardScreen() {
    val bg = colorResource(id = R.color.card_bg)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(dimensionResource(id = R.dimen.padding_screen))
    ) {
        // Скролл гарантирует, что на маленьких экранах ничего не обрежется
        val scrollState = rememberScrollState()

        // Адаптив: в портрете — колонка, в альбоме — "два блока" рядом
        val isLandscape = androidx.compose.ui.platform.LocalConfiguration.current.screenWidthDp >
                androidx.compose.ui.platform.LocalConfiguration.current.screenHeightDp

        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_l)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileBlock(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                ContactsBlock(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(dimensionResource(id = R.dimen.space_l)))
                ProfileBlock()
                Spacer(Modifier.height(dimensionResource(id = R.dimen.space_l)))
                ContactsBlock()
                Spacer(Modifier.height(dimensionResource(id = R.dimen.space_l)))
            }
        }
    }
}
@Composable
private fun ProfileBlock(modifier: Modifier = Modifier) {
    val logoSize = dimensionResource(id = R.dimen.logo_size)
    val nameSize = dimensionResource(id = R.dimen.text_name).value
    val titleSize = dimensionResource(id = R.dimen.text_title).value

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(logoSize)
                .background(colorResource(id = R.color.logo_bg))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = stringResource(id = R.string.cd_logo),
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(dimensionResource(id = R.dimen.space_m)))

        Text(
            text = stringResource(id = R.string.name),
            color = colorResource(id = R.color.text_primary),
            fontSize = androidx.compose.ui.unit.TextUnit(nameSize, androidx.compose.ui.unit.TextUnitType.Sp)
        )

        Spacer(Modifier.height(dimensionResource(id = R.dimen.space_s)))

        Text(
            text = stringResource(id = R.string.title),
            color = colorResource(id = R.color.text_secondary),
            fontSize = androidx.compose.ui.unit.TextUnit(titleSize, androidx.compose.ui.unit.TextUnitType.Sp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
@Composable
private fun ContactsBlock(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_s)),
        horizontalAlignment = Alignment.Start
    ) {
        ContactRow(icon = Icons.Default.Phone, cd = R.string.cd_phone, textRes = R.string.phone)
        ContactRow(icon = Icons.Default.Share, cd = R.string.cd_social, textRes = R.string.social)
        ContactRow(icon = Icons.Default.Email, cd = R.string.cd_email, textRes = R.string.email)
    }
}
@Composable
private fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    @StringRes cd: Int,
    @StringRes textRes: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_m))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = cd),
            tint = colorResource(id = R.color.accent)
        )
        Text(
            text = stringResource(id = textRes),
            color = colorResource(id = R.color.text_primary),
            fontSize = dimensionResource(id = R.dimen.text_contact).value
                .let { androidx.compose.ui.unit.TextUnit(it, androidx.compose.ui.unit.TextUnitType.Sp) },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
