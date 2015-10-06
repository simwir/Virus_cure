package dk.simwir.file.handling;

import dk.simwir.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*
    Copyright © 2015 Simon Virenfeldt

    This file is part of Virus cure.

    Virus cure is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Virus cure is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
public class ReadFile {

    private Scanner scanner;
    public boolean fileOpen = false;
    public int healedPrisoners = 0;

    public void openFile(String file){
        try {
            fileOpen = true;
            scanner = new Scanner(new File(Text.RELATIVE_PATH+file + ".prison"));
            //scanner = new Scanner(new File("C:\\Users\\Simon\\AppData\\Local\\Introversion\\Prison Architect\\saves\\"+file+".prison"));
        }catch (Exception e){
            System.out.println("File not found");
            fileOpen = false;
        }
    }

    public void openTempFile(String file){
        try {
            fileOpen = true;
            scanner = new Scanner(new File(file+".temp"));
        }catch (Exception e){
            System.out.println("File not found");
            fileOpen = false;
        }
    }

    public void readFile(CreateFile file){
        while (scanner.hasNext()){
            String token = scanner.next();
            if (token.equals("StatusEffects")){
                file.addRecord(token);
                boolean a = true;
                while (a){
                    ArrayList<String> effect = new ArrayList<String>();
                    boolean b = true;
                    while (b) {
                        String string = scanner.next();
                        if (!string.equals("END")) {
                            effect.add(string);
                        } else {
                            if (effect.size()==0){
                                a=b=false;
                                file.addRecord(string);
                            }else if (!effect.get(1).equals("virus")){
                                effect.add(string);
                                for (String anEffect : effect) {
                                    file.addRecord(anEffect);
                                }
                                b=false;
                            }else {
                                b = false;
                                healedPrisoners++;
                            }
                        }
                    }
                }
            }else {
                file.addRecord(token);
            }
        }
        System.out.println(healedPrisoners + " healed");
    }

    public void copyToSaveFile(CreateFile file){
        while (scanner.hasNext()){
            file.addRecord(scanner.next());
        }
    }

    public void closeFile(){
        scanner.close();
    }
}
