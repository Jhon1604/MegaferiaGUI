package core.main;

import com.formdev.flatlaf.FlatDarkLaf;
import core.controllers.ApplicationController;
import core.controllers.utils.DependencyInjector;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        System.setProperty("flatlaf.useNativeLibrary", "false");
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("No se pudo inicializar el tema FlatLaf");
        }
        DependencyInjector injector = new DependencyInjector().initialize();
        ApplicationController appController = new ApplicationController(injector);
        appController.start();
    }
}
