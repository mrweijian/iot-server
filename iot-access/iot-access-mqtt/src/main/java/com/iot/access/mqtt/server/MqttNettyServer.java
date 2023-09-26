package com.iot.access.mqtt.server;

import com.iot.access.mqtt.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-18 15:46
 */
@Component
@Slf4j
public class MqttNettyServer {

    @Autowired
    private NettyServerHandler nettyServerHandler;

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(10);

        // 创建服务端的启动对象
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                // 使用NioServerSocketChannel作为服务器的通道实现
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                // 初始化服务器连接队列
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        // 编码器
                        pipeline.addLast("encoder", MqttEncoder.INSTANCE);
                        // 解码器
                        pipeline.addLast("decoder", new MqttDecoder());
                        pipeline.addLast(nettyServerHandler);
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind(9002).sync();

            // 等待服务监听关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException exception) {
            log.error("netty服务启动失败！，失败原因：{}", exception.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
