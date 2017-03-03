package com.github.garymr.android.logger;

public class Configuration {

    private static final String DEFAULT_TAG = "LOGGER";

    private String tag = DEFAULT_TAG;
    private Level level = Level.ALL;
    private LoggerPrinter loggerPrinter = new LoggerPrinter();
    private Interceptor interceptor;

    private Configuration() {
    }

    public String getTag() {
        return tag;
    }

    public Level getLevel() {
        return level;
    }

    public LoggerPrinter getLoggerPrinter() {
        return loggerPrinter;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public static final class Builder {
        private Configuration config;

        public Builder() {
            config = new Configuration();
        }

        public Configuration create() {
            return config;
        }

        public Builder setTag(String tag) {
            if (tag == null || tag.trim().length() == 0) {
                return this;
            }

            config.tag = tag;
            return this;
        }

        public Builder setLevel(Level level) {
            if (level == null) {
                return this;
            }

            config.level = level;
            return this;
        }

        public Builder setLoggerPrinter(LoggerPrinter loggerPrinter) {
            if (loggerPrinter == null) {
                return this;
            }

            config.loggerPrinter = loggerPrinter;
            return this;
        }

        public Builder setInterceptor(Interceptor interceptor) {
            config.interceptor = interceptor;
            return this;
        }
    }
}
