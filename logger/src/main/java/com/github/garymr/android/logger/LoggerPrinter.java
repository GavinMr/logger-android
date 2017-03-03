package com.github.garymr.android.logger;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LoggerPrinter {

    private final ThreadLocal<String> localTag = new ThreadLocal<>();
    private static final int MAX_LOG_LENGTH = 4000;
    private static final int MAX_LOG_TAG_LENGTH = 23;
    private static final String JAVA_SUFFIX = ".java";
    /**
     * The minimum stack trace index, starts at this class after two native calls.
     */
    private static final int MIN_STACK_OFFSET = 5;

    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 2;


    /** Log a verbose message with optional format args. */
    public void v(String message, Object... args) {
        if (Logger.isVerboseEnabled()) {
            log(Level.VERBOSE, message, args);
        }
    }

    /** Log a verbose exception and a message with optional format args. */
    public void v(Throwable t, String message, Object... args) {
        if (Logger.isVerboseEnabled()) {
            log(Level.VERBOSE, t, message, args);
        }
    }

    /** Log a debug message with optional format args. */
    public void d(String message, Object... args) {
        if (Logger.isDebugEnabled()) {
            log(Level.DEBUG, message, args);
        }
    }

    /** Log a debug exception and a message with optional format args. */
    public void d(Throwable t, String message, Object... args) {
        if (Logger.isDebugEnabled()) {
            log(Level.DEBUG, t, message, args);
        }
    }

    /** Log an info message with optional format args. */
    public void i(String message, Object... args) {
        if (Logger.isInfoEnabled()) {
            log(Level.INFO, message, args);
        }
    }

    /** Log an info exception and a message with optional format args. */
    public void i(Throwable t, String message, Object... args) {
        if (Logger.isInfoEnabled()) {
            log(Level.INFO, t, message, args);
        }
    }

    /** Log a warning message with optional format args. */
    public void w(String message, Object... args) {
        if (Logger.isWarnEnabled()) {
            log(Level.WARN, message, args);
        }
    }

    /** Log a warning exception and a message with optional format args. */
    public void w(Throwable t, String message, Object... args) {
        if (Logger.isWarnEnabled()) {
            log(Level.WARN, t, message, args);
        }
    }

    /** Log an error message with optional format args. */
    public void e(String message, Object... args) {
        if (Logger.isErrorEnabled()) {
            log(Level.ERROR, message, args);
        }
    }

    /** Log an error exception and a message with optional format args. */
    public void e(Throwable t, String message, Object... args) {
        if (Logger.isErrorEnabled()) {
            log(Level.ERROR, t, message, args);
        }
    }

    /** Log an assert message with optional format args. */
    public void wtf(String message, Object... args) {
        if (Logger.isAssertEnabled()) {
            log(Level.ASSERT, message, args);
        }
    }

    /** Log an assert exception and a message with optional format args. */
    public void wtf(Throwable t, String message, Object... args) {
        if (Logger.isAssertEnabled()) {
            log(Level.ASSERT, t, message, args);
        }
    }

    public void json(Level level, String json, String message, Object... args) {
        if (!Logger.isEnabled(level)) {
            return;
        }

        String logMessage;
        if (TextUtils.isEmpty(message)) {
            logMessage = "";
        } else {
            if (args.length > 0) {
                logMessage = String.format(message, args);
            } else {
                logMessage = message;
            }
        }

        if (TextUtils.isEmpty(json)) {
            log(level, logMessage + lineSeparator() + "Empty/Null json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                log(level, logMessage + lineSeparator() + jsonObject.toString(JSON_INDENT));
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                log(level, logMessage + lineSeparator() + jsonArray.toString(JSON_INDENT));
            }
        } catch (JSONException e) {
            e(e.getCause().getMessage() + lineSeparator() + json);
        }
    }

    /** Log at {@code priority} a message with optional format args. */
    public void log(Level level, String message, Object... args) {
        if (!Logger.isEnabled(level)) {
            return;
        }

        logInner(level, prepareMessage(null, message, args), null);
    }

    /** Log at {@code priority} an exception and a message with optional format args. */
    public void log(Level level, Throwable t, String message, Object... args) {
        if (!Logger.isEnabled(level)) {
            return;
        }

        logInner(level, prepareMessage(t, message, args), t);
    }

    protected String prepareMessage(Throwable t, String message, Object... args) {
        if (message != null && message.length() == 0) {
            message = null;
        }
        if (message == null) {
            if (t == null) {
                return null; // Swallow message if it's null and there's no throwable.
            }
            message = getStackTraceString(t);
        } else {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            if (t != null) {
                message += lineSeparator() + getStackTraceString(t);
            }
        }
        return message;
    }

    protected String lineSeparator() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return "\n";
        }
        return System.lineSeparator();
    }

    protected String prepareJavaInfo() {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        int position = MIN_STACK_OFFSET;
        for (; position < stackTrace.length; position++) {
            StackTraceElement e = stackTrace[position];
            String name = e.getClassName();
            if (!name.equals(LoggerPrinter.class.getName()) && !name.equals(Logger.class.getName())) {
                break;
            }
        }

        if (position < stackTrace.length) {
            StackTraceElement targetElement = stackTrace[position];
            String className = targetElement.getClassName();
            String fileName = targetElement.getFileName();
            String[] classNameInfo = className.split("\\.");
            if (classNameInfo.length > 0) {
                className = classNameInfo[classNameInfo.length - 1];
            }
            String methodName = targetElement.getMethodName();
            int lineNumber = targetElement.getLineNumber();
            if (lineNumber < 0) {
                lineNumber = 0;
            }

            String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

            return className + "." + methodNameShort + " (" + fileName + ":" + lineNumber + ") ";
        }

        return "";
    }

    protected String getStackTraceString(Throwable t) {
        // Don't replace this with Log.getStackTraceString() - it hides
        // UnknownHostException, which is not what we want.
        StringWriter sw = new StringWriter(256);
        PrintWriter pw = new PrintWriter(sw, false);
        t.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    public LoggerPrinter tag(String tag) {
        if (tag != null) {
            localTag.set(makeLogTag(tag));
        }
        return this;
    }

    private String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH) {
            return str.substring(0, MAX_LOG_TAG_LENGTH - 1);
        }

        return str;
    }

    /**
     * @return the appropriate tag based on local or global
     */
    private String getTag() {
        String tag = localTag.get();
        if (tag != null) {
            localTag.remove();
            return tag;
        }
        return Logger.getConfig().getTag();
    }

    public void logInner(Level level, String message, Throwable t) {
        String metaData = prepareJavaInfo();

        String logMessage = null;
        if (Logger.getConfig().getInterceptor() != null) {
            logMessage = Logger.getConfig().getInterceptor().intercept(level, message, t, metaData);
        }

        if (logMessage == null || logMessage.isEmpty()) {
            logMessage = metaData + message;
        }

        if (logMessage.length() < MAX_LOG_LENGTH) {
            print(level, getTag(), logMessage);
            return;
        }

        // Split by line, then ensure each line can fit into Log's maximum length.
        for (int i = 0, length = logMessage.length(); i < length; i++) {
            int newline = logMessage.indexOf('\n', i);
            newline = newline != -1 ? newline : length;
            do {
                int end = Math.min(newline, i + MAX_LOG_LENGTH);
                String part = logMessage.substring(i, end);
                print(level, getTag(), part);
                i = end;
            } while (i < newline);
        }
    }

    private void print(Level level, String tag, String message) {
        if (level == Level.VERBOSE) {
            Log.v(tag, message);
        } else if (level == Level.DEBUG) {
            Log.d(tag, message);
        } else if (level == Level.INFO) {
            Log.i(tag, message);
        } else if (level == Level.WARN) {
            Log.w(tag, message);
        } else if (level == Level.ERROR) {
            Log.e(tag, message);
        } else if (level == Level.ASSERT) {
            Log.wtf(tag, message);
        }
    }
}
