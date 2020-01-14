package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Camilla
 */
@Entity
@Table(name = "moviecache")
@NamedQueries({
    @NamedQuery(name = "MovieCache.deleteAllRows", query = "DELETE FROM MovieCache"),
    @NamedQuery(name = "MovieCache.getByTitle", query = "SELECT mc FROM MovieCache mc WHERE mc.title= :title")})

public class MovieCache implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TITLE", length = 100)
    String title;

    @Basic(optional = false)
    @NotNull
    @Column(name = "RESPONSE", length = 5000)
    String response;

    @Basic(optional = false)
    @NotNull
    @Column(name = "COUNT")
    int count;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CACHED")
    @Temporal(TemporalType.TIMESTAMP)
    Date CachedDate;

    public MovieCache() {
    }

    public MovieCache(String title, String response, int count, Date CachedDate) {
        this.title = title;
        this.response = response;
        this.count = count;
        this.CachedDate = CachedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCachedDate() {
        return CachedDate;
    }

    public void setCachedDate(Date CachedDate) {
        this.CachedDate = CachedDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.response);
        hash = 59 * hash + this.count;
        hash = 59 * hash + Objects.hashCode(this.CachedDate);
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
        final MovieCache other = (MovieCache) obj;
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.response, other.response)) {
            return false;
        }
        if (!Objects.equals(this.CachedDate, other.CachedDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MovieCache{" + "title=" + title + ", response=" + response + ", count=" + count + ", CachedDate=" + CachedDate + '}';
    }

}
