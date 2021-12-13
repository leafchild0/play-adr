package com.leafchild0.adr.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author vmalyshev
 * @date 13.12.2021
 */

@Entity
@Table(name = "records")
@Data
@Audited
@EqualsAndHashCode
@ToString
public class AdrRecord {

    public AdrRecord() {
    }

    public AdrRecord(Long id, String name, RecordStatus status, String context, String decision, String consequences, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.context = context;
        this.decision = decision;
        this.consequences = consequences;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "name", length = 100, nullable = false)
    public String name;

    @Column(name = "status", length = 40, nullable = false)
    @Enumerated(EnumType.STRING)
    public RecordStatus status;

    @Column(name = "context")
    public String context;

    @Column(name = "decision")
    public String decision;

    @Column(name = "consequences")
    public String consequences;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public AdrRecordDTO toDto() {
        return new AdrRecordDTO(id, name, status.name(), context, decision, consequences, createdAt);
    }

}
