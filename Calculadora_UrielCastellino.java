import java.util.Scanner;

public class Calculadora_UrielCastellino {
    public static void main(String[] args) {
        // Tu código aquí
        Scanner sc = new Scanner(System.in);

        boolean exit = true;

        while (exit){

            //declaracion e inicio de variables con la entrada de datos
            System.out.println("Por favor ingrese dos números, primero uno, presione enter y repita para ingresar el segundo: ");
            int number_1 = sc.nextInt();
            int number_2 = sc.nextInt();

            //Seccion para elegir la operacion

            System.out.println("Seleccione la operacion que quiere realizar: [1] para suma, [2] para resta, [3] para multiplicacon, [4] para división: ");
            byte option = sc.nextByte();

            if (option == 1){
                int sum = number_1 + number_2;
                System.out.println(sum);

            } else {
                if (option == 2){
                    //operacion de resta
                    System.out.println(number_1 - number_2);
                } else {
                    if (option == 3){
                        //operacion de multiplicacion
                        System.out.println(number_1 * number_2);
                    } else {
                        if (option == 4){
                            //operacion de divicion
                            //bloque de validacion para que no se pueda dividir por cero
                            if (number_2 != 0){
                                System.out.println(number_1 / number_2);
                            } else System.out.println("No se puede divir por cero. ");
                        }
                    }
                }

            }
            //bloque de codigo para poder continuar o salir del programa
            System.out.println("Si desea continuar, ingrese [1], para salir ingrese [0]");
            byte out = sc.nextByte();
            if (out == 0){
                //mensaje de salida y break para salir
                System.out.println("Muchas gracias por usar nuestros servicios. ");
                break;
            }
        }
    }
}
