import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Users{
    Scanner scan;
    private String login;
    private String pass;
    private Path usersFile;
    private List<String> usersList;
    
    public Users(){
        this.scan = new Scanner(System.in);

        //LÊ ARQUIVO TXT
        this.usersFile = Paths.get("C:/Users/HSK/Desktop/JAVA/Pratique/users.data.txt");
        try {
            this.usersList = Files.readAllLines(usersFile);
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
    }
    
    public void menuLogin(){
        System.out.println("Digite o seu login:");
        login = scan.next();
    }
    
    public void menuPass(){
        System.out.println("Digite a sua senha:");
        pass = scan.next();
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
        Boolean passValidation = false;

        //VERIFICA SE A SENHA ESTA CORRETA
        if(usersList.contains(login + "," + pass)){
            passValidation = true;
        }else{
            passValidation = false;
        }

        return passValidation;
    }

    public void setLogin(){
        menuLogin();

        if(checkLogin()){
            menuPass();

            if(checkPass()){
                System.out.println("Login efetuado com sucesso!");
            }else{
                System.out.println("Senha invalida tente novamente");
                setLogin();
            }

        }else{
            System.out.println("Login não encontrado, tente novamente...");
            setLogin();
        }
    }

    public void userRegister(){
        System.out.println("Por favor registre um novo usuario...");
        menuLogin();

        //VERIFICA SE O LOGIN JA EXISTE
        if(checkLogin()){
            System.out.println("Usuario já cadastrado, por favor realize o login...");
            setLogin();

        }else{
            menuPass();
            //REGISTRA USUARIO E SENHA NO ARQUIVO
            try {
                usersList.add(login + "," + pass);
                Files.write(usersFile, usersList);
                System.out.println("Usuario registrado com sucesso!");
            } catch (Exception e) {
                System.out.println("ERRO: " + e);
            }
        }
    }
}
