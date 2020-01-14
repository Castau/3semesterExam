
package dto;

import java.util.Objects;

/**
 *
 * @author Camilla
 */
public class MovieAllDTO {
    String title, plot, directors, genres , cast;
    int year;
    String poster;
    ImdbDTO imdb;
    RottenTomatoesDTO tomatoes;
    MetaCriticDTO metaCritic;

    public MovieAllDTO() {
    }

    public MovieAllDTO(String title, String plot, String directors, String genres, String cast, int year, String poster, ImdbDTO imdb, RottenTomatoesDTO tomatoes, MetaCriticDTO metaCritic) {
        this.title = title;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.year = year;
        this.poster = poster;
        this.imdb = imdb;
        this.tomatoes = tomatoes;
        this.metaCritic = metaCritic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public ImdbDTO getImdb() {
        return imdb;
    }

    public void setImdb(ImdbDTO imdb) {
        this.imdb = imdb;
    }

    public RottenTomatoesDTO getTomatoes() {
        return tomatoes;
    }

    public void setTomatoes(RottenTomatoesDTO tomatoes) {
        this.tomatoes = tomatoes;
    }

    public MetaCriticDTO getMetaCritic() {
        return metaCritic;
    }

    public void setMetaCritic(MetaCriticDTO metaCritic) {
        this.metaCritic = metaCritic;
    }

    @Override
    public String toString() {
        return "MovieAllDTO{" + "title=" + title + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", year=" + year + ", poster=" + poster + ", imdb=" + imdb + ", tomatoes=" + tomatoes + ", metaCritic=" + metaCritic + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.plot);
        hash = 71 * hash + Objects.hashCode(this.directors);
        hash = 71 * hash + Objects.hashCode(this.genres);
        hash = 71 * hash + Objects.hashCode(this.cast);
        hash = 71 * hash + this.year;
        hash = 71 * hash + Objects.hashCode(this.poster);
        hash = 71 * hash + Objects.hashCode(this.imdb);
        hash = 71 * hash + Objects.hashCode(this.tomatoes);
        hash = 71 * hash + Objects.hashCode(this.metaCritic);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovieAllDTO other = (MovieAllDTO) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.plot, other.plot)) {
            return false;
        }
        if (!Objects.equals(this.directors, other.directors)) {
            return false;
        }
        if (!Objects.equals(this.genres, other.genres)) {
            return false;
        }
        if (!Objects.equals(this.cast, other.cast)) {
            return false;
        }
        if (!Objects.equals(this.poster, other.poster)) {
            return false;
        }
        if (!Objects.equals(this.imdb, other.imdb)) {
            return false;
        }
        if (!Objects.equals(this.tomatoes, other.tomatoes)) {
            return false;
        }
        if (!Objects.equals(this.metaCritic, other.metaCritic)) {
            return false;
        }
        return true;
    }
    
}
