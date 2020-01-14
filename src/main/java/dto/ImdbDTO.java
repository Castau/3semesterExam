package dto;

import java.util.Objects;

/**
 *
 * @author Camilla
 */
public class ImdbDTO {
    String source;
    double rating;
    int votes;

    public ImdbDTO() {
    }

    public ImdbDTO(String Source, double Rating, int Votes) {
        this.source = Source;
        this.rating = Rating;
        this.votes = Votes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "ImdbDTO{" + "Source=" + source + ", Rating=" + rating + ", Votes=" + votes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.source);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.rating) ^ (Double.doubleToLongBits(this.rating) >>> 32));
        hash = 73 * hash + this.votes;
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
        final ImdbDTO other = (ImdbDTO) obj;
        if (Double.doubleToLongBits(this.rating) != Double.doubleToLongBits(other.rating)) {
            return false;
        }
        if (this.votes != other.votes) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        return true;
    }
    
    
}
