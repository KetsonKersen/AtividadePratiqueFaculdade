public class Index{

    public void main(String[] args){
        System.out.println("Seja bem vindo sistema de eventos!");
        
        Menu menu = new Menu();
        Users user = new Users(menu);
        Events event = new Events(menu);

        menu.Inicial(user , event);
    }

}