package com.arifudesu.kadeproject2.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

/**
 * </> with <3 by SyakirArif
 * say no to plagiarism
 */
class ApiRepository {

    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}