package pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewUser {

    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;

    public NewUser(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}