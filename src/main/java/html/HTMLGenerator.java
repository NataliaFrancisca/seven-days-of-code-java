package html;

import content.Content;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public class HTMLGenerator {
    private final PrintWriter writer;
    private final String htmlTitle;

    public HTMLGenerator(PrintWriter writer, String htmlTitle){
        this.writer = writer;
        this.htmlTitle = htmlTitle;
    }

    private void setOpenHTML(){
        String html =
                """
                <html>
                <head>
                    <title>%s</title>
                    <meta charset="utf-8">
                </head>
                <body style="display:flex;min-height: 100vh; height: auto; background: #BCCEF8; justify-content: center; align-items: center; flex-direction: column; font-family: Gill Sans, sans-serif">
                <h1 style="font-size: 34px; font-weight: 800; text-transform: upper-case"; text-align: start;>%s</h1>
                <main style="display:grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 14px; width: 80%%; margin: 20px 0">
                """;
        writer.println(String.format(html, this.htmlTitle, this.htmlTitle));
    }

    private String getDateFormatted(String date){
        String dateClean = date.replaceAll("-", "").trim();
        String year = dateClean.substring(0,4);
        String month = dateClean.substring(4,6);
        String day = dateClean.substring(6,8);
        return day.concat("/").concat(month).concat("/").concat(year);
    }

    private String getRatingFormatted(Double rating){
        return String.format("%.2f", Objects.requireNonNullElse(rating, 0.0)).replace(",", ".");
    }

    private void setMovieCard(Content content){
        String card = """
                <div style="background: #FAF7F0; display: flex; flex-direction: column; padding: 40px 20px; gap: 8px; border-radius: 4px; overflow: hidden;  text-overflow: ellipsis; white-space: nowrap;">                                                                                                   
                    <img src="%s" style="height: 400px;object-fit: cover; border-radius: 4px">
                     <h2 style="font-weight: 800; text-wrap: wrap">%s</h2>
                    <div style="display: flex; flex-direction: column; margin-top: auto; font-size: 16px">
                        <span style="font-weight: 700; display: flex; align-items: center; gap: 10px">Nota:
                            <p style="font-weight: 500; margin: 0"> %s</p>
                        </span>
                        
                        <span style="font-weight: 700; display: flex; align-items: center; gap: 10px">Lançamento:
                            <p style="font-weight: 500; margin: 0"> %s</p>
                        </span>
                   </div>
                </div>
                """;

        writer.println(String.format(card, content.getPosterImage(), content.getTitle(), getRatingFormatted(content.getRating()), getDateFormatted(content.getReleaseDate())));
    }

    private void setCloseHTML(){
        String html = """
                </main>
                </body>
                </html>
                """;
        writer.println(html);
    }

    public void generate(List<Content> contents){
        setOpenHTML();

        for(Content movie: contents){
            setMovieCard(movie);
        }

        setCloseHTML();
    }
}
