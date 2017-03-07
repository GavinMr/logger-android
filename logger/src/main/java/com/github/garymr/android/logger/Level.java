package com.github.garymr.android.logger;

public enum Level {

    /**
     * No events will be logged.
     */
    OFF(0),

    ASSERT(100),

    ERROR(200),

    WARN(300),

    INFO(400),

    DEBUG(500),

    VERBOSE(600),

    /**
     * All events should be logged.
     */
    ALL(Integer.MAX_VALUE);

    private final int value;

    Level(final int val) {
        this.value = val;
    }

    public static Level valueOf(int value) {
        switch (value) {
            case 0:
                return OFF;
            case 100:
                return ASSERT;
            case 200:
                return ERROR;
            case 300:
                return WARN;
            case 400:
                return INFO;
            case 500:
                return DEBUG;
            case 600:
                return VERBOSE;
            default:
                return ALL;
        }
    }

    /**
     * Returns the integer value of the Level.
     *
     * @return the integer value of the Level.
     */
    public int getValue() {
        return value;
    }
}
