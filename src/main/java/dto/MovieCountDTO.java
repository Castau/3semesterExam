package dto;

/**
 *
 * @author Camilla
 */
public class MovieCountDTO {
    String title;
    int count;

    public MovieCountDTO() {
    }

    public MovieCountDTO(String title, int count) {
        this.title = title;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MovieCountDTO{" + "title=" + title + ", count=" + count + '}';
    }
    
    
}
