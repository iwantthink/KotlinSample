package com.ryan.kotlinsample.study

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.net.URLEncoder
import java.util.zip.GZIPInputStream
import java.util.zip.Inflater
import java.util.zip.InflaterInputStream


class NetworkUtils {

    companion object {

        fun get(iRequest: IRequest): String? {
            val inputStream: InputStream?
            val httpURLConnection: HttpURLConnection

            try {
                val url = URL(
                    buildUrl(
                        iRequest.getBaseUrl(),
                        iRequest.getParam(), iRequest.getEncrypt()
                    )
                )
                httpURLConnection = openUrlConnection(url)
                if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = wrapStream(
                        httpURLConnection.contentEncoding,
                        httpURLConnection.inputStream
                    )

                    return convertStreamToString(inputStream)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        private fun convertStreamToString(inputStream: InputStream?): String {
            val inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            bufferedReader.forEachLine {
                stringBuilder.append("$it\n")
            }
            bufferedReader.close()
            inputStreamReader.close()
            return stringBuilder.toString()
        }

        private fun wrapStream(contentEncoding: String?, inputStream: InputStream?): InputStream? {
            return if (contentEncoding != null && inputStream != null) {
                when {
                    "gzip".equals(contentEncoding, true) -> GZIPInputStream(inputStream)
                    "defalte".equals(contentEncoding, true) -> InflaterInputStream(inputStream, Inflater(false), 512)
                    else -> null
                }
            } else {
                null
            }
        }

        private fun openUrlConnection(url: URL): HttpURLConnection {
            val connection: URLConnection
            connection = url.openConnection() as HttpURLConnection

            return connection
        }

        private fun buildUrl(baseUrl: String, param: HashMap<String, String>, encrypt: IEncrypt): String {
            if (param.size == 0) {
                return baseUrl
            }
            var newBaseUrl: String = baseUrl
            if (!baseUrl.endsWith("?")) {
                newBaseUrl += "?"
            }

            val strBuilder: StringBuilder = StringBuilder()

            for ((key, value) in param) {
                strBuilder.append("$key=${URLEncoder.encode(value)}&")
            }
            newBaseUrl += strBuilder.toString().substring(0, strBuilder.length - 1)



            return "result"

        }

        interface IRequest {
            fun getBaseUrl(): String

            fun getMethod(): String

            fun getEncrypt(): IEncrypt

            fun getParam(): HashMap<String, String>

        }

        private data class FilePair constructor(val fileName: String, val data: ByteArray)

        interface IEncrypt {
            fun encrypt(src: String)

            fun dencrypt(src: String)
        }

    }


}

