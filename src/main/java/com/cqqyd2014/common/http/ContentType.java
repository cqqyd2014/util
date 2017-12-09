package com.cqqyd2014.common.http;

public enum ContentType {
	stream("application/octet-stream"),tiff("image/tiff"),jpeg("image/jpeg"),png("image/png")
	,msword("application/msword"),excel("application/vnd.ms-excel");
private final String name;
    
    private ContentType(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
