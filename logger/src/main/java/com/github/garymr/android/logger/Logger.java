package com.github.garymr.android.logger;

public final class Logger {

    private static Configuration configuration;

    private Logger() {}

    public static Configuration.Builder newConfigurationBuilder() {
        return new Configuration.Builder();
    }

    public synchronized static void initialize(final Configuration config) {
        Logger.configuration = config;
    }

    public static Configuration getConfig() {
        if (configuration == null) {
            synchronized (Logger.class) {
                if (configuration == null) {
                    configuration = new Configuration.Builder().create();
                }
            }
        }
        return configuration;
    }

    /**
     * Log a verbose message with optional format args.
     * @param message
     * @param args
     */
    public static void v(String message, Object... args) {
        getConfig().getLoggerPrinter().v(message, args);
    }

    /**
     * Log a verbose exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    public static void v(Throwable t, String message, Object... args) {
        getConfig().getLoggerPrinter().v(t, message, args);
    }

    /**
     * Log a verbose message.
     * @param object
     */
    public static void v(Object object) {
        getConfig().getLoggerPrinter().v(object);
    }

    /**
     * Log a debug message with optional format args.
     * @param message
     * @param args
     */
    public static void d(String message, Object... args) {
        getConfig().getLoggerPrinter().d(message, args);
    }

    /**
     * Log a debug exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    public static void d(Throwable t, String message, Object... args) {
        getConfig().getLoggerPrinter().d(t, message, args);
    }

    /**
     * Log a debug message.
     * @param object
     */
    public static void d(Object object) {
        getConfig().getLoggerPrinter().d(object);
    }

    /**
     * Log an info message with optional format args.
     * @param message
     * @param args
     */
    public static void i(String message, Object... args) {
        getConfig().getLoggerPrinter().i(message, args);
    }

    /**
     * Log an info exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    public static void i(Throwable t, String message, Object... args) {
        getConfig().getLoggerPrinter().i(t, message, args);
    }

    /**
     * Log a info message.
     * @param object
     */
    public static void i(Object object) {
        getConfig().getLoggerPrinter().i(object);
    }

    /**
     * Log a warning message with optional format args.
     * @param message
     * @param args
     */
    public static void w(String message, Object... args) {
        getConfig().getLoggerPrinter().w(message, args);
    }

    /**
     * Log a warning exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    public static void w(Throwable t, String message, Object... args) {
        getConfig().getLoggerPrinter().w(t, message, args);
    }

    /**
     * Log a warning message.
     * @param object
     */
    public static void w(Object object) {
        getConfig().getLoggerPrinter().w(object);
    }

    /**
     * Log an error message with optional format args.
     * @param message
     * @param args
     */
    public static void e(String message, Object... args) {
        getConfig().getLoggerPrinter().e(message, args);
    }

    /**
     * Log an error exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    public static void e(Throwable t, String message, Object... args) {
        getConfig().getLoggerPrinter().e(t, message, args);
    }

    /**
     * Log a error message.
     * @param object
     */
    public static void e(Object object) {
        getConfig().getLoggerPrinter().e(object);
    }

    /**
     * Log an assert message with optional format args.
     * @param message
     * @param args
     */
    public static void wtf(String message, Object... args) {
        getConfig().getLoggerPrinter().wtf(message, args);
    }

    /**
     * Log an assert exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    public static void wtf(Throwable t, String message, Object... args) {
        getConfig().getLoggerPrinter().wtf(t, message, args);
    }

    /**
     * Log an assert message.
     * @param object
     */
    public static void wtf(Object object) {
        getConfig().getLoggerPrinter().wtf(object);
    }

    public static void json(Level level, String json) {
        json(level, json, null);
    }

    public static void json(Level level, String json, String message, Object... args) {
        if (level == null || level == Level.ALL || level == Level.OFF) {
            w("Requires effective level of log");
            return;
        }
        getConfig().getLoggerPrinter().json(level, json, message, args);
    }

    public static boolean isVerboseEnabled() {
        return isEnabled(Level.VERBOSE);
    }

    public static boolean isDebugEnabled() {
        return isEnabled(Level.DEBUG);
    }

    public static boolean isInfoEnabled() {
        return isEnabled(Level.INFO);
    }

    public static boolean isWarnEnabled() {
        return isEnabled(Level.WARN);
    }

    public static boolean isErrorEnabled() {
        return isEnabled(Level.ERROR);
    }

    public static boolean isAssertEnabled() {
        return isEnabled(Level.ASSERT);
    }

    public static boolean isEnabled(Level level) {
        return getConfig().getLevel().getValue() >= level.getValue();
    }

    public static LoggerPrinter tag(String tag) {
        return getConfig().getLoggerPrinter().tag(tag);
    }

}
