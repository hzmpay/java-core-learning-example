package com.hzm.freestyle.modbus;

/**
 * Modbus工具类
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年03月17日
 */
public class ModbusUtil {

    public static byte[] buildFrame(String devAddr, String funCode, String[] datas) {
        int devAddrByte = Integer.parseInt(devAddr);
        int funCodeByte = Integer.parseInt(funCode, 16);
        byte[] dataByte = new byte[datas.length * 2 + 4];
        dataByte[0] = (byte) devAddrByte;
        dataByte[1] = (byte) funCodeByte;
        for (int i = 2, j = 0; i < dataByte.length - 2; i = i + 2, j++) {
            String value = Integer.toHexString(Integer.parseInt(datas[j]));
            if (value.length() == 1) {
                value = "000" + value;
            } else if (value.length() == 2) {
                value = "00" + value;
            } else if (value.length() == 3) {
                value = "0" + value;
            }
            dataByte[i + 1] = (byte) Integer.parseInt(value.substring(2, 4), 16);
            dataByte[i] = (byte) Integer.parseInt(value.substring(0, 2), 16);
        }
        String data = CRCUtil.byteToStr(dataByte, dataByte.length - 2);
        String crc = CRCUtil.getCRC(data);
        dataByte[dataByte.length - 2] = (byte) Integer.parseInt(crc.substring(0, 2), 16);
        dataByte[dataByte.length - 1] = (byte) Integer.parseInt(crc.substring(2, 4), 16);
        return dataByte;
    }

    public static void main(String[] args) {
        byte[] bytes = buildFrame("01", "03", new String[]{"0", "9"});
        for (byte aByte : bytes) {
            System.out.print(aByte + " ");
        }
    }
}
