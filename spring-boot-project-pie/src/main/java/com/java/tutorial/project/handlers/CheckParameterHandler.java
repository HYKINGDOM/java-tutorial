package com.java.tutorial.project.handlers;

import com.feiniaojin.ddd.ecosystem.pie.ChannelHandler;
import com.feiniaojin.ddd.ecosystem.pie.ChannelHandlerContext;
import com.java.tutorial.project.domain.ArticleTitleModifyDto;
import com.java.tutorial.project.domain.HandlerResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 *
 * @author meta
 */
@Slf4j
public class CheckParameterHandler implements ChannelHandler {

    @Override
    public void channelProcess(ChannelHandlerContext ctx, Object in, Object out) {

        log.info("参数校验:开始执行");

        if (in instanceof ArticleTitleModifyDto) {
            ArticleTitleModifyDto cmd = (ArticleTitleModifyDto)in;
            String articleId = cmd.getArticleId();
            Objects.requireNonNull(articleId, "articleId不能为空");
            String title = cmd.getTitle();
            Objects.requireNonNull(title, "title不能为空");
            String content = cmd.getContent();
            Objects.requireNonNull(content, "content不能为空");
        }
        log.info("参数校验:校验通过,即将进入下一个Handler");
        ctx.fireChannelProcess(in, out);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause, Object in, Object out) throws Exception {
        log.error("参数校验:异常处理逻辑", cause);
        HandlerResult re = (HandlerResult)out;
        re.setCode(400);
        re.setMsg("参数异常");
    }
}
