package html;

import movie.Movie;
import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {
    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer){
        this.writer = writer;
    }

    private String getMovieImage(String imgPath){
        return "https://image.tmdb.org/t/p/original".concat(imgPath);
    };

    private void setOpenHTML(){
        String html =
                """
                <html>
                <head>
                    <title>Filmes Populares</title>
                    <meta charset="utf-8">
                </head>
                <body style="display:flex;min-height: 100vh; height: auto; background: cyan; justify-content: center; align-items: center; font-family: Gill Sans, sans-serif">
                <main style="display:grid; grid-template-columns: repeat(3, 1fr); gap: 10px; width: 80%; margin: 20px 0">
                """;
        writer.println(html);
    }

    private void setMovieCard(Movie movie){
        String card = """
                <div style="background: white; display: flex; flex-direction: column; padding: 10px; gap: 14px; border-radius: 4px">
                    <img src="%s" style="height: 500px;object-fit: cover;">
                     <h4>%s</h4>
                    <div style="display: flex; justify-content: space-between; align-items: center">
                        <p>Nota: %s</p>
                        <small>Ano: %s</small>
                   </div>
                </div>
                """;

        String imageUrl = getMovieImage(movie.getPoster_path());
        writer.println(String.format(card, imageUrl, movie.getTitle(), movie.getVote_average(), movie.getRelease_date()));
    }

    private void setCloseHTML(){
        String html = """
                </main>
                </body>
                </html>
                """;
        writer.println(html);
    }

    public void generate(List<Movie> movies){
        setOpenHTML();

        for(Movie movie: movies){
            setMovieCard(movie);
        }

        setCloseHTML();
    }
}
