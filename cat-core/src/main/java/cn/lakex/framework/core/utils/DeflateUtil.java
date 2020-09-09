/*
 * Copyright (c) 2018. Born To Learn Education Inc. Ltd All Rights Reserved.
 */

package cn.lakex.framework.core.utils;

import net.dreamlu.mica.core.utils.Base64Util;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Based Deflater Utils
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/7/16 21:47
 * @since 3.0.0
 */
public class DeflateUtil {
    /**
     * 压缩
     */
    public static String compress(String data) {
        /**
         *     document: https://docs.oracle.com/javase/8/docs/api/java/util/zip/Deflater.html
         *     0 ~ 9 压缩等级 低到高
         *     public static final int BEST_COMPRESSION = 9;            最佳压缩的压缩级别。
         *     public static final int BEST_SPEED = 1;                  压缩级别最快的压缩。
         *     public static final int DEFAULT_COMPRESSION = -1;        默认压缩级别。
         *     public static final int DEFAULT_STRATEGY = 0;            默认压缩策略。
         *     public static final int DEFLATED = 8;                    压缩算法的压缩方法(目前唯一支持的压缩方法)。
         *     public static final int FILTERED = 1;                    压缩策略最适用于大部分数值较小且数据分布随机分布的数据。
         *     public static final int FULL_FLUSH = 3;                  压缩刷新模式，用于清除所有待处理的输出并重置拆卸器。
         *     public static final int HUFFMAN_ONLY = 2;                仅用于霍夫曼编码的压缩策略。
         *     public static final int NO_COMPRESSION = 0;              不压缩的压缩级别。
         *     public static final int NO_FLUSH = 0;                    用于实现最佳压缩结果的压缩刷新模式。
         *     public static final int SYNC_FLUSH = 2;                  用于清除所有未决输出的压缩刷新模式; 可能会降低某些压缩算法的压缩率。
         */

        // 1. 使用最佳压缩级别构建一个压缩器
        Deflater compressor = new Deflater(Deflater.BEST_COMPRESSION);

        // 2. 传入需要压缩的字符串
        compressor.setInput(data.getBytes());
        compressor.finish();

        // 3. 创建压缩过程中需要的缓冲区
        final byte[] bytes = new byte[256];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256);

        while (!compressor.finished()) {
            // 4. 开始压缩数据，并写入到指定的缓冲区
            int length = compressor.deflate(bytes);
            outputStream.write(bytes, 0, length);
        }
        // 5. 调用 end 完成压缩（丢弃未处理的内容）
        compressor.end();
        return Base64Util.encodeToString(outputStream.toByteArray());
    }

    /**
     * 解压缩
     */
    @Nullable
    public static String unCompress(String data) {
        byte[] decodeString = Base64Util.decode(data.getBytes());

        // 1. 创建一个新的解压缩器
        Inflater compressor = new Inflater();

        // 2. 传入需要解压缩的字符串
        compressor.setInput(decodeString);

        // 3. 创建解压缩缓冲区
        final byte[] bytes = new byte[256];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256);
        try {
            while (!compressor.finished()) {
                // 4. 开始解压缩，并将解压缩后的内容填充到缓存区
                int length = compressor.inflate(bytes);
                outputStream.write(bytes, 0, length);
            }
        } catch (DataFormatException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 5. 完成解压缩，关闭解压缩器
            compressor.end();
        }

        // 6. 返回解压缩后的字符串
        return outputStream.toString();
    }
}
