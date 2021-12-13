package com.leafchild0.adr.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Record details POJO for frontend.
 * @author vmalyshev
 */
@Data
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

    public AdrRecord toRecord() {
        return new AdrRecord(id, name, RecordStatus.valueOf(status), context, decision, consequences, new Date(), new Date());
    }
}
