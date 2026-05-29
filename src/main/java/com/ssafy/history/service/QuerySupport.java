package com.ssafy.history.service;

final class QuerySupport {
    private static final int DEFAULT_LIMIT = 20;
    private static final int MAX_LIMIT = 200;

    private QuerySupport() {
    }

    static int normalizeLimit(Integer limit) {
        if (limit == null) {
            return DEFAULT_LIMIT;
        }
        return Math.min(Math.max(limit, 1), MAX_LIMIT);
    }

    static int normalizeOffset(Integer offset) {
        if (offset == null) {
            return 0;
        }
        return Math.max(offset, 0);
    }
}
