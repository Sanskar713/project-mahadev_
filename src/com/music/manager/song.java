package com.music.manager;

/**
 * Simple Song class: stores song details and a nice toString for display.
 *
 * @param duration in minutes
 */
record Song(int id, String title, String artist, String album, double duration) {

    @Override
    public String toString() {
        return String.format("%d. %s - %s (%s) [%.2f min]", id, title, artist, album, duration);
    }
}

