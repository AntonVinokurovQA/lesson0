package pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewUserResponse {

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("job")
    private String job;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getJob() {
        return job;
    }
}