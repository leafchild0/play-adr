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
@Builder
public class AdrRecordDTO implements Serializable {

    private static final long serialVersionUID = -3762372930220248494L;

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
