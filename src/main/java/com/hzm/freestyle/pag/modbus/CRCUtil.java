package com.hzm.freestyle.pag.modbus;

import java.math.BigInteger;

public class CRCUtil {

    /**
     * 接收到的字节数组转换16进制字符串
     */
    public static String byteToStr(byte[] b, int size) {
        String ret = "";
        for (int i = 0; i < size; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        byte[] bytes = new BigInteger(str, 16).toByteArray();
        return bytes;
    }

    /**
     * 计算CRC16/Modbus校验码  低位在前,高位在后
     *
     * @param str 十六进制字符串
     * @return
     */
    public static String getCRC(String str) {
        byte[] bytes = toBytes(str);
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        String crc = Integer.toHexString(CRC);
        if (crc.length() == 2) {
            crc = "00" + crc;
        } else if (crc.length() == 3) {
            crc = "0" + crc;
        }
        crc = crc.substring(2, 4) + crc.substring(0, 2);
        return crc.toUpperCase();
    }


    /**
     * 充电子板协议-计算校验值
     */
    public static String getCRC_16CheckSum(String hexdata) {
        if (hexdata == null || hexdata.equals("")) {
            return "00";
        }
        hexdata = hexdata.replaceAll(" ", "");
        int total = 0;
        int len = hexdata.length();
        if (len % 2 != 0) {
            return "00";
        }
        int num = 0;
        while (num < len) {
            String s = hexdata.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        String data = hexInt(total);
        return data.substring(data.length() - 2, data.length());
    }

    public static String hexInt(int total) {
        int a = total / 256;
        int b = total % 256;
        if (a > 255) {
            return hexInt(a) + format(b);
        }
        return format(a) + format(b);
    }

    public static String format(int hex) {
        String hexa = Integer.toHexString(hex);
        int len = hexa.length();
        if (len < 2) {
            hexa = "0" + hexa;
        }
        return hexa;
    }


    public static byte[] str2bytes(Integer dataType, String data, Integer dataLength) {
        return sumHex(Integer.parseInt(data, dataType), dataLength);
    }

    /**
     * 将int转化为指定字节长度的byte数组
     *
     * @param tu5    数据
     * @param length 字节长度
     * @return
     */
    public static byte[] sumHex(int tu5, int length) {
        byte[] bytes5 = new byte[length];
        while (length > 0) {
            length--;
            bytes5[length] = (byte) (tu5 >> 8 * (bytes5.length - length - 1) & 0xFF);
        }
        return bytes5;
    }

    public static void main(String[] args) {
        System.out.println(getCRC("06"));
    }


}
