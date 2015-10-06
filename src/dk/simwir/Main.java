package dk.simwir;

import dk.simwir.file.handling.CreateFile;
import dk.simwir.file.handling.ReadFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
public class Main {

    static final public String VERSION = "1.0";

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        boolean overwrite = false;
        String tempOutput = null;

        System.out.print(Text.COPYRIGHT + Text.INTRO_A + Text.INTRO_B + Text.INTRO_C + Text.INTRO_D);
        String saveFile = keyboard.next();

        while (saveFile.equals("warranty")) {
            System.out.println(Text.WARRANTY);
            saveFile = keyboard.next();
        }

        ReadFile readFile = new ReadFile();

        CreateFile createFile = new CreateFile();

        readFile.openFile(saveFile);
        while (!readFile.fileOpen) {
            System.out.println(Text.FILE_NOT_OPEN);
            saveFile = keyboard.next();
            while (saveFile.equals("warranty")) {
                System.out.println(Text.WARRANTY);
                saveFile = keyboard.next();
            }
            readFile.openFile(saveFile);
        }
        System.out.println("File " + saveFile + ".prison found\n");
        System.out.println(Text.OUTPUT_NAME);
        String outputFile = keyboard.next();
        if (outputFile.equals(saveFile)) {
            overwrite = true;
            tempOutput = generateTempName();
            createFile.openTempFile(tempOutput);
        } else {
            createFile.openFile(outputFile);
        }
        System.out.println("File " + outputFile + ".prison created\nHealing. Please wait");

        readFile.readFile(createFile);

        readFile.closeFile();
        createFile.closeFile();
        if (overwrite) {
            if (tempOutput != null) {
                readFile.openTempFile(tempOutput);
                createFile.openFile(saveFile);
                readFile.copyToSaveFile(createFile);
                readFile.closeFile();
                createFile.closeFile();
                try {
                    Files.delete(new File(tempOutput + ".temp").toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Error, try not overwriting.");
            }
        }
        System.out.println(Text.DONE);
        try {
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static String generateTempName() {
        return "temp save " + System.currentTimeMillis();
    }


}
