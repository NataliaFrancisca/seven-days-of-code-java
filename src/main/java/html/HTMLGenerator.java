package html;

import movie.Movie;
import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {
    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer){
        this.writer = writer;
    }

    private void setOpenHTML(){
        String html =
                """
                <html>
                <head>
                    <title>Filmes Populares</title>
                    <meta charset="utf-8">
                </head>
                <body style="display:flex;min-height: 100vh; height: auto; background: cyan; justify-content: center; align-items: center; font-family: Gill Sans, sans-serif">
                <main style="display:grid; grid-template-columns: repeat(auto-fill, minmax(300px, auto)); gap: 10px; width: 80%; margin: 20px 0">
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

        writer.println(String.format(card, movie.getPosterImage(), movie.getTitle(), movie.getRating(), movie.getReleaseDate()));
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
