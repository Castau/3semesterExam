package dto;

/**
 *
 * @author Camilla
 */
public class MetaCriticDTO {
    String source;
    int metacritic;

    public MetaCriticDTO() {
    }

    public MetaCriticDTO(String Msource, int metacritic) {
        this.source = Msource;
        this.metacritic = metacritic;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(int metacritic) {
        this.metacritic = metacritic;
    }

    @Override
    public String toString() {
        return "MetaCriticDTO{" + "Msource=" + source + ", metacritic=" + metacritic + '}';
    }
    
    
}
