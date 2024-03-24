import java.util.Scanner;

public class Index{
    private int value;
    Scanner scan;

    public void main(String[] args){
        scan = new Scanner(System.in);
        System.out.println("Seja bem vindo sistema de eventos!");
        MenuInicial();
    }
    
    public void MenuInicial(){
        Users user = new Users();
        System.out.println("Por favor escolha uma opção para continuar...");
        System.out.println("1. Fazer login");
        System.out.println("2. Fazer cadastro");
        value = scan.nextInt();

        switch (value) {
            //LOGIN
            case 1:
                user.setLogin();
                break;
                
            //REGISTRES
            case 2:
                user.userRegister();
                break;
                
            default:
                System.out.println("Opções invalidas, por favor tente novamente...");
                MenuInicial();
                break;
        }
    }
}