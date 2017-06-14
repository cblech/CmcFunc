/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmcfunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class CmcFunc {

    static CmcFunc cmcFunc = new CmcFunc();
    static boolean debug = true;

    ArrayList<CmcfFile> cmcfFiles = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        args = new String[]{"c", "test"};
        if (args.length > 0) {
            switch (args[0]) {
                case "c":
                case "compile":
                    if (args.length > 1) {
                        cmcFunc.compile(new File("projekts\\" + args[1]));
                    } else {
                        System.out.println("No porjekt name given");
                    }
                    break;
                case "n":
                case "new":
                    cmcFunc.makeNewProjekt();
                    break;
                case "a":
                case "change":
                    if (args.length > 1) {
                        cmcFunc.changeProjekt(new File("projekts\\" + args[1]));
                    } else {
                        System.out.println("No porjekt name given");
                    }
                    break;
                case "h":
                case "help":
                    cmcFunc.printHelp();
                    break;
                default:
                    cmcFunc.printHelp();
                    System.out.println();
                    System.out.println("Please use an argument");
                    break;
            }
        } else {
            cmcFunc.printHelp();
            System.out.println();
            System.out.println("Please use an argument");
        }
    }

    private void printHelp() {
        cmcFunc.printLogo();
        System.out.println("Use 'h' or 'help' to display this text");
        System.out.println("Use 'n' or 'new' to create a new projekt");
        System.out.println("Use 'a' or 'change' to change projekt properties");
        System.out.println("Use 'c' or 'compile' to compile a projekt");
    }

    private void printLogo() {
        //Print Logo
        System.out.println("                       \u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591");
        System.out.println("  \u2591\u2591\u2591\u2591  \u2591     \u2591  \u2591\u2591\u2591\u2591  \u2591      \u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591");
        System.out.println(" \u2591    \u2591 \u2591\u2591   \u2591\u2591 \u2591    \u2591 \u2591 \u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591");
        System.out.println(" \u2591      \u2591 \u2591 \u2591 \u2591 \u2591      \u2591 \u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591");
        System.out.println(" \u2591      \u2591  \u2591  \u2591 \u2591      \u2591     \u2591\u2591 \u2591\u2591\u2591 \u2591 \u2591  \u2591\u2591\u2591\u2591   \u2591");
        System.out.println(" \u2591      \u2591     \u2591 \u2591      \u2591 \u2591\u2591\u2591\u2591\u2591\u2591 \u2591\u2591\u2591 \u2591 \u2591\u2591\u2591\u2591 \u2591 \u2591\u2591\u2591\u2591");
        System.out.println(" \u2591    \u2591 \u2591     \u2591 \u2591    \u2591 \u2591 \u2591\u2591\u2591\u2591\u2591\u2591 \u2591\u2591\u2591 \u2591 \u2591\u2591\u2591\u2591 \u2591 \u2591\u2591\u2591\u2591");
        System.out.println("  \u2591\u2591\u2591\u2591  \u2591     \u2591  \u2591\u2591\u2591\u2591  \u2591 \u2591\u2591\u2591\u2591\u2591\u2591\u2591   \u2591\u2591 \u2591\u2591\u2591\u2591 \u2591\u2591   \u2591");
        System.out.println("                       \u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591");
        System.out.println();
    }

    private void selectMcInstallFolder(ProjektProperties pp, BufferedReader br) throws IOException {
        System.out.print("Insalation folder of your Minecraft [default]: ");
        String inLine = br.readLine();
        if (inLine.equals("")) {
            pp.setProperty(pp.keyUseDefaultMcPath, "true");
        } else {
            pp.setProperty(pp.keyUseDefaultMcPath, "false");
            pp.setProperty(pp.keyMcPath, inLine);
        }
    }

    private void choseMap(ProjektProperties pp, BufferedReader br) throws IOException {
        while (true) {
            System.out.print("Name of your Minecraft map: ");
            String inLine = br.readLine();

            File mcSaveFileFolder = new File(pp.getMcFolder(), "saves");

            boolean isOk = false;
            for (String file : mcSaveFileFolder.list()) {
                if (file.equals(inLine)) {
                    isOk = true;
                }
            }
            if (isOk) {
                pp.setProperty(pp.keyMcMapName, inLine);
                break;
            } else {
                System.out.println("This map does not exist. Make sure to type in the name of the maps foder.");
            }
        }
    }

    private void assignGameLoop(ProjektProperties pp, BufferedReader br) throws IOException {
        System.out.print("Should the loop function be asigned as GameLoopFunction?[Y,n]: ");
        String inLine = br.readLine();
        if (inLine.equals("n") || inLine.equals("N")) {
            pp.setProperty(pp.keyAsignGameLoop, "false");
        } else {
            pp.setProperty(pp.keyAsignGameLoop, "true");
        }
    }

    private void inDevelopment(ProjektProperties pp, BufferedReader br) throws IOException {
        System.out.print("Is the CmcProjekt in development state?[Y,n]: ");
        String inLine = br.readLine();
        if (inLine.equals("n") || inLine.equals("N")) {
            pp.setProperty(pp.keyInDeveloping, "false");
        } else {
            pp.setProperty(pp.keyInDeveloping, "true");
        }
    }

    private void makeNewProjekt() {
        try {
            //Init reader
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            printLogo();

            System.out.println("Creating new projekt.");

            //Chose Name
            System.out.print("Name your projekt: ");
            String inLine = br.readLine();
            ProjektProperties pp = new ProjektProperties(new File("projekts\\" + inLine + "\\"));
            pp.makeNew();
            //pp.setProperty(pp.keyName, inLine);

            //Select Minecraft install folder
            selectMcInstallFolder(pp, br);

            //Chose Map
            choseMap(pp, br);

            //Asign gameloop
            assignGameLoop(pp, br);

            //in Development state
            inDevelopment(pp, br);

            //Save new Propeties
            pp.store();
        } catch (IOException ex) {
            Logger.getLogger(CmcFunc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void changeProjekt(File projekt) {
        if (projekt.exists()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                ProjektProperties pp = new ProjektProperties(projekt);
                pp.load();

                printLogo();
                //Editing Info
                System.out.println("Editing projekt \"" + projekt.getName() + "\"");

                //minecraft install folder
                System.out.print("Minecraft installfolder is set to ");
                if (pp.getProperty(pp.keyUseDefaultMcPath).equals("true")) {
                    System.out.println("default");
                } else {
                    System.out.println(pp.getProperty(pp.keyMcPath));
                }
                if (changeQm(br)) {
                    selectMcInstallFolder(pp, br);
                }

                //chose map
                System.out.println("Map is set to " + pp.getProperty(pp.keyMcMapName));
                if (changeQm(br)) {
                    choseMap(pp, br);
                }

                //assign gameloop
                System.out.println("Gameloop asignment is set to " + pp.getProperty(pp.keyAsignGameLoop));
                if (changeQm(br)) {
                    assignGameLoop(pp, br);
                }

                //in Development
                System.out.println("In development is set to " + pp.getProperty(pp.keyInDeveloping));
                if (changeQm(br)) {
                    inDevelopment(pp, br);
                }

                pp.store();
            } catch (IOException ex) {
                Logger.getLogger(CmcFunc.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("The projekt '" + projekt.getName() + "' does not exist.");
        }
    }

    private boolean changeQm(BufferedReader br) throws IOException {
        System.out.print("Do you want to change this propertie?[y,N]: ");
        String inLine = br.readLine();
        if (inLine.equals("y") || inLine.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    private void compile(File projekt) {
        if (projekt.exists()) {
            System.out.print("Compiling the projekt '" + projekt.getName() + "' in ");
            ProjektProperties pp = new ProjektProperties(projekt);
            if (pp.load()) {
                if (Boolean.parseBoolean(pp.getProperty(pp.keyInDeveloping))) {
                    System.out.println("development mode.");
                } else {
                    System.out.println("release mode.");
                }
                
                cmcfFiles.add(new CmcfFile(new File(projekt, "main.cmcf")));
                
                for (CmcfFile cmcfFile : cmcfFiles) {
                    cmcfFile.load();
                }
            }
        } else {
            System.out.println("The projekt '" + projekt.getName() + "' does not exist.");
        }
    }
}
