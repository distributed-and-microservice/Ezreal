/**
 *
 *  Copyright 2018 chengfan
 *
 *  website: fanhub.cn
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.fanhub.ezreal.plugin.netty;

import cn.fanhub.ezreal.core.plugin.EzPlugin;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 *
 * @author chengfan
 * @version $Id: NettyInboundHandlerPlugin.java, v 0.1 2018年03月16日 下午3:04 chengfan Exp $
 */
public abstract class NettyInboundHandlerPlugin extends ChannelInboundHandlerAdapter implements EzPlugin {
    @Override
    public void init() {
        System.out.println("init netty plugin");
    }

    @Override
    public void execute(Object ...args) throws Exception {
        System.out.println("execute netty plugin");
    }

    @Override
    public void destroy() {
        System.out.println("destroy netty plugin");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("plugin received: " + in.toString(CharsetUtil.UTF_8));
        ctx.fireChannelRead(in);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}