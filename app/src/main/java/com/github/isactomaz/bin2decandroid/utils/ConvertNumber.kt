package com.github.isactomaz.bin2decandroid.utils

import com.github.isactomaz.bin2dec.utils.Bin2Dec

fun convertNumber(binary: String): Pair<String, Boolean> {
    return try {
        Pair(Bin2Dec.bin2Dec(binary).toString(), false)
    } catch (e: IllegalArgumentException) {
        Pair("Error", true)
    }
}