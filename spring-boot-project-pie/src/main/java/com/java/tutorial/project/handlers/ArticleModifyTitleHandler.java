package com.java.tutorial.project.handlers;

import com.feiniaojin.ddd.ecosystem.pie.ChannelHandler;
import com.feiniaojin.ddd.ecosystem.pie.ChannelHandlerContext;
import com.java.tutorial.project.domain.ArticleTitleModifyDto;
import com.java.tutorial.project.domain.HandlerResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticleModifyTitleHandler implements ChannelHandler {

    @Override
    public void channelProcess(ChannelHandlerContext ctx, Object in, Object out) throws Exception {

        log.info("修改标题:进入修改标题的Handler");

        ArticleTitleModifyDto cmd = (ArticleTitleModifyDto)in;

        String title = cmd.getTitle();
        //修改标题的业务逻辑
        log.info("修改标题:title={}", title);

        cmd.setTitle(title+ " 修改标题 channelProcess");
        if (true) {
            throw new RuntimeException("exception info ");
        }

        log.info("修改标题:执行完成,即将进入下一个Handler");
        ctx.fireChannelProcess(in, out);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause, Object in, Object out) throws Exception {
        log.error("修改标题:异常处理逻辑");
        HandlerResult re = (HandlerResult)out;
        re.setCode(1501);
        re.setMsg("修改标题发生异常");
    }
}
