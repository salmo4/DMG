import javax.swing.*;
import java.util.Scanner;


/**
 * Created by icondor on 26/03/16.
 */
public class agenda {

    /* utility methods for INPUT/OUTPUT using a GUI or Console, please leave them as they are */

    // GUI
    public static int readIntGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        int returnValue = Integer.parseInt(input);
        return returnValue;
    }

    public static double readDoubleGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        double returnValue = Double.parseDouble(input);
        return returnValue;
    }

    public static String readStringGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        return input;
    }

    public static void printGUI(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public static void printGUI(int text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public static void printGUI(double text) {
        JOptionPane.showMessageDialog(null, text);
    }

    // CONSOLE
    public static String readStringConsole(String label) {
        System.out.print(label);
        String input = new Scanner(System.in).nextLine();
        return input;
    }

    public static int readIntConsole(String label) {
        System.out.print(label);
        int input = new Scanner(System.in).nextInt();
        return input;
    }

    public static double readDoubleConsole(String label) {
        System.out.print(label);
        double input = new Scanner(System.in).nextDouble();
        return input;
    }

    public static void printConsole(String text) {
        System.out.println(text);
    }

    public static void printConsole(int text) {
        System.out.println(text);
    }

    public static void printConsole(double text) {
        System.out.println(text);
    }

    /* end of utility methods*/



    //Start of actual code

    static Person[] listaPersoane = new Person[10]; //declarare+initializare
    static int index=0;

    //Functia principala, main
    public static void main(String[] arguments) {

        //Construire meniu

        int opt = 0;
        do {
            printConsole("Meniul principal:"); //l-am facut pe orizontala pentru ca imi ocupa prea mult din consola
            printConsole("1-Afisarea persoane / 2-Adaugare persoana / 3-Cautare nume / 4-Modificare /5.Stergere ");
            printConsole("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            opt = readIntConsole("Alegeti optiunea dorita:");
            System.out.println();

            if (opt == 1)
                listare();
            else if (opt == 2) {
                String nume = readStringConsole("Introduceti numele :");
                String telefon = readStringConsole("Introduceti telefonul :");
                creare(nume,telefon);
            }
            else if (opt == 3){
                String valoare = readStringConsole("introduceti numele : ");
                cautareAfisare(valoare);
            }
            else if (opt ==4){
                String nume = readStringConsole("introduceti numele de modificat:");

                 modificare(nume);
            }
            else if (opt == 5){
                String nume = readStringConsole("introduceti numele de sters:");
                stergere(nume);
            }

        } while (opt != 6);
    }


    //METODE

    //AFISARE SIR DE NUME
    public static void listare() {
        for (int i = 0; i < listaPersoane.length; i++) {
            if (listaPersoane[i] != null) {  // => la inceput nu afiseaza nimic, pentru ca nu am introdus nume

                Person p = listaPersoane[i]; // in p pun persoana curenta
                printConsole(p.getName());
                printConsole(p.getPhoneNumber());
            }
        }
        System.out.println();
    }

    //CREARE NUME NOU IN AGENDA
    public static void creare(String nume, String telefon) {

        Person p = new Person(nume,telefon);

        listaPersoane[index]=p;
        index++;
    }

//    //CREARE NUME NOU FARA DUPLICAT
//    public static void creareFaraDuplicat(String valoare) {
//        int i = cautare(valoare); //cautam daca mai exista
//        if (i == -1) {  //true inseamna ca inca nu exista numele introdus
//            if(index<listaPersoane.length) {
//                listaPersoane[index] = valoare;
//                index++;}
//            else{ // incercam sa gasim locuri libere in sir
//                for (int j = 0; j < listaPersoane.length; j++) {
//                    if (listaPersoane[j] == null) { //a gasit primul loc liber, il scriem aici
//                        listaPersoane[j] = valoare;
//                        System.out.println("Numele " + valoare + " a fost introdus in agenda!");
//                        break;
//                    }
//                    else{
//                        System.out.println("Ne pare rau, dar agenda este plina deja!");
//                        break;
//                    }
//                }
//
//            }
//        } else {
//            printConsole("Numele introdus exista deja.");
//        }
//        System.out.println();
//    }

    //CAUTARE NUME IN AGENDA
    public static int cautare(String numePersoana) {
        int r = -1;

        for (int i = 0; i < listaPersoane.length; i++) {

            Person p =listaPersoane[i];
            if (numePersoana.equals(p.getName())) { // pers de la pozitia i este
                r = i;
                break;
            }
        }
        System.out.println();
        return r;
    }

    //CAUTARE NUME + AFISARE POZITIE
    public static int cautareAfisare(String valoare) {
        int r = -1;

        for (int i = 0; i < listaPersoane.length; i++) {
            if (listaPersoane[i] != null) {
                if (valoare.equals(listaPersoane[i].getName())) {
                    r = i;
                    printConsole("Numele " + valoare + " a fost gasit pe pozitia " + r);
                    break;
                }
            }
        }
        if(r==-1)
            printConsole("Nu-i aici");

        System.out.println();
        return r;
    }

    //MODIFICARE NUME IN AGENDA
    public static void modificare(String nume) {
        int index = cautareAfisare(nume); //indexul numelui de modificat
        if (index == -1) {
            printConsole("Numele " + nume + " nu exista in agenda!");
        } else {
            String numeModif = readStringConsole("Introduceti numele dorit: ");
            listaPersoane[index].setName(numeModif);  //inlocuim numele de la indexul respectiv cu numele nou
            printConsole("Nume modificat cu succes. Verificati prin afisare.");
        }
        System.out.println();
    }

    //STERGERE NUME
    public static void stergere(String nume) {
        int index = cautare(nume); //indexul numelui de sters
        if (index == -1) {
            System.out.println();
            printConsole("Numele " + nume + " nu exista in agenda!");
            System.out.println();
        }
        else {
            listaPersoane[index] = null;
            printConsole("Nume sters cu succes. Verificati prin afisare.");
        }
        System.out.println();
    }


}