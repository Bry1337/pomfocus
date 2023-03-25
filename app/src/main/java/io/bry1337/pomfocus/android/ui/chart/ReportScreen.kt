package io.bry1337.pomfocus.android.ui.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import io.bry1337.pomfocus.android.ui.theme.AppTheme

/**
 * Created by Bryan on 3/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Composable
fun ReportScreen(modifier: Modifier = Modifier) {
    val columnChart = columnChart()
    val chartEntryModelProducer = entryModelOf(4f, 12f, 8f, 16f)
    Column(modifier = modifier.fillMaxSize()) {
        Chart(
            chart = remember(columnChart) { columnChart },
            model = chartEntryModelProducer,
            startAxis = startAxis(),
            bottomAxis = bottomAxis(title = "3/23")
        )
    }
}

@Preview
@Composable
fun ReportScreenPreview() {
    AppTheme {
        ReportScreen(modifier = Modifier.background(Color.White))
    }
}
