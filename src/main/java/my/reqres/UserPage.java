package my.reqres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lv.ctco.automationschool2020.User;
import my.reqres.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPage {
    public User[] getData() {
        return data;
    }

    public void setData(User[] data) {
        this.data = data;
    }

    User[] data;
}
