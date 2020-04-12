package com.example.authorization.utils;

/**
 * @Author ltx
 * @Date 19:53 2020/4/9
 */
public class Base64 {

    private static java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    private static java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();


    public static byte[] decode(String base64) {
        return decoder.decode(base64.getBytes());
    }

    public static String encode(byte[] bytes) throws Exception {
        return new String(encoder.encode(bytes));
    }
//
//    public static String encodeFile(String filePath) throws Exception {
//        byte[] bytes = fileToByte(filePath);
//        return encode(bytes);
//    }

//    public static void decodeToFile(String filePath, String base64) throws Exception {
//        byte[] bytes = decode(base64);
//        byteArrayToFile(bytes, filePath);
//    }
//
//    public static byte[] fileToByte(String filePath) throws Exception {
//        byte[] data = new byte[0];
//        File file = new File(filePath);
//        if (file.exists()) {
//            FileInputStream in = new FileInputStream(file);
//            ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
//            byte[] cache = new byte[CACHE_SIZE];
//            int nRead = 0;
//            while ((nRead = in.read(cache)) != -1) {
//                out.write(cache, 0, nRead);
//                out.flush();
//            }
//            out.close();
//            in.close();
//            data = out.toByteArray();
//        }
//        return data;
//    }
//
//    public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {
//        InputStream in = new ByteArrayInputStream(bytes);
//        File destFile = new File(filePath);
//        if (!destFile.getParentFile().exists()) {
//            destFile.getParentFile().mkdirs();
//        }
//        destFile.createNewFile();
//        OutputStream out = new FileOutputStream(destFile);
//        byte[] cache = new byte[CACHE_SIZE];
//        int nRead = 0;
//        while ((nRead = in.read(cache)) != -1) {
//            out.write(cache, 0, nRead);
//            out.flush();
//        }
//        out.close();
//        in.close();
//    }

}
