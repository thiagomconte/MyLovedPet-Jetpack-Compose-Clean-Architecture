package com.conte.design_system.module.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.conte.design_system.module.AppIcons
import com.conte.design_system.module.theme.AppColor
import com.conte.design_system.module.theme.JosefinSansFamily
import com.conte.design_system.module.utils.DateTransformation
import com.conte.design_system.module.utils.TimeTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppOutlineTextField(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    singleLine: Boolean = true,
    visualTransformationType: VisualTransformationType = VisualTransformationType.NONE,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    borderColor: Color = AppColor.Peach.copy(alpha = .6F),
    placeholder: String? = null
) {

    var validatedValue: String = value
    val maxSize = visualTransformationType.size
    when (visualTransformationType) {
        VisualTransformationType.DATE -> {
            if (value.length > maxSize) {
                validatedValue = value.dropLast(1)
            }
        }

        VisualTransformationType.TIME -> {
            if (value.length > maxSize) {
                validatedValue = value.dropLast(1)
            }
        }

        VisualTransformationType.NONE -> {}
    }


    OutlinedTextField(
        modifier = modifier,
        value = validatedValue,
        onValueChange = onValueChange,
        singleLine = singleLine,
        label = {
            AppText(
                text = label,
                color = AppColor.Black.copy(alpha = .8F),
                fontSize = fontSize
            )
        },
        textStyle = TextStyle(fontFamily = JosefinSansFamily, fontWeight = FontWeight.Normal),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = borderColor.copy(alpha = 1F),
            focusedBorderColor = borderColor.copy(alpha = 1F),
            unfocusedBorderColor = borderColor,
            errorBorderColor = AppColor.Error,
        ),
        visualTransformation = when (visualTransformationType) {
            VisualTransformationType.DATE -> DateTransformation()
            VisualTransformationType.TIME -> TimeTransformation()
            VisualTransformationType.NONE -> VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = isError,
        trailingIcon = if (isError) {
            { AppIcon(painter = AppIcons.AlertIcon) }
        } else null,
        placeholder = if (placeholder != null) {
            {
                AppText(
                    text = placeholder,
                    fontSize = fontSize,
                    color = AppColor.Black.copy(alpha = .5F)
                )
            }
        } else null
    )
}

enum class VisualTransformationType(val size: Int) {
    DATE(8), TIME(4), NONE(0);
}

@Preview
@Composable
fun AppOutlineTextFieldPreview() {
    AppOutlineTextField(
        value = "Ola mundo",
        onValueChange = {},
        label = ""
    )
}