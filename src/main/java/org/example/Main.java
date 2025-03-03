package org.example;

import org.example.Menu.MenuInicioSesion;
import org.example.Utils.Logger;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        Logger.logInfo("Iniciando la aplicación");
        new MenuInicioSesion().mostrarMenu();
    }
}