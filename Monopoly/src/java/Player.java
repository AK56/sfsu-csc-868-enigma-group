public class Player {
    
    private String name;
    private String userName;
    private String password;
    
    public void setName(String name){
        this.name = name;
    }
     
    public void setUserName(String name){
        this.userName = name;
    }
    
     public void setPassword(String pw){
        this.password = pw;
    }
     
      public String getName(){
        return name;
    }
     
    public String getUserName(){
        return userName;
    }
    
     public String getPassword(){
        return password;
    }
}