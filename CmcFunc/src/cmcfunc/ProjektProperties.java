/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmcfunc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author jonathan
 */
public class ProjektProperties {

    ArrayList<String> propertieKeys = new ArrayList<>();

    Properties projektProperties = null;
    File projektPath;
    File baseFolder;

    //Variables
//    private String name;
//    private File mcPath;
//    private boolean useDefaultMcPath;
//    private String mcMapName;
//    private boolean asignGameLoop;
    
    //Keys
    final String keyName = "z2n457sn";
    final String keyMcPath = "wvgzeazva";
    final String keyUseDefaultMcPath = "ah7eb5z";
    final String keyMcMapName = "sbes6bs";
    final String keyAsignGameLoop = "at4vabbt";
    final String keyInDeveloping = "asrzab4";

    public ProjektProperties(File projektPath) {
        this.projektPath = projektPath;
        propertieKeys.add(keyName);
        propertieKeys.add(keyMcPath);
        propertieKeys.add(keyUseDefaultMcPath);
        propertieKeys.add(keyMcMapName);
        propertieKeys.add(keyAsignGameLoop);
        propertieKeys.add(keyInDeveloping);
    }

    public boolean load() {
        try {
            projektProperties = new Properties();
            projektProperties.load(new FileReader(new File(projektPath.getPath() + "\\proj.ekt")));

            return true;
        } catch (IOException ex) {
            if (CmcFunc.debug) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public boolean store() {
        try {
            projektPath.mkdirs();
            projektProperties.store(new FileWriter(new File(projektPath.getPath() + "\\proj.ekt")), null);

            return true;
        } catch (IOException ex) {
            if (CmcFunc.debug) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public void makeNew() {
        projektProperties = new Properties();
    }

    public void setProperty(String key, String value) {
        projektProperties.setProperty(key, value);
    }

    public String getProperty(String key) {
        return projektProperties.getProperty(key);
    }

    public File getMcFolder() {
        if (Boolean.parseBoolean(getProperty(keyUseDefaultMcPath))) {
            return new File(System.getenv("APPDATA") + "\\.minecraft");
        } else {
            return new File(getProperty(keyMcPath));
        }
    }
}
