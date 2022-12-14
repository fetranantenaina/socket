import java.net.*;
import java.util.*;
import java.io.*;

public class Mainc2{
    public static void main(String[] args){
        try(Socket socket = new Socket("localhost", 5000)){
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output =new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner=new Scanner(System.in);
            String userInput;
            String response;
            String clientName="Kanty";
            ClientThread clientthread=new ClientThread(socket);
            clientthread.start();
            do{
                if(clientName.equals("empty")){
                    System.out.println("Enter your name");
                    userInput= scanner.nextLine();
                    clientName=userInput;
                    output.println(userInput);
                    if(userInput.equals("exit")){
                        break;
                    }
                }else{
                    String message = ("(" + clientName + ")" + "message : ");
                    System.out.println(message);
                    userInput = scanner.nextLine();
                    output.println(message + " " + userInput);
                    if(userInput.equals("exit")){
                        break;
                    }
                }
            }while(!userInput.equals("exit"));
        }catch(Exception e){
            System.out.println("Exception occured in client main" + e.getStackTrace());
        }
        
    }
}
