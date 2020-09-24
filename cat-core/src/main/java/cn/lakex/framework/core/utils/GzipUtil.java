/*
 * Copyright (c) 2018. Born To Learn Education Inc. Ltd All Rights Reserved.
 */

package cn.lakex.framework.core.utils;

import lombok.experimental.UtilityClass;
import net.dreamlu.mica.core.utils.Base64Util;
import net.dreamlu.mica.core.utils.StringUtil;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Based Gzip Utils
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/7/16 22:13
 * @since 3.0.0
 */
@UtilityClass
public class GzipUtil {
    private final static String TAG = "GzipUtil";

    /**
     * 使用 Gzip 压缩字符串
     *
     * @param data 待压缩的字符串
     * @return After unzip {@link String}
     */
    public static String compress(String data) throws IOException {
        if (data.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            // Trace some error logs
            throw new RuntimeException(e);
        }
        return Base64Util.encodeToString(out.toByteArray());
    }

    /**
     * 使用 Gzip 解压字符串
     *
     * @param data 待压缩的字符串
     * @return After unzip {@link String}
     */
    @Nullable
    public String unCompress(String data) throws IOException {
        if (StringUtil.isBlank(data)) {
            return null;
        }
        byte[] decode = Base64Util.decodeFromString(data);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(decode);
        GZIPInputStream gzipStream = null;
        try {
            gzipStream = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gzipStream.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            // Trace some error logs
            throw new RuntimeException(e);
        } finally {
            out.close();
            if (gzipStream != null) {
                gzipStream.close();
            }
        }
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }
}
