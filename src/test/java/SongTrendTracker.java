import java.util.*;

class SongTrendTracker {
    private final Map<String, SongStats> songPlayCounts = new HashMap<>();
    private final PriorityQueue<SongStats> topSongs = new PriorityQueue<>((o1, o2) -> {
        int compare = o1.playCount.compareTo(o2.playCount);
        if (compare == 0) {
            return o1.playOrder.compareTo(o2.playOrder);
        } else {
            return compare;
        }
    });
    private final int capacity = 10; // Top 10 songs
    private int playOrder;

    // Records a song play and updates the top list
    public void recordSongPlay(String songName) {
        int playCount = 1;
        SongStats songStats;
        if (songPlayCounts.containsKey(songName)) {
            songStats = songPlayCounts.get(songName);
            songStats.playCount += 1;
        } else {
            songStats = new SongStats(songName, playCount, playOrder++);
        }

        songPlayCounts.put(songName, songStats);

        updateTopSongs(songStats);
    }

    private void updateTopSongs(SongStats songStats) {
        topSongs.remove(songStats); // Update existing entry
        topSongs.add(songStats);

        if (topSongs.size() > capacity) {
            topSongs.poll(); // Maintain only the top 10//poll removes
        }
    }

    public List<String> getTopSongs() {
        List<String> songs = new ArrayList<>();
        while (!topSongs.isEmpty()) {
            SongStats songStats = topSongs.poll();
            songs.add(songStats.songName + ": " + songStats.playCount);
        }
        Collections.reverse(songs); // Reverse to show the top song first
        return songs;
    }

    private static class SongStats {
        String songName;
        Integer playCount;
        Integer playOrder;

        SongStats(String songName, int playCount, int playOrder) {
            this.songName = songName;
            this.playCount = playCount;
            this.playOrder = playOrder;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            SongStats songStats = (SongStats) obj;
            return Objects.equals(songName, songStats.songName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(songName);
        }
    }

    public static void main(String[] args) {
        SongTrendTracker tracker = new SongTrendTracker();
        tracker.recordSongPlay("Song A");
        tracker.recordSongPlay("Song A");
        tracker.recordSongPlay("Song B");
        tracker.recordSongPlay("Song A");
        tracker.recordSongPlay("Song C");
        tracker.recordSongPlay("Song D");
        tracker.recordSongPlay("Song E");
        tracker.recordSongPlay("Song F");
        tracker.recordSongPlay("Song G");
        tracker.recordSongPlay("Song B");
        tracker.recordSongPlay("Song H");
        tracker.recordSongPlay("Song I");
        tracker.recordSongPlay("Song J");
        tracker.recordSongPlay("Song K");
        tracker.recordSongPlay("Song K");
        tracker.recordSongPlay("Song L");
        tracker.recordSongPlay("Song M");
        tracker.recordSongPlay("Song N");
        tracker.recordSongPlay("Song M");
        tracker.recordSongPlay("Song A");
        tracker.recordSongPlay("Song M");

        System.out.println("Top songs: " + tracker.getTopSongs());
    }
}

