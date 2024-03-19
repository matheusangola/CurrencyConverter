package com.example.currencyconverter.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Currency(@StringRes val nameRes: Int,
                @StringRes val descriptionRes: Int)
               // @DrawableRes val imageRes: Int)
