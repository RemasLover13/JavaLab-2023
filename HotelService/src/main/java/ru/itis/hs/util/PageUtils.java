package ru.itis.hs.util;

import org.springframework.beans.factory.annotation.Value;

public class PageUtils {
    public static int DEFAULT_PAGE_SIZE;

    @Value("${hotel.page.size}")
    public void setDefaultPageSize(int defaultPageSize) {
        DEFAULT_PAGE_SIZE = defaultPageSize;
    }
}
