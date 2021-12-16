import java.util.ArrayList;
import java.util.Scanner;

public class Programa {
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = "";
        ArrayList<String> salida = new ArrayList<>();
        Mobil mobil;

        Mobil.setIn(in);

        while(!str.equals("0 0 0 0")) {

            str = in.nextLine();
            
            
            if(!str.equals("0 0 0 0")) {

                mobil = new Mobil(str);
                mobil.calcularPeso();

                if(mobil.isEstable()) {
                    
                    salida.add("SI");

                } else {

                    salida.add("NO");

                }

            } 

        }
        for ( String s : salida ) {

            System.out.println(s);
            
        }

    }
}

class Mobil {
    
    private static Scanner in;
    private int[] dist = new int[2];       // dist[0]  -> distancia izquierda
    private int[] pesos = new int[2];      // peso[0]  -> peso izquierdo
    private Mobil[] mobiles = new Mobil[2];;   
    private int pesoTotal = 0;             // suma de pesos de los mobiles y pesos hijos

    Mobil(Scanner in) {
        this(in.nextLine());
    }
    Mobil(String lin) {
        
        String[] strings = lin.split(" ");

        dist[0] = Integer.parseInt(strings[1]);
        dist[1] = Integer.parseInt(strings[3]);

        if (strings[0].equals("0") ) {

            pesos[0] = 0;
            mobiles[0] = new Mobil(in.nextLine());

        } else {

            pesos[0] = Integer.parseInt(strings[0]);

        }
        if (strings[2].equals("0") ) {

            pesos[1] = 0;
            mobiles[1] = new Mobil(in.nextLine());

        } else {

            pesos[1] = Integer.parseInt(strings[2]);

        }

    }

    public static void setIn(Scanner in) {
        Mobil.in = in;
    }

    public void calcularPeso() {

        for (int i : pesos) {

            pesoTotal += i;

        }
        for(Mobil m : mobiles ) {

            if (m != null ) {

                m.calcularPeso();
                pesoTotal += m.pesoTotal;

            }

        }
    }
    public boolean isEstable() {

        boolean salida;

        if (pesos[0] == 0 && pesos[1] == 0) { // dos mobiles

            salida = mobiles[0].isEstable() && mobiles[1].isEstable() && mobiles[0].pesoTotal * dist[0] == mobiles[1].pesoTotal * dist[1];

        }  else if(pesos[0] == 0 || pesos[1] == 0) {    // un mobil un peso

            if( pesos[0] != 0 ) {                   // para el peso en la izquierda

                salida = mobiles[1].isEstable() && pesos[0] * dist[0] == mobiles[1].pesoTotal * dist[1];

            } else {                                // para el peso en la derecha
                
                salida = mobiles[0].isEstable() && pesos[1] * dist[1] == mobiles[0].pesoTotal * dist[0];

            }

        } else {    // dos pesos

            salida = pesos[0] * dist[0] == pesos[1] * dist[1];

        }

        return salida;
    }

}