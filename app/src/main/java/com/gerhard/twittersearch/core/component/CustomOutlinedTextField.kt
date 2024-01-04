package com.gerhard.twittersearch.core.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.gerhard.twittersearch.R
import com.gerhard.twittersearch.ui.theme.TwitterAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(onTextChanged: (inputText: String) -> Unit) {
    var text by remember { mutableStateOf("") }

    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = TwitterAppTheme.colors.accent01,
        unfocusedBorderColor = TwitterAppTheme.colors.accent03,
        textColor = TwitterAppTheme.colors.text01,
        cursorColor = TwitterAppTheme.colors.text01,
        focusedSupportingTextColor = TwitterAppTheme.colors.text01,
        unfocusedSupportingTextColor = TwitterAppTheme.colors.text01,
        placeholderColor = TwitterAppTheme.colors.text01
    )

    val customHintTextStyle = TextStyle(
        fontSize = 16.sp,
        color = TwitterAppTheme.colors.text01
    )

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onTextChanged(text)
        },
        label = {
            Text(
                text = stringResource(id = R.string.input_field_label),
                style = customHintTextStyle
            )
        },
        modifier = Modifier.fillMaxWidth(),
        isError = false,
        singleLine = true,
        colors = customTextFieldColors
    )

}