import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Users{
    Scanner scan = new Scanner(System.in);
    
    private String login;
    private String pass;
    private Path usersFile;
    private List<String> usersList;

    Menu menu;
    
    public Users(Menu menu){
        this.menu = menu;
        
        //LÊ ARQUIVO TXT
        this.usersFile = Paths.get("./users.data.txt");
 
        try {
            this.usersList = Files.readAllLines(usersFile);
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
    }

    public String getLogin(){
        return this.login;
    }
    
    public Boolean checkLogin(){
        Boolean userRegistered = false;

        try {
            //LÊ APENAS OS LOGINS JÁ REGISTRADOS
            ArrayList<String> registeredLogin = new ArrayList<String>();
            for(String user:this.usersList){
                int index = user.indexOf(",");
                registeredLogin.add(user.substring(0, index));
            }

            //VERIFICA SE O LOGIN JA ESTA CADASTRADO
            if(registeredLogin.contains(login)){
                userRegistered = true;
            }else{
                userRegistered = false;
            }
            
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }

        return userRegistered;
    }

    public Boolean checkPass(){
        //VERIFICA SE A SENHA ESTA CORRETA
        return usersList.contains(login + "," + pass);
    }

    public void userLogin(){
        this.login = menu.login();

        if(checkLogin()){
            this.pass = menu.pass();

            if(checkPass()){
                menu.mensagem("            Login efetuado com sucesso!");
                menu.InitialEvents();
            }else{
                menu.mensagem("            Senha invalida tente novamente");
                userLogin();
            }

        }else{
            menu.mensagem("         Login não encontrado, tente novamente");
            userLogin();
        }
    }

    public void userRegister(){
        this.login = menu.login();

        //VERIFICA SE O LOGIN JA EXISTE
        if(checkLogin()){
            menu.mensagem("    Usuario já cadastrado, por favor realize o login");
            userLogin();

        }else{
            this.pass = menu.pass();

            //REGISTRA USUARIO E SENHA NO ARQUIVO
            try {
                usersList.add(login + "," + pass);
                Files.write(usersFile, usersList);
                menu.mensagem("            Usuario registrado com sucesso!");
            
            } catch (Exception e) {
                System.out.println("ERRO: " + e);
            }
        }
    }


}
