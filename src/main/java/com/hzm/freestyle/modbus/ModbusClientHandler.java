package com.hzm.freestyle.modbus;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年03月17日
 */
@Slf4j
public class ModbusClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("RemoteAddress" + ctx.channel().remoteAddress() + " active !");
        log.info("msg" + ctx.channel().id() + " active !");
        ModbusClient.addClient(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        log.info("接收到数据 ：{} ", buf.toString(CharsetUtil.UTF_8));

        handle(msg);
    }

    public void handle(Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes;
        int length = buf.readableBytes();
        if (buf.hasArray()) {
            bytes = buf.array();
        } else {
            bytes = new byte[length];
            buf.getBytes(buf.readerIndex(), bytes);
        }
        String payload = CRCUtil.byteToStr(bytes, bytes.length);
        log.info("[ModbusRtu 同步服务器 ]接受数据" + payload);
        try {
            String[] returnData = getStringArray(payload);
            String str = String.join(" ", returnData);
            log.info("格式化之后的数据 ：{} ", str);
        } catch (Exception e) {
            log.error("解析数据失败", e);
        } finally {
            ReferenceCountUtil.release(buf);
        }
    }

    public static String[] getStringArray(String data) {
        String[] result = new String[data.length() / 2];
        for (int i = 0; i < data.length() / 2; i++) {
            result[i] = data.substring(i * 2, i * 2 + 2);
        }
        return result;
    }
}
