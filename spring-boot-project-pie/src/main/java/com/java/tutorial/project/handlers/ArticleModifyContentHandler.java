package com.java.tutorial.project.handlers;

import com.feiniaojin.ddd.ecosystem.pie.ChannelHandler;
import com.feiniaojin.ddd.ecosystem.pie.ChannelHandlerContext;
import com.java.tutorial.project.domain.ArticleTitleModifyDto;
import com.java.tutorial.project.domain.HandlerResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticleModifyContentHandler implements ChannelHandler {

    @Override
    public void channelProcess(ChannelHandlerContext ctx, Object in, Object out) {

        log.info("修改正文:进入修改正文的Handler");
        ArticleTitleModifyDto cmd = (ArticleTitleModifyDto)in;
        log.info("修改正文,content={}", cmd.getContent());
        log.info("修改正文:执行完成,即将进入下一个Handler");

        ctx.fireChannelProcess(in, out);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause, Object in, Object out) {

        log.error("修改标题:异常处理逻辑");
        HandlerResult re = (HandlerResult)out;
        re.setCode(1502);
        re.setMsg("修改正文发生异常");
    }
}
