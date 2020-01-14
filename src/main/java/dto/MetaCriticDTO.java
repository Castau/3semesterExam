package dto;

import java.util.Objects;

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
        return "MetaCriticDTO{" + "source=" + source + ", metacritic=" + metacritic + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.source);
        hash = 47 * hash + this.metacritic;
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
        final MetaCriticDTO other = (MetaCriticDTO) obj;
        if (this.metacritic != other.metacritic) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        return true;
    }
    
    
}
