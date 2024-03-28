import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
public class Events {
    Scanner scan = new Scanner(System.in);

    private Path eventsFile;
    private List<String> eventList;
    Menu menu;
    public Events(Menu menu){
        this.menu = menu;
        this.eventsFile = Paths.get("./events.data.txt");

        try{
            eventList = Files.readAllLines(eventsFile);
        }catch(Exception e){
            System.out.println("Erro: " + e);
        }
    }

    public void list(){
        if(eventList.size() == 0){
            menu.mensagem("         Nenhum evento cadastrado");
        }else{
            menu.mensagem("                    Lista de eventos");
            for(int i = 0; i < eventList.size(); i++){
                System.out.printf("%d. %s%n", i + 1, eventList.get(i));
            }
        }
    }

    public void create(String typeEvent, String name, String date, String time, String userLogged){

        LocalDate D = LocalDate.now();
        LocalTime T = LocalTime.now();
        String newDate =  T.getHour() + ":" + T.getMinute() + " - " + D.getDayOfMonth() + "/" + D.getMonthValue() + "/" + D.getYear(); 
        
        String newEvent = typeEvent + " - " + name + " - " + "Data: " + date + " as " + time + "  | Criado por: " + userLogged  + " as " + newDate;
        eventList.add(newEvent);

        try{
            Files.write(eventsFile, eventList);
            menu.mensagem("         Evento cadastrado com sucesso!");
        }catch(Exception e){
            System.out.println("Erro: " + e);
        }
    }
    public void delete(Integer id){
        if(id < 1 || id > eventList.size()){
            menu.mensagem("Opção invalida, por favor tente novamente");
            menu.deleteEvent();
        }else{
            eventList.remove((int)id -1);
            try{
                Files.write(eventsFile, eventList);
                menu.mensagem("         Evento excluido com sucesso!");
    
            }catch(Exception e){
                System.out.println("Erro: " + e);
            }
        }
    }
    public void edit(){

    }

}
