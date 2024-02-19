package com.java.coco.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * 参考tomcat8中的并发DateFormat
 * <p>
 * {@link SimpleDateFormat}的线程安全包装器。 不使用ThreadLocal，创建足够的SimpleDateFormat对象来满足并发性要求。
 * </p>
 *
 * @author yihur
 */
public class ConcurrentDateFormat {
    private final static ConcurrentMap<String, ConcurrentDateFormat> CACHE = new ConcurrentHashMap<>(3);
    private final String pattern;
    private final Locale locale;
    private final TimeZone timezone;
    private final Queue<SimpleDateFormat> queue = new ConcurrentLinkedQueue<>();

    private ConcurrentDateFormat(String pattern, Locale locale, TimeZone timezone) {
        this.pattern = pattern;
        this.locale = locale;
        this.timezone = timezone;
        SimpleDateFormat initial = createInstance();
        queue.add(initial);
    }

    public static ConcurrentDateFormat of(String pattern) {
        // 直接使用 pattern 格式化的场景比较多，每次 new 性能太差
        return CACHE.computeIfAbsent(pattern,
            (key) -> new ConcurrentDateFormat(key, Locale.getDefault(), TimeZone.getDefault()));
    }

    public static ConcurrentDateFormat of(String pattern, TimeZone timezone) {
        return new ConcurrentDateFormat(pattern, Locale.getDefault(), timezone);
    }

    public static ConcurrentDateFormat of(String pattern, Locale locale, TimeZone timezone) {
        return new ConcurrentDateFormat(pattern, locale, timezone);
    }

    public String format(Date date) {
        SimpleDateFormat sdf = queue.poll();
        if (sdf == null) {
            sdf = createInstance();
        }
        String result = sdf.format(date);
        queue.add(sdf);
        return result;
    }

    public Date parse(String source) throws ParseException {
        SimpleDateFormat sdf = queue.poll();
        if (sdf == null) {
            sdf = createInstance();
        }
        Date result = sdf.parse(source);
        queue.add(sdf);
        return result;
    }

    private SimpleDateFormat createInstance() {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        sdf.setTimeZone(timezone);
        return sdf;
    }
}
