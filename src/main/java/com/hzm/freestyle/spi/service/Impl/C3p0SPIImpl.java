package com.hzm.freestyle.spi.service.Impl;

import com.hzm.freestyle.spi.service.MysqlSPI;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月02日
 */
public class C3p0SPIImpl implements MysqlSPI {

    @Override
    public void driver() {
        System.out.println("C3p0的MysqlSpi实现。。。。。");
    }
}
