package movie;

public class Movie{
    private String title;
    private String release_date;
    private Double vote_average;
    private String poster_path;

    public Movie(String title, String release_date, Double vote_average, String poster_path) {
        this.title = title;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }
}