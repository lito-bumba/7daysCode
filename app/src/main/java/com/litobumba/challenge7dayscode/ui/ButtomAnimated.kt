package com.litobumba.challenge7dayscode.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.litobumba.challenge7dayscode.ui.theme.ColorPrimary


@Preview(showBackground = true)
@Composable
fun ButtomAnimated() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LoadingButtonIndicator()
    }
}

private const val IndicatorSize = 5
private const val NumIndicators = 3

enum class AnimationType {
    Bounce,
    Fade,
}

private val AnimationType.animationDuration: Int
    get() = when (this) {
        AnimationType.Bounce -> 300
        AnimationType.Fade -> 600
    }

private val AnimationType.animationDelay: Int
    get() = animationDuration / NumIndicators

private val AnimationType.initialValue: Float
    get() = when (this) {
        AnimationType.Bounce -> IndicatorSize / 2f
        AnimationType.Fade -> 1f
    }

private val AnimationType.targetValue: Float
    get() = when (this) {
        AnimationType.Bounce -> -IndicatorSize / 2f
        AnimationType.Fade -> .2f
    }

@Composable
private fun LoadingDot(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = color)
    )
}

@Composable
fun LoadingButtonIndicator() {
    var loading by remember { mutableStateOf(false) }

    LoadingButton(
        onClick = { loading = !loading },
        modifier = Modifier
            .padding(all = 16.dp)
            .height(50.dp)
            .width(130.dp),
        loading = loading,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = ColorPrimary
        ),
    ) {
        Text(text = "Refresh")
    }
}

@Composable
fun LoadingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    indicatorSpacing: Dp = 5.dp,
    content: @Composable () -> Unit,
) {
    val contentAlpha by animateFloatAsState(targetValue = if (loading) 0f else 1f)
    val loadingAlpha by animateFloatAsState(targetValue = if (loading) 1f else 0f)
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        shape = RoundedCornerShape(9.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            LoadingIndicator(
                animating = loading,
                modifier = Modifier.graphicsLayer { alpha = loadingAlpha },
                color = colors.contentColor(enabled = enabled).value,
                indicatorSpacing = indicatorSpacing,
                animationType = AnimationType.Bounce
            )
            Box(
                modifier = Modifier
                    .graphicsLayer { alpha = contentAlpha }
            ) {
                content()
            }
        }
    }
}

@Composable
private fun LoadingIndicator(
    animating: Boolean,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    indicatorSpacing: Dp = 5.dp,
    animationType: AnimationType
) {
    val animatedValues = List(IndicatorSize) { index ->
        var animatedValue by remember(key1 = animating, key2 = animationType) { mutableStateOf(0f) }
        LaunchedEffect(key1 = animating, key2 = animationType) {
            if (animating) {
                animate(
                    initialValue = animationType.initialValue,
                    targetValue = animationType.targetValue,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = animationType.animationDuration),
                        repeatMode = RepeatMode.Reverse,
                        initialStartOffset = StartOffset(animationType.animationDelay * index),
                    ),
                ) { value, _ -> animatedValue = value }
            }
        }
        animatedValue
    }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        animatedValues.forEach { animatedValue ->
            LoadingDot(
                modifier = Modifier
                    .padding(horizontal = indicatorSpacing)
                    .width(IndicatorSize.dp)
                    .aspectRatio(1f)
                    .then(
                        when (animationType) {
                            AnimationType.Bounce -> Modifier.offset(y = animatedValue.dp)
                            AnimationType.Fade -> Modifier.graphicsLayer { alpha = animatedValue }
                        }
                    ),
                color = color,
            )
        }
    }
}