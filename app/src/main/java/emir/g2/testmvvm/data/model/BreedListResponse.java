package emir.g2.testmvvm.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BreedListResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private Map<String, String[]> message;

    @Expose(serialize = false, deserialize = false)
    private Set<String> breeds;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Breed> getMessage() {
        List<Breed> breeds=new ArrayList<>();
        for (Map.Entry<String, String[]> entry : message.entrySet()) {
           Breed breed=new Breed();
           breed.setType(entry.getKey());
           breed.setSubType(entry.getValue());
           breeds.add(breed);
        }

        return breeds;
    }

    public void setMessage(Map<String, String[]> message) {
        this.message = message;
    }

    public Set<String> getBreeds() {
        return breeds;
    }

    public void setBreeds(Set<String> breeds) {
        this.breeds = breeds;
    }
}
