package com.github.garymr.android.logger;

public interface Interceptor {

    String intercept(Level level, String message, Throwable t, String metaData);
}
