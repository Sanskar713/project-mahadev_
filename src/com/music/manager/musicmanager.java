package com.music.manager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main program: menu-driven music playlist manager (console).
 */
 class MusicManager {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Song> allSongs = new ArrayList<>();
    private static final ArrayList<Playlist> playlists = new ArrayList<>();
    private static int nextSongId = 1;

    public static void main(String[] args) {
        System.out.println("Welcome to Music Playlist Manager (Java console version)");
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addSong();
                case 2 -> viewAllSongs();
                case 3 -> createPlaylist();
                case 4 -> addSongToPlaylist();
                case 5 -> removeSongFromPlaylist();
                case 6 -> viewPlaylist();
                case 7 -> playPlaylist(false);
                case 8 -> playPlaylist(true);
                case 9 -> {
                    System.out.println("Exiting. Goodbye!");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Music Playlist Manager ---");
        System.out.println("1. Add Song");
        System.out.println("2. View All Songs");
        System.out.println("3. Create Playlist");
        System.out.println("4. Add Song to Playlist");
        System.out.println("5. Remove Song from Playlist");
        System.out.println("6. View Playlist");
        System.out.println("7. Play Playlist (in order)");
        System.out.println("8. Play Playlist (shuffle)");
        System.out.println("9. Exit");
    }

    // ------- Menu actions -------
    private static void addSong() {
        String title = readLine("Enter song title: ");
        String artist = readLine("Enter artist: ");
        String album = readLine("Enter album: ");
        double duration = readDouble();
        Song song = new Song(nextSongId++, title, artist, album, duration);
        allSongs.add(song);
        System.out.println("Song added: " + song);
    }

    private static void viewAllSongs() {
        if (allSongs.isEmpty()) {
            System.out.println("No songs available. Add songs first.");
            return;
        }
        System.out.println("All songs:");
        for (Song s : allSongs) {
            System.out.println("  " + s);
        }
    }

    private static void createPlaylist() {
        String name = readLine("Enter playlist name: ");
        if (findPlaylistByName(name) != null) {
            System.out.println("A playlist with that name already exists.");
            return;
        }
        playlists.add(new Playlist(name));
        System.out.println("Playlist \"" + name + "\" created.");
    }

    private static void addSongToPlaylist() {
        Playlist p = selectPlaylist();
        if (p == null) return;
        viewAllSongs();
        if (allSongs.isEmpty()) return;
        int id = readInt("Enter song ID to add: ");
        Song s = findSongById(id);
        if (s == null) {
            System.out.println("Song with ID " + id + " not found.");
            return;
        }
        p.addSong(s);
    }

    private static void removeSongFromPlaylist() {
        Playlist p = selectPlaylist();
        if (p == null) return;
        p.displaySongs();
        int id = readInt("Enter song ID to remove from playlist: ");
        boolean removed = p.removeSongById(id);
        if (removed) System.out.println("Song removed from playlist.");
        else System.out.println("Song not found in playlist.");
    }

    private static void viewPlaylist() {
        Playlist p = selectPlaylist();
        if (p != null) p.displaySongs();
    }

    private static void playPlaylist(boolean shuffle) {
        Playlist p = selectPlaylist();
        if (p == null) return;
        if (shuffle) p.playShuffle();
        else p.playAll();
    }

    // ------- Helpers -------
    private static Playlist selectPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists found. Create one first.");
            return null;
        }
        System.out.println("Playlists:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + playlists.get(i).getName());
        }
        int idx = readInt("Enter playlist number: ");
        if (idx < 1 || idx > playlists.size()) {
            System.out.println("Invalid playlist number.");
            return null;
        }
        return playlists.get(idx - 1);
    }

    private static Song findSongById(int id) {
        for (Song s : allSongs) if (s.id() == id) return s;
        return null;
    }

    private static Playlist findPlaylistByName(String name) {
        for (Playlist p : playlists) if (p.getName().equalsIgnoreCase(name)) return p;
        return null;
    }

    // ------- Input utilities (robust simple input) -------
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                String line = readLine(prompt);
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                String line = readLine("Enter duration (minutes, e.g. 3.50): ");
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g. 3.5).");
            }
        }
    }
}
