/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmcfunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author jonathan
 */
public class CmcfFile {

    File path;
    String[] lines;

    public CmcfFile(File path) {
        this.path = path;
    }

    void load() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            System.out.println(path.getAbsolutePath() + " does not exist.");
        }

    }

}
