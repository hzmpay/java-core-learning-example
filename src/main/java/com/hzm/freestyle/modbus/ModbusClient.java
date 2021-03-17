package com.hzm.freestyle.modbus;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年03月17日
 */
@Slf4j
public class ModbusClient {

    public static final Map<String, ChannelHandlerContext> CLIENT_MAP = new HashMap<>();

    public static final String HOST = "127.0.0.1";
    public static final Integer PORT = 502;

    public static void addClient(ChannelHandlerContext context) {
        CLIENT_MAP.put(context.channel().remoteAddress().toString(), context);
    }

    public static void main(String[] args) throws InterruptedException {

        initNetty();
        byte[] msgByte = buildMsg();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入");
            if (scanner.hasNext()) {
                scanner.next();
                sendMsg(msgByte);
            }
        }
    }

    public static byte[] buildMsg() {
        byte[] msgByte = new byte[8];
        // 第一个字节为设备地址
        msgByte[0] = 01;
        // 第二个字节“03”为功能码
        msgByte[1] = 03;

        // 第三，四个字节为起始地址
        msgByte[2] = 00;
        msgByte[3] = 00;

        // 第五，六个字节为查询的字节长度
        msgByte[4] = 00;
        msgByte[5] = 9;

        // 最后两个字节“85 C9”为CRC校验
        String crcDataMsg = CRCUtil.byteToStr(msgByte, 6);
        String crc = CRCUtil.getCRC(crcDataMsg);

        msgByte[6] = (byte) Integer.parseInt(crc.substring(0, 2), 16);
        msgByte[7] = (byte) Integer.parseInt(crc.substring(2, 4), 16);
        return msgByte;
    }

    public static void initNetty() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(HOST, PORT))
                    // 保持连接不断开
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //加入心跳
//                            ch.pipeline().addLast(new IdleStateHandler(0,30,0));
                            //业务处理类
                            ch.pipeline().addLast(new ModbusClientHandler());
                        }
                    });
//            ChannelFuture channelFuture = bootstrap.connect().sync();
//            channelFuture.channel().closeFuture().sync();

            ChannelFuture future = bootstrap.connect();
            future.addListener(new GenericFutureListener<ChannelFuture>() {
                @Override
                public void operationComplete(ChannelFuture f) throws Exception {
                    if (f.isSuccess()) {
                        log.info("{} TCP客户端端开启成功，主机:{},端口:{}", "modbus", HOST, PORT);
                    } else {
                        log.error("{}TCP客户端连接失败,主机:{},端口:{}", "modbus", HOST, PORT);
                    }
                }

            });


        } catch (Exception e) {
            log.error("", e);
            group.shutdownGracefully().sync();
        }
    }

    public static void sendMsg(byte[] bytes) {
        log.info("发送消息 ：{} ", CRCUtil.byteToStr(bytes, bytes.length));
        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        ChannelHandlerContext context = CLIENT_MAP.get("/" + HOST + ":" + PORT);
        context.writeAndFlush(byteBuf);
    }
}
