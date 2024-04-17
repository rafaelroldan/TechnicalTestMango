package com.rafaelroldan.designsystem

import androidx.compose.runtime.Immutable
import com.rafaelroldan.designsystem.theme.CustomColor
import com.rafaelroldan.designsystem.theme.Font
import com.rafaelroldan.designsystem.theme.Spacing

@Immutable
class ThemeBase {
    val spacing = Spacing()
    val color = CustomColor()
    val font = Font()
}
