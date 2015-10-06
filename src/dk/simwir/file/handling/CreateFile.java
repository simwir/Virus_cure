package dk.simwir.file.handling;

import dk.simwir.Text;

import java.util.Formatter;

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
public class CreateFile {

    private Formatter formatter;

    public void openFile(String file){
        try {
            formatter = new Formatter(Text.RELATIVE_PATH+file + ".prison");
            //formatter = new Formatter("C:\\Users\\Simon\\AppData\\Local\\Introversion\\Prison Architect\\saves\\"+file+".prison");
        }catch (Exception e){
            System.out.println("Could not create file\nTerminating");
            try {
                wait( 10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.exit(1);

        }
    }

    public void openTempFile(String file){
        try {
            formatter = new Formatter(file+".temp");
        }catch (Exception e){
            System.out.println("Could not create file\nTerminating");
            try {
                wait( 10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.exit(1);

        }
    }

    public  void addRecord(String record){
        formatter.format("%s", record+" ");
    }

    public void closeFile(){
        formatter.close();
    }
}
