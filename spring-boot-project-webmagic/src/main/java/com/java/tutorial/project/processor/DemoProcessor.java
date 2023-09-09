package com.java.tutorial.project.processor;

import lombok.val;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author bingbing
 */
public class DemoProcessor implements PageProcessor {

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().smartContent();
        String string = selectable.get();



    }

    @Override
    public Site getSite() {
        return site;
    }
}
