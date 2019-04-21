package com.github.sigalhu.generator.id;

import com.github.sigalhu.utils.Try;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author huxujun
 * @date 2019-04-22
 */
public abstract class BaseIdGenerator implements IdGenerator {

    /**
     * 时间戳从2019-01-01 08:00:00开始
     */
    protected static final long TIMESTAMP_START = Try.of(() ->
            DateUtils.parseDate("2019-01-01 08:00:00", "yyyy-MM-dd HH:mm:ss").getTime()).getAsLong();
}
