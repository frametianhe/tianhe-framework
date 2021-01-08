package com.tianhe.framework.netty.online.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLEngine;
import java.net.ConnectException;
import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractChannel {

	private static final Logger logger = LoggerFactory.getLogger(AbstractChannel.class);

	private ChannelInfo channelInfo;

	private String charsetName;

	private ChannelHandler handel;

	private IdleStateHandler idleStateHandler;

	public void connect(String msg) throws ChannelException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(idleStateHandler);
					ch.pipeline().addLast(new HttpResponseDecoder());
					ch.pipeline().addLast(new HttpRequestEncoder());
					ch.pipeline().addLast(handel);
				}
			});

			ChannelFuture f = b.connect(channelInfo.getFrontIp(), Integer.parseInt(channelInfo.getFrontPort())).sync();
			URI uriPost = new URI(channelInfo.getUrl());
			DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uriPost.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes(charsetName)));
			request.headers().set(HttpHeaders.Names.HOST, channelInfo.getFrontIp());
			request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
			request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
			f.channel().write(request);
			f.channel().flush();
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			logger.error("���������쳣", e);
			if (e instanceof ConnectException) {
				throw new ChannelException("connect exception");
			}
			throw new ChannelException("connect exception");
		} finally {
			workerGroup.shutdownGracefully();
		}

	}

	public void connect(Map<String, Object> msg) throws ChannelException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline cPipeLine = ch.pipeline();
					ch.pipeline().addLast(idleStateHandler);
					cPipeLine.addLast(new HttpResponseDecoder());
					cPipeLine.addLast(new HttpRequestEncoder());
					cPipeLine.addLast(handel);
				}
			});

			ChannelFuture f = b.connect(channelInfo.getFrontIp(), Integer.parseInt(channelInfo.getFrontPort())).sync();
			URI uri = new URI(channelInfo.getUrl());
			DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toASCIIString());
			HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);
			HttpPostRequestEncoder bodyRequestEncoder = new HttpPostRequestEncoder(factory, request, false);
			if (msg != null) {
				for (Entry<String, Object> entry : msg.entrySet()) {
					if (entry.getValue() != null) {
						bodyRequestEncoder.addBodyAttribute(entry.getKey(), entry.getValue().toString());
					}
				}
			}

			request = (DefaultFullHttpRequest) bodyRequestEncoder.finalizeRequest();
			request.headers().set(HttpHeaders.Names.HOST, channelInfo.getFrontIp());
			request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
			request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
			f.channel().write(request);
			f.channel().flush();
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			logger.error("���������쳣", e);
			if (e instanceof ConnectException) {
				throw new ChannelException("connect exception");
			}
			throw new ChannelException("connect exception");
		} finally {
			workerGroup.shutdownGracefully();
		}
	}


	public void connectSSL(Map<String, Object> msg) throws ChannelException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				public void initChannel(SocketChannel ch) throws Exception {
					SSLEngine engine = SslContextFactory.getClientContext().createSSLEngine();
					// // ����SSLEngine����������Ϊ�ͻ��˹���ģʽ
					engine.setUseClientMode(true);
					ch.pipeline().addLast("ssl", new SslHandler(engine));
					ch.pipeline().addLast(idleStateHandler);
					ch.pipeline().addLast(new HttpResponseDecoder());
					ch.pipeline().addLast(new HttpRequestEncoder());
					ch.pipeline().addLast(handel);
				}
			});
			ChannelFuture f = b.connect(channelInfo.getFrontIp(), Integer.parseInt(channelInfo.getFrontPort())).sync();
			URI uri = new URI(channelInfo.getUrl());
			DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toASCIIString());
			HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);
			HttpPostRequestEncoder bodyRequestEncoder = new HttpPostRequestEncoder(factory, request, false);
			if (msg != null) {
				for (Entry<String, Object> entry : msg.entrySet()) {
					if (entry.getValue() != null) {
						bodyRequestEncoder.addBodyAttribute(entry.getKey(), entry.getValue().toString());
					}
				}
			}

			request = (DefaultFullHttpRequest) bodyRequestEncoder.finalizeRequest();

			request.headers().set(HttpHeaders.Names.HOST, channelInfo.getFrontIp());
			request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
			request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
			f.channel().write(request);
			f.channel().flush();
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof ConnectException) {
				throw new ChannelException("connect exception");
			}
			throw new ChannelException("connect exception");
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
	public void connectHttps(String msg) throws ChannelException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				public void initChannel(SocketChannel ch) throws Exception {
					SSLEngine engine = SslContextFactory.getClientContext().createSSLEngine();
					engine.setUseClientMode(true);
					ch.pipeline().addLast("ssl", new SslHandler(engine));
					ch.pipeline().addLast(idleStateHandler);
					ch.pipeline().addLast(new HttpResponseDecoder());
					ch.pipeline().addLast(new HttpRequestEncoder());
					ch.pipeline().addLast(handel);
				}
			});

			ChannelFuture f = b.connect(channelInfo.getFrontIp(), Integer.parseInt(channelInfo.getFrontPort())).sync();
			URI uripost = new URI(channelInfo.getUrl());
			DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uripost.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes(charsetName)));
			request.headers().set(HttpHeaders.Names.HOST, channelInfo.getFrontIp());
			request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
			request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
			f.channel().write(request);
			f.channel().flush();
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof ConnectException) {
				throw new ChannelException("connect exception");
			}
			throw new ChannelException("connect exception");
		} finally {
			workerGroup.shutdownGracefully();
		}

	}
	public ChannelHandler getHandel() {
		return handel;
	}

	public void setHandel(ChannelHandler handel) {
		this.handel = handel;
	}

	public ChannelInfo getChannelInfo() {
		return channelInfo;
	}

	public void setChannelInfo(ChannelInfo channelInfo) {
		this.channelInfo = channelInfo;
	}

	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public IdleStateHandler getIdleStateHandler() {
		return idleStateHandler;
	}

	public void setIdleStateHandler(IdleStateHandler idleStateHandler) {
		this.idleStateHandler = idleStateHandler;
	}

}
