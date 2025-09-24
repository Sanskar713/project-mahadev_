package com.music.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Playlist holds songs and provides display/play methods.
 */
 class Playlist {
    private final String name;
    private final ArrayList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSong(Song s) {
        songs.add(s);
        System.out.println("Added \"" + s.title() + "\" to playlist \"" + name + "\".");
    }

    public boolean removeSongById(int id) {
        Iterator<Song> it = songs.iterator();
        while (it.hasNext()) {
            if (it.next().id() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void displaySongs() {
        if (songs.isEmpty()) {
            System.out.println("Playlist \"" + name + "\" is empty.");
            return;
        }
        System.out.println("Songs in playlist \"" + name + "\":");
        for (Song s : songs) {
            System.out.println("  " + s);
        }
    }

    public void playAll() {
        if (songs.isEmpty()) {
            System.out.println("Playlist \"" + name + "\" is empty. Nothing to play.");
            return;
        }
        System.out.println("Playing playlist \"" + name + "\" in order:");
        for (Song s : songs) {
            System.out.println("  Playing: " + s.title() + " — " + s.artist());
            // (If you want, you can Thread.sleep(...) to simulate time)
        }
    }

    public void playShuffle() {
        if (songs.isEmpty()) {
            System.out.println("Playlist \"" + name + "\" is empty. Nothing to play.");
            return;
        }
        System.out.println("Playing playlist \"" + name + "\" in shuffle mode:");
        List<Song> copy = new ArrayList<>(songs);
        Collections.shuffle(copy);
        for (Song s : copy) {
            System.out.println("  Playing: " + s.title() + " — " + s.artist());
        }
    }
}

