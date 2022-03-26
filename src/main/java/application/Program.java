package application;

import ui.UserInterface;

public class Program {
    public static void main(String[] args) {

        UserInterface ui = new UserInterface();

        try {
            ui.limpaConsole();
            ui.mainMenu();
        }
        catch (Exception e){
            System.out.println("Erro inesperado!"+e.getMessage());
            e.printStackTrace();
        }
    }
}
