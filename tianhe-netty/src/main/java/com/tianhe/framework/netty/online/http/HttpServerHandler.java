package com.tianhe.framework.netty.online.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.HttpData;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianhe on 2019/12/13.
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<Object> {

    private Map<String,Handler> handlerMappings = new HashMap<>();

    public HttpServerHandler(Map<String,Handler> handlerMappings){
        this.handlerMappings = handlerMappings;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //        请求参数
        Map<String, Object> params = new HashMap<>();
//        请求内容
        byte[] body;
        FullHttpRequest request = (FullHttpRequest) msg;
//        解析get请求参数
        parseGetParams(params, request);
//        解析post请求参数
        parsePostParams(params, request);
//      解析请求体
        body = parseBody(request);
        Request requestData = new Request();
        requestData.setParams(params);
        requestData.setBody(body);
//        handler路由
        String handlerName = parseHandler(request);
        String result = null;
        if(handlerName.endsWith("Action")){
            result = handlerMappings.get(handlerName).handler(requestData);
        }

        try {
            HttpResponseStatus status = HttpResponseStatus.OK;
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(result.getBytes()));
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=utf-8");
            httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
            httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            ctx.write(httpResponse);
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            writeErrorResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR.code(), "服务异常", ctx);
        }
    }

    private String parseHandler(FullHttpRequest request) {
        String uri = request.getUri();
        String handlerName = "";
        if(uri.indexOf("?") == -1){
            handlerName = uri.substring(uri.lastIndexOf("/")+1,uri.length());
        }else{
            handlerName = uri.substring(uri.lastIndexOf("/")+1,uri.indexOf("?"));
        }
        return handlerName;
    }

    private byte[] parseBody(FullHttpRequest request) {
        byte[] body = new byte[]{};
        if (request.content().readableBytes() <= 0) {
            body = null;
        } else {
            byte[] bytes = new byte[request.content().readableBytes()];
            request.content().getBytes(0, bytes);
            body = bytes;
        }
        return body;
    }

    private void parsePostParams(Map<String, Object> params, FullHttpRequest request) throws IOException {
        if (request.method().equals(HttpMethod.POST)) {
            HttpPostRequestDecoder postRequestDecoder = new HttpPostRequestDecoder(request);
            List<InterfaceHttpData> bodyHttpDatas = postRequestDecoder.getBodyHttpDatas();
            for (InterfaceHttpData data : bodyHttpDatas) {
                if (data instanceof HttpData) {
                    HttpData httpData = (HttpData) data;
                    String name = httpData.getName();
                    String value = httpData.getString();
                    params.put(name, value);
                }
            }
            postRequestDecoder.destroy();
        }
    }

    private void parseGetParams(Map<String, Object> params, FullHttpRequest request) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        for (Map.Entry<String, List<String>> param : parameters.entrySet()) {
            params.put(param.getKey(), param.getValue().get(0));
        }
    }

    private void writeErrorResponse(int statusCode, String message, ChannelHandlerContext ctx) {
        DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.valueOf(statusCode), Unpooled.copiedBuffer(message, Charset.forName("utf-8")));
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=utf-8");
        ctx.write(httpResponse);
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
}
