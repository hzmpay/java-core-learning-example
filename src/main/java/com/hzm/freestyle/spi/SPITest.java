package com.hzm.freestyle.spi;

import com.hzm.freestyle.spi.service.MysqlSPI;

import java.util.ServiceLoader;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月02日
 */
public class SPITest {

    public static void main(String[] args) {
        final ServiceLoader<MysqlSPI> serviceLoader = ServiceLoader.load(MysqlSPI.class);

        for (MysqlSPI mysqlSPI : serviceLoader) {
            mysqlSPI.driver();
        }

    }
}
