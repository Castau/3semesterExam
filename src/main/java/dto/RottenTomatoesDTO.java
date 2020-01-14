package dto;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.source);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.viewerRating) ^ (Double.doubleToLongBits(this.viewerRating) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.criticRating) ^ (Double.doubleToLongBits(this.criticRating) >>> 32));
        hash = 83 * hash + this.viewerNumReviews;
        hash = 83 * hash + this.viewerMeter;
        hash = 83 * hash + this.criticNumReviews;
        hash = 83 * hash + this.criticMeter;
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
        final RottenTomatoesDTO other = (RottenTomatoesDTO) obj;
        if (Double.doubleToLongBits(this.viewerRating) != Double.doubleToLongBits(other.viewerRating)) {
            return false;
        }
        if (Double.doubleToLongBits(this.criticRating) != Double.doubleToLongBits(other.criticRating)) {
            return false;
        }
        if (this.viewerNumReviews != other.viewerNumReviews) {
            return false;
        }
        if (this.viewerMeter != other.viewerMeter) {
            return false;
        }
        if (this.criticNumReviews != other.criticNumReviews) {
            return false;
        }
        if (this.criticMeter != other.criticMeter) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        return true;
    }

    
}
