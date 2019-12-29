package com.github.sigalhu.utils;

import com.google.common.io.BaseEncoding;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author huxujun
 * @date 2019/12/23
 */
public class CompressUtils {

    /**
     * 压缩字符串为字节数组
     *
     * @param content 待压缩字符串
     * @return 压缩后的字节数组
     */
    public static byte[] zip(String content) {
        return zip(content, StandardCharsets.UTF_8);
    }

    /**
     * 压缩字符串为字节数组
     *
     * @param content 待压缩字符串
     * @param charset 待压缩字符串编码
     * @return 压缩后的字节数组
     */
    public static byte[] zip(String content, Charset charset) {
        return zip(content.getBytes(charset));
    }

    /**
     * 压缩字符串为字节数组
     *
     * @param content 待压缩字节数组
     * @return 压缩后的字节数组
     */
    public static byte[] zip(byte[] content) {
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(content);
                BufferedInputStream bis = new BufferedInputStream(bais);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ZipOutputStream zos = new ZipOutputStream(baos);
                BufferedOutputStream bos = new BufferedOutputStream(zos)
        ) {
            zos.putNextEntry(new ZipEntry("content"));
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
            }
            bos.close();
            zos.close();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 解压为字节数组
     *
     * @param bytes 压缩后的字节数组
     * @return 解压后的字节数组
     */
    public static byte[] unzip(byte[] bytes) {
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ZipInputStream zis = new ZipInputStream(bais);

                ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            byte[] data = new byte[1024];
            if (zis.getNextEntry() != null) {
                int count;
                while ((count = zis.read(data)) != -1) {
                    baos.write(data, 0, count);
                }
            }
            return baos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 解压为字符串
     *
     * @param bytes 压缩后的字节数组
     * @return 解压后的字符串
     */
    public static String unzip2Str(byte[] bytes) {
        return new String(unzip(bytes), StandardCharsets.UTF_8);
    }

    /**
     * 压缩字符串并编码
     *
     * @param content 待压缩字符串
     * @return 解压后的字符串
     */
    public static String zipAndEncode(String content) {
        return zipAndEncode(content, StandardCharsets.UTF_8);
    }

    /**
     * 压缩字符串并编码
     *
     * @param content 待压缩字符串
     * @param charset 待压缩字符串编码
     * @return 解压后的字符串
     */
    public static String zipAndEncode(String content, Charset charset) {
        return zipAndEncode(content.getBytes(charset));
    }

    /**
     * 压缩字符串并编码
     *
     * @param content 待压缩字节数组
     * @return 解压后的字符串
     */
    public static String zipAndEncode(byte[] content) {
        return BaseEncoding.base64().encode(zip(content));
    }

    /**
     * 解压已编码的字符串为字节数组
     *
     * @param content 待解压字符串
     * @return 解压后的字节数组
     */
    public static byte[] decodeAndUnzip(String content) {
        return unzip(BaseEncoding.base64().decode(content));
    }

    /**
     * 解压已编码的字符串为字符串
     *
     * @param content 待解压字符串
     * @return 解压后的字符串
     */
    public static String decodeAndUnzip2Str(String content) {
        return new String(decodeAndUnzip(content), StandardCharsets.UTF_8);
    }
}
