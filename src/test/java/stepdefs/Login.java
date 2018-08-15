package stepdefs;

/**
 * Created by KaushikS on 15/08/2018.
 */
public class Login {


    private String username;
    private String password;
    private String Code;
    private String Message;

    public String getPassword() {
        return password;
    }

    public Login setPassword(String password) {
        this.password = password;
        return this;
    }



    public String getCode() {
        return Code;
    }

    public Login setCode(String Code) {
        this.Code = Code;
        return this;
    }



    public String getMessage() {
        return Message;
    }

    public Login setMessage(String Message) {
        this.Message = Message;
        return this;
    }




    public String getUsername() {
        return username;
    }

    public Login setUsername(String username) {
        this.username = username;
        return this;
    }




}
