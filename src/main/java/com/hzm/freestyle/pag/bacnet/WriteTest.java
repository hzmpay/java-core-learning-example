package com.hzm.freestyle.pag.bacnet;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.npdu.ip.IpNetwork;
import com.serotonin.bacnet4j.npdu.ip.IpNetworkBuilder;
import com.serotonin.bacnet4j.transport.DefaultTransport;
import com.serotonin.bacnet4j.type.enumerated.ObjectType;
import com.serotonin.bacnet4j.type.enumerated.PropertyIdentifier;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.Real;
import com.serotonin.bacnet4j.util.RequestUtils;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年03月23日
 */
public class WriteTest {

    public static void main(String[] args) throws Exception {
        LocalDevice d = null;
        try {
            IpNetwork ipNetwork = new IpNetworkBuilder()
                    .withLocalBindAddress("172.21.10.229")
                    .withSubnet("255.255.255.0", 24)
                    .withPort(47808)
                    .withReuseAddress(true)
                    .build();

            d = new LocalDevice(123, new DefaultTransport(ipNetwork));
            d.initialize();
            d.startRemoteDeviceDiscovery();

            int deviceNumber = 2228280;
            RemoteDevice rd = d.getRemoteDevice(deviceNumber).get();//获取远程设备

            //必须先修改out of service为true
            RequestUtils.writeProperty(d, rd, new ObjectIdentifier(ObjectType.analogValue, 0), PropertyIdentifier.outOfService, Boolean.TRUE);
            Thread.sleep(1000);
            //修改属性值
            RequestUtils.writePresentValue(d, rd, new ObjectIdentifier(ObjectType.analogValue, 0), new Real(55));
            Thread.sleep(2000);
            System.out.println("analogValue0= " + RequestUtils.readProperty(d, rd, new ObjectIdentifier(ObjectType.analogValue, 0), PropertyIdentifier.presentValue, null));

            Thread.sleep(1000);
            d.terminate();
        } catch (Exception e) {
            e.printStackTrace();
            if (d != null) {
                d.terminate();
            }
        }

    }
}
