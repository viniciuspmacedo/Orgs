package com.viniciusmacedo.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.formatToBRL(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}