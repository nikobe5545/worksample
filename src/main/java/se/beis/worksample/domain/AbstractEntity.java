package se.beis.worksample.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public abstract class AbstractEntity {
    @Id
    @Column(nullable = false)
    private Long id;
    @Version
    private Long version;
    private OffsetDateTime created;
    private OffsetDateTime updated;

    @PrePersist
    protected void onCreate() {
        created = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }
}
