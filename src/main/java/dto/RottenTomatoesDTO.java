package dto;

/**
 *
 * @author Camilla
 */
public class RottenTomatoesDTO {
    String source;
    double viewerRating, criticRating;
    int viewerNumReviews, viewerMeter, criticNumReviews, criticMeter;

    public RottenTomatoesDTO() {
    }

    public RottenTomatoesDTO(String source, double viewerRating, double criticRating, int viewerNumReviews, int viewerMeter, int criticNumReviews, int criticMeter) {
        this.source = source;
        this.viewerRating = viewerRating;
        this.criticRating = criticRating;
        this.viewerNumReviews = viewerNumReviews;
        this.viewerMeter = viewerMeter;
        this.criticNumReviews = criticNumReviews;
        this.criticMeter = criticMeter;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getViewerRating() {
        return viewerRating;
    }

    public void setViewerRating(double viewerRating) {
        this.viewerRating = viewerRating;
    }

    public double getCriticRating() {
        return criticRating;
    }

    public void setCriticRating(double criticRating) {
        this.criticRating = criticRating;
    }

    public int getViewerNumReviews() {
        return viewerNumReviews;
    }

    public void setViewerNumReviews(int viewerNumReviews) {
        this.viewerNumReviews = viewerNumReviews;
    }

    public int getViewerMeter() {
        return viewerMeter;
    }

    public void setViewerMeter(int viewerMeter) {
        this.viewerMeter = viewerMeter;
    }

    public int getCriticNumReviews() {
        return criticNumReviews;
    }

    public void setCriticNumReviews(int criticNumReviews) {
        this.criticNumReviews = criticNumReviews;
    }

    public int getCriticMeter() {
        return criticMeter;
    }

    public void setCriticMeter(int criticMeter) {
        this.criticMeter = criticMeter;
    }

    @Override
    public String toString() {
        return "RottenTomatoesDTO{" + "source=" + source + ", viewerRating=" + viewerRating + ", criticRating=" + criticRating + ", viewerNumReviews=" + viewerNumReviews + ", viewerMeter=" + viewerMeter + ", criticNumReviews=" + criticNumReviews + ", criticMeter=" + criticMeter + '}';
    }

    
}
