package com.github.garymr.android.logger;

/**
 * Created by gang on 2017/3/7.
 */

public interface LoggerPrinter {

    /**
     * Log a verbose message with optional format args.
     * @param message
     * @param args
     */
    void v(String message, Object... args);

    /**
     * Log a verbose exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    void v(Throwable t, String message, Object... args);

    /**
     * Log a verbose message with object
     * @param object
     */
    void v(Object object);

    /**
     * Log a debug message with optional format args.
     * @param message
     * @param args
     */
    void d(String message, Object... args);

    /**
     * Log a debug exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    void d(Throwable t, String message, Object... args);

    /**
     * Log a debug message with object
     * @param object
     */
    void d(Object object);

    /**
     * Log an info message with optional format args.
     * @param message
     * @param args
     */
    void i(String message, Object... args);

    /**
     * Log an info exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    void i(Throwable t, String message, Object... args);

    /**
     * Log a info message with object
     * @param object
     */
    void i(Object object);

    /**
     * Log a warning message with optional format args.
     * @param message
     * @param args
     */
    void w(String message, Object... args);

    /**
     * Log a warning exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    void w(Throwable t, String message, Object... args);

    /**
     * Log a warning message with object
     * @param object
     */
    void w(Object object);

    /**
     * Log an error message with optional format args.
     * @param message
     * @param args
     */
    void e(String message, Object... args);

    /**
     * Log an error exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    void e(Throwable t, String message, Object... args);

    /**
     * Log a error message with object
     * @param object
     */
    void e(Object object);

    /**
     * Log an assert message with optional format args.
     * @param message
     * @param args
     */
    void wtf(String message, Object... args);

    /**
     * Log an assert exception and a message with optional format args.
     * @param t
     * @param message
     * @param args
     */
    void wtf(Throwable t, String message, Object... args);

    /**
     * Log a assert message with object
     * @param object
     */
    void wtf(Object object);

    /**
     *
     * @param level
     * @param json
     * @param message
     * @param args
     */
    void json(Level level, String json, String message, Object... args);

    /**
     * Log at {@code level} a message with optional format args.
     * @param level
     * @param message
     * @param args
     */
    void log(Level level, String message, Object... args);

    /**
     * Log at {@code level} an exception and a message with optional format args.
     * @param level
     * @param t
     * @param message
     * @param args
     */
    void log(Level level, Throwable t, String message, Object... args);

    /**
     *
     * @param tag
     * @return
     */
    LoggerPrinter tag(String tag);
}
