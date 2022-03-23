package ru.nifontbus.lastcourse

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import ru.nifontbus.core_ui.bigPadding
import ru.nifontbus.core_ui.normalPadding

@Composable
fun MainScreen(
    onLesson1: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val width = maxWidth
        Column {
            Header(width)
            ButtonList(onLesson1 = onLesson1)
        }
    }
}

@Composable
private fun Header(width: Dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = bigPadding)
    ) {
        Text(
            text = stringResource(R.string.sLast),
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = width / 4)
        )
        Text(
            text = stringResource(R.string.sCourse),
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = width / 4)
        )
    }
}

@Composable
private fun ButtonList(
    onLesson1: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(normalPadding)
    )
    {
        Button(onClick = onLesson1) {
            Text(
                text = stringResource(R.string.sLesson1),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}