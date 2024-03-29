package com.conte.design_system.module.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.conte.design_system.module.theme.AppColor
import com.conte.design_system.module.utils.ExtraLargeRoundedCornerShape
import com.conte.design_system.module.utils.ExtraSmallRoundedCornerShape
import com.conte.design_system.module.utils.LargeRoundedCornerShape
import com.conte.design_system.module.utils.MediumRoundedCornerShape
import com.conte.design_system.module.utils.SmallRoundedCornerShape

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    shape: RoundedCornerShape = ExtraSmallRoundedCornerShape,
    backgroundColor: Color = AppColor.Orange,
    contentColor: Color = AppColor.White,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
        shape = shape,
        enabled = enabled
    ) {
        AppText(
            text = text,
            color = contentColor,
            fontSize = fontSize
        )
    }
}

@Preview
@Composable
fun ExtraSmallRoundedCornerShapedButton() {
    AppButton(text = "Hello World", shape = ExtraSmallRoundedCornerShape) {}
}

@Preview
@Composable
fun SmallRoundedCornerShapeButton() {
    AppButton(text = "Hello World", shape = SmallRoundedCornerShape) {}
}

@Preview
@Composable
fun MediumRoundedCornerShapeButton() {
    AppButton(text = "Hello World", shape = MediumRoundedCornerShape) {}
}

@Preview
@Composable
fun LargeRoundedCornerShapeButton() {
    AppButton(text = "Hello World", shape = LargeRoundedCornerShape) {}
}

@Preview
@Composable
fun ExtraLargeRoundedCornerShapeButton() {
    AppButton(text = "Hello World", shape = ExtraLargeRoundedCornerShape) {}
}