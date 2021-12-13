package com.leafchild0.adr.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Record details POJO for frontend.
 * @author vmalyshev
 */
public class AdrRecordDTO implements Serializable {

    private static final long serialVersionUID = -3762372930220248494L;

    public AdrRecordDTO() {
    }

    public AdrRecordDTO(Long id, String name, String status, String context, String decision, String consequences, Date date) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.context = context;
        this.decision = decision;
        this.consequences = consequences;
        this.date = date;
    }

    private Long id;
    private String name;
    private String status;
    private String context;
    private String decision;
    private String consequences;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone="GMT")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getConsequences() {
        return consequences;
    }

    public void setConsequences(String consequences) {
        this.consequences = consequences;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AdrRecord toRecord() {
        return new AdrRecord(id, name, RecordStatus.valueOf(status), context, decision, consequences, new Date(), new Date());
    }

    @Override
    public String toString() {
        return "AdrRecordDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", context='" + context + '\'' +
                ", decision='" + decision + '\'' +
                ", consequences='" + consequences + '\'' +
                ", date=" + date +
                '}';
    }
}
