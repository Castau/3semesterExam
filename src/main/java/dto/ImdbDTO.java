package dto;

/**
 *
 * @author Camilla
 */
public class ImdbDTO {
    String Source;
    double Rating;
    int Votes;

    public ImdbDTO() {
    }

    public ImdbDTO(String Source, double Rating, int Votes) {
        this.Source = Source;
        this.Rating = Rating;
        this.Votes = Votes;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double Rating) {
        this.Rating = Rating;
    }

    public int getVotes() {
        return Votes;
    }

    public void setVotes(int Votes) {
        this.Votes = Votes;
    }

    @Override
    public String toString() {
        return "ImdbDTO{" + "Source=" + Source + ", Rating=" + Rating + ", Votes=" + Votes + '}';
    }
    
    
}
