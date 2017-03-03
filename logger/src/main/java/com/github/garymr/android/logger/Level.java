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

    private final int intLevel;

    private Level(final int val) {
        intLevel = val;
    }

    /**
     * Returns the integer value of the Level.
     *
     * @return the integer value of the Level.
     */
    public int intLevel() {
        return intLevel;
    }
}
