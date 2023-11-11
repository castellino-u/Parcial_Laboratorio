package parcial_2;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        // Bloque while para simular un menú
        while (true) {
            // Bloque de entrada de datos
            // Inicializo la lista dna
            ArrayList<String> dna = new ArrayList<>();

            // Bloque para agregar las cadenas a la lista
            for (int i = 0; i < 6; i++) {
                while (true) {
                    System.out.print("Ingrese la cadena " + (i + 1) + " (usando A, C, G y T): ");
                    String adnInput = sc.nextLine();

                    if (adnInput.length() == 6) {
                        boolean caracteresValidos = true;

                        for (char caracter : adnInput.toCharArray()) {
                            if ("ACGTacgt".indexOf(caracter) == -1) {
                                caracteresValidos = false;
                                break;
                            }
                        }

                        if (caracteresValidos) {
                            // Si se verificó que  está bien, agrega a la lista de cadenas en mayúsculas
                            dna.add(adnInput.toUpperCase());
                            break;
                        }
                    }

                    System.out.println("La cadena debe tener exactamente 6 caracteres y solo puede contener A, C, G y T.");
                }
            }

            // Llamado a la función
            boolean mutant = isMutant(dna);

            if (mutant) {
                System.out.println("El ADN ingresado pertenece a un mutante.");
            } else {
                System.out.println("El ADN ingresado pertenece a un humano.");
            }

            while (true) {
                System.out.print("¿Desea cargar otro ADN? ");
                try {
                    System.out.println("Ingrese [1] para continuar y [2] para salir: ");
                    option = Integer.parseInt(sc.nextLine());

                    if (option == 1 || option == 2) {
                        break;
                    } else {
                        System.out.println("Datos ingresados incorrectos.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Datos ingresados incorrectos.");
                }
            }

            if (option == 2) {
                break;
            }
        }

        sc.close();
    }

    // Función que determina si el ADN es de un mutante
    public static boolean isMutant(ArrayList<String> dna) {
        int countSecuence = 0;

        // PRIMERO FILAS -------------------------------------------------------------------------
        int count = 0;
        for (int line = 0; line < dna.size(); line++) {
            String secuenciaFila = "";
            for (int column = 0; column < dna.get(line).length(); column++) {
                if (column == 0) {
                    secuenciaFila = String.valueOf(dna.get(line).charAt(column));
                } else if (dna.get(line).charAt(column) == dna.get(line).charAt(column - 1)) {
                    secuenciaFila += dna.get(line).charAt(column);
                } else {
                    secuenciaFila = String.valueOf(dna.get(line).charAt(column));
                }

                if (secuenciaFila.length() >= 4) {
                    count++;
                    countSecuence++;
                }
            }
        }

        // SEGUNDO COLUMNAS ---------------------------------------------------------------------
        int line_1 = dna.size();
        int column_1 = dna.get(0).length();
        count = 0;
        for (int j = 0; j < column_1; j++) {
            String columnSequence = "";
            for (int i = 0; i < line_1; i++) {
                if (i == 0) {
                    columnSequence = String.valueOf(dna.get(i).charAt(j));
                } else if (dna.get(i).charAt(j) == dna.get(i - 1).charAt(j)) {
                    columnSequence += dna.get(i).charAt(j);
                } else {
                    columnSequence = String.valueOf(dna.get(i).charAt(j));
                }

                if (columnSequence.length() >= 4) {
                    count++;
                    countSecuence++;
                }
            }
        }

        // AHORA DIAGONALES DESCENDENTES ----------------------------------------------------------
        String secuenciaDiag6 = "";
        String sequenceDiag5 = "";
        String sequenceDiag4 = "";
        String sequenceDiag5_2 = "";
        String sequenceDiag4_2 = "";

        for (int chain = 0; chain < dna.size(); chain++) {
            for (int lettre = 0; lettre < dna.size(); lettre++) {
                if (chain == lettre) {
                    if (chain != 0) {
                        if (dna.get(chain).charAt(lettre) == dna.get(chain - 1).charAt(lettre - 1)) {
                            secuenciaDiag6 += dna.get(chain).charAt(lettre);
                        } else {
                            secuenciaDiag6 = String.valueOf(dna.get(chain).charAt(lettre));
                        }
                    } else {
                        secuenciaDiag6 = String.valueOf(dna.get(chain).charAt(lettre));
                    }

                    if (secuenciaDiag6.length() >= 4) {
                        secuenciaDiag6 = "";
                        countSecuence++;
                    }
                }

                // Diagonales de 5 abajo del 0,0
                if ((chain - 1) == lettre) {
                    if (lettre == 0) {
                        sequenceDiag5 = String.valueOf(dna.get(chain).charAt(lettre));
                    } else if (dna.get(chain).charAt(lettre) == dna.get(chain - 1).charAt(lettre - 1)) {
                        sequenceDiag5 += dna.get(chain).charAt(lettre);
                    } else {
                        sequenceDiag5 = String.valueOf(dna.get(chain).charAt(lettre));
                    }

                    if (sequenceDiag5.length() >= 4) {
                        countSecuence++;
                        sequenceDiag5 = "";
                    }
                }

                // Diagonal de 4 abajo del 0,0
                if ((chain - 2) == lettre) {
                    if (lettre == 0) {
                        sequenceDiag4 = String.valueOf(dna.get(chain).charAt(lettre));
                    } else if (dna.get(chain).charAt(lettre) == dna.get(chain - 1).charAt(lettre - 1)) {
                        sequenceDiag4 += dna.get(chain).charAt(lettre);
                    } else {
                        sequenceDiag4 = String.valueOf(dna.get(chain).charAt(lettre));
                    }

                    if (sequenceDiag4.length() >= 4) {
                        countSecuence++;
                        sequenceDiag4 = "";
                    }
                }

                // Diagonal de 5 ------------- arriba del 0,0
                if ((chain + 1) == lettre) {
                    if (chain == 0) {
                        sequenceDiag5_2 = String.valueOf(dna.get(chain).charAt(lettre));
                    } else if (dna.get(chain).charAt(lettre) == dna.get(chain - 1).charAt(lettre - 1)) {
                        sequenceDiag5_2 += dna.get(chain).charAt(lettre);
                    } else {
                        sequenceDiag5_2 = String.valueOf(dna.get(chain).charAt(lettre));
                    }

                    if (sequenceDiag5_2.length() >= 4) {
                        countSecuence++;
                        sequenceDiag5_2 = "";
                    }
                }

                // Diagonal de 4 --------------arriba del 0,0
                if ((chain + 2) == lettre) {
                    if (chain == 0) {
                        sequenceDiag4_2 = String.valueOf(dna.get(chain).charAt(lettre));
                    } else if (dna.get(chain).charAt(lettre) == dna.get(chain - 1).charAt(lettre - 1)) {
                        sequenceDiag4_2 += dna.get(chain).charAt(lettre);
                    } else {
                        sequenceDiag4_2 = String.valueOf(dna.get(chain).charAt(lettre));
                    }

                    if (sequenceDiag4_2.length() >= 4) {
                        countSecuence++;
                        sequenceDiag4_2 = "";
                    }
                }
            }
        }

        // DIAGONALES ASCENDENTES ----------------------------------------------------------------
        for (int i = 0; i < dna.size(); i++) {
            for (int j = 0; j < dna.get(i).length(); j++) {
                if ((i == 3) && (j == 0)) {
                    if (dna.get(i).charAt(j) == dna.get(i - 1).charAt(j + 1) && dna.get(i - 2).charAt(j + 2) == dna.get(i - 3).charAt(j + 3)) {
                        countSecuence++;
                    }
                }

                // diagonal de 5
                if ((i == 4) && (j == 0)) {
                    if ((dna.get(i).charAt(j) == dna.get(i - 1).charAt(j + 1))) {
                        if ((dna.get(i).charAt(j) == dna.get(i - 2).charAt(j + 2))) {
                            if ((dna.get(i).charAt(j) == dna.get(i - 3).charAt(j + 3))) {
                                if (dna.get(i).charAt(j) == dna.get(i - 4).charAt(j + 4)) {
                                    countSecuence++;
                                } else {
                                    countSecuence++;
                                }
                            }
                        } else if ((dna.get(i - 1).charAt(j + 1) == dna.get(i - 2).charAt(j + 2))) {
                            if ((dna.get(i - 2).charAt(j - 2) == dna.get(i - 3).charAt(j + 3))) {
                                if ((dna.get(i - 1).charAt(j + 1) == dna.get(i - 4).charAt(j + 4))) {
                                    countSecuence++;
                                }
                            }
                        }
                    }
                }

                // Diagonal principal ascendente
                if ((i == 5) && (j == 0)) {
                    if ((dna.get(i).charAt(j) == dna.get(i - 1).charAt(j + 1))) {
                        if ((dna.get(i).charAt(j) == dna.get(i - 2).charAt(j + 2))) {
                            if ((dna.get(i).charAt(j) == dna.get(i - 3).charAt(j + 3))) {
                                if ((dna.get(i).charAt(j) == dna.get(i - 4).charAt(j + 4))) {
                                    if ((dna.get(i).charAt(j) == dna.get(i - 5).charAt(j + 5))) {
                                        countSecuence++;
                                    } else {
                                        countSecuence++;
                                    }
                                } else {
                                    countSecuence++;
                                }
                            }
                        }
                    } else if ((dna.get(i - 1).charAt(j + 1) == dna.get(i - 2).charAt(j + 2))) {
                        if ((dna.get(i - 1).charAt(j + 1) == dna.get(i - 3).charAt(j + 3))) {
                            if ((dna.get(i - 1).charAt(j + 1) == dna.get(i - 4).charAt(j + 4))) {
                                if ((dna.get(i - 1).charAt(j + 1) == dna.get(i - 5).charAt(j + 5))) {
                                    countSecuence++;
                                }
                            }
                        }
                    } else if ((dna.get(i - 2).charAt(j + 2) == dna.get(i - 3).charAt(j + 3)) && (dna.get(i - 2).charAt(j + 2) == dna.get(i - 5).charAt(j + 5))) {
                        countSecuence++;
                    }
                }

                // Diagonal de 5 secundaria
                if ((i == 5) && (j == 1)) {
                    if ((dna.get(i).charAt(j) == dna.get(i - 1).charAt(j + 1))) {
                        if ((dna.get(i).charAt(j) == dna.get(i - 2).charAt(j + 2))) {
                            if ((dna.get(i).charAt(j) == dna.get(i - 3).charAt(j + 3))) {
                                if (dna.get(i).charAt(j) == dna.get(i - 4).charAt(j + 4)) {
                                    countSecuence++;
                                } else {
                                    countSecuence++;
                                }
                            }
                        } else if ((dna.get(i - 1).charAt(j + 1) == dna.get(i - 2).charAt(j + 2))) {
                            if ((dna.get(i - 2).charAt(j - 2) == dna.get(i - 3).charAt(j + 3))) {
                                if ((dna.get(i - 1).charAt(j + 1) == dna.get(i - 4).charAt(j + 4))) {
                                    countSecuence++;
                                }
                            }
                        }
                    }
                }

                // Diagonal de 4 secundaria
                if ((i == 5) && (j == 2)) {
                    if (dna.get(i).charAt(j) == dna.get(i - 1).charAt(j + 1) && dna.get(i - 2).charAt(j + 2) == dna.get(i - 3).charAt(j + 3)) {
                        countSecuence++;
                    }
                }
            }
        }

        // Si el contador de secuencias contó 2 o más secuencias, devuelve true; de lo contrario, false.
        return countSecuence >= 2;
    }
}

