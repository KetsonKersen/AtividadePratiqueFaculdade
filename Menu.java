import java.util.Scanner;

public class Menu {
    private int value;
    Scanner scan = new Scanner(System.in);

    Users user;
    Events event;
    
    public void Inicial(Users user, Events event){
        this.user = user;
        this.event = event;

        title("MENU");
        mensagem("      Por favor escolha uma opção para continuar...");
        System.out.println("1. Fazer login");
        System.out.println("2. Fazer cadastro");
     
        System.out.printf("%nOPÇÃO: ");
        value = scan.nextInt();

        switch (value) {
            //LOGIN
            case 1:
            title("LOGIN");
                user.userLogin();
                break;
                
            //REGISTER
            case 2:
            title("CADASTRO");
                user.userRegister();
                break;
                
            default:
                mensagem("Opção invalida, por favor tente novamente");
                Inicial(user , event);
                break;
        }
    }

    // OPCOES DE USUARIOS
    public String login(){
        System.out.print("Login: ");
        return scan.next();
    }
    public String pass(){
        System.out.print("Senha: ");
        return scan.next();
    }

    //MENU DE EVENTOS
    public void InitialEvents(){
        title("EVENTOS");

        mensagem("      Por favor escolha uma opção para continuar...");
        
        System.out.println("1. Listar todos os eventos");
        System.out.println("2. Criar um novo evento");
        System.out.println("3. Excluir um evento");
        System.out.println("4. Editar um evento");

        System.out.printf("%nOPÇÃO: ");
        value = scan.nextInt();

        switch (value) {
            case 1:
                event.list();
                InitialEvents();
                break;

            case 2:
                createEvent();
                break;

            case 3:
                deleteEvent();
                break;

            case 4:
                event.edit();
                break;
        
            default:
                mensagem("Opção invalida, por favor tente novamente");
                break;
        }
    }

    public void createEvent(){
        mensagem("                  Criar novo evento");
        System.out.printf("Criar evento do tipo: %n");

        String[] options = {"Show","Cinema","Teatro","Esporte","Palestra"};
        
        for(int i = 0; i < options.length; i ++){
            System.out.printf("%d. %s%n", i+1 , options[i]);
        }
        
        System.out.printf("%nOPÇÃO: ");

        int index = scan.nextInt();

        if(index < 1 || index > options.length){
            mensagem("           Opção invalida, por favor tente novamente");
            createEvent();
        }else{
            System.out.println("Digite o nome do evento: ");
            String nameEvent = scan.next();
    
            System.out.println("Digite a data do evento: ");
            String dateEvent = scan.next();
    
            System.out.println("Digite o horario do evento: ");
            String timeEvent = scan.next();

            event.create(options[index - 1], nameEvent, dateEvent, timeEvent, user.getLogin());
        }
    }

    public void deleteEvent(){
        event.list();
        mensagem("         Digite o numero do evento para excluir");
        System.out.printf("Exluir evento: ");
        int idEvent = scan.nextInt();
        event.delete(idEvent);
    }

    public void title(String texto){
        System.out.printf("%n=====================| " + texto + " |===========================%n");
    }

    public void mensagem(String texto){
        System.out.printf("%n--------------------------------------------------------%n");
        System.out.printf("%s", texto);
        System.out.printf("%n--------------------------------------------------------%n%n");
    }

}
