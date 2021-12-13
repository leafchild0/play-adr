package com.leafchild0.adr.data;

/**
 * Enum for Status
 * @author vmalyshev
 * @date 13.12.2021
 */
public enum RecordStatus {
    SUGGESTED("Supended"),
    APPROVED("Approved"),
    SUSPENDED("Suspended");

    private String name;

    RecordStatus(String name) {
        this.name = name;
    }

    public static RecordStatus getStatus(String status) {
        for (RecordStatus b : RecordStatus.values()) {
            if (b.name.equalsIgnoreCase(status)) {
                return b;
            }
        }
        return null;
    }
}
