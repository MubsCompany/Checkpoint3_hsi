package com.mubs.checkpoint3_hsi.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Person(
    @StringRes val personNameRes: Int,
    @StringRes val personCodeRes: Int,
    @StringRes val personAgeRes: Int,
    @StringRes val personHometownRes: Int,
    @DrawableRes val personProfileImageRes: Int
)
