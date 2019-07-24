package com.ryan.kotlinsample.study

import java.io.*

class StreamUtil {

    companion object {
        // 字节
        val fis = FileInputStream(File("a/b/c/d.txt"))
        val fis1 = BufferedInputStream(fis)

        val fos = FileOutputStream(File(""))
        val fos1 = BufferedOutputStream(fos)

        // 字符
        val fr = FileReader(File(""))
        val fr1 = BufferedReader(fr)

        val fw = FileWriter(File(""))
        val fw1 = BufferedWriter(fw)

        // 转换流   字节流->字符流
        val isr = InputStreamReader(fis)
        // 字符流->字节流
        val osw = OutputStreamWriter(fos)
    }


    fun readFileByByte(filePath: String) {
        val file = File(filePath)
        val inputStream: InputStream
        val outputStream: OutputStream

        inputStream = FileInputStream(file)
        outputStream = FileOutputStream(File("abc/acc/dd.file"))

        var temp: Int
        while ((inputStream.read().also { temp = it }) != null) {
            outputStream.write(temp)
        }
        try {
            while ((inputStream.read().also { temp = it }) != null) {
                outputStream.write(temp)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        } finally {
            inputStream.close()
            outputStream.close()
        }
    }

    fun readFileByCharacter(filePath: String) {
        val file = File(filePath)
        val isr: InputStreamReader = InputStreamReader(FileInputStream(File(filePath)))
        val osw: OutputStreamWriter = OutputStreamWriter(FileOutputStream(File(filePath)))
        val br = isr.buffered()
        val bw = osw.buffered()

        var temp: Int
        try {
            while (br.read().also { temp = it } != null) {
                bw.write(temp)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        } finally {
            isr.close()
            osw.close()
        }
    }


    /**
     * 允许在任意位置读写文件
     */
    fun randomAccessFile(filePath: String): Unit {
        val raf = RandomAccessFile(filePath, "rw")

        raf.seek(raf.length())
        raf.write(12)

    }
}

class Person : Serializable{

}

