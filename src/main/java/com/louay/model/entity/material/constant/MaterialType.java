package com.louay.model.entity.material.constant;

public enum MaterialType {
    FILE("FILE"), TEXT("TEXT");

    private final String materialType;

    MaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialType() {
        return materialType;
    }
}
