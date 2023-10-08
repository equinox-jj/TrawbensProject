package com.trawlbens.core.helper

import com.trawlbens.core.R

fun parentPlatformIcon(platformName: String): Int {
    return when (platformName) {
        "PC" -> R.drawable.ic_windows
        "PlayStation" -> R.drawable.ic_playstation
        "Xbox" -> R.drawable.ic_xbox
//        "iOS" -> 1
//        "Android" -> 1
//        "Apple Macintosh" -> 1
//        "Linux" -> 1
//        "Atari" -> 1
//        "Commodore / Amiga" -> 1
        "SEGA" -> R.drawable.ic_nintendo
//        "3DO" -> 1
//        "Neo Geo" -> 1
//        "Web" -> 1
        else -> R.drawable.ic_windows
    }
}