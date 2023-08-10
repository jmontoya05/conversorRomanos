import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("Por favor ingrese un número romano");
            String romano = sc.nextLine();
            romano = romano.toUpperCase().replace(" ", "");
            int tamano = romano.length();

            Boolean romanoValido = validarRomano(romano, tamano);

            if (romanoValido) {
                int entero = convertirRomano(romano, tamano);
                System.out.println("El número equivalente a " + romano + " es " + entero);
            }
        }
    }
    private static Boolean validarRomano(String romano, int tamano) {

        int cantidadI = 0, cantidadV = 0, cantidadX = 0, cantidadL = 0, cantidadC = 0, cantidadD = 0, cantidadM = 0;
        char siguiente;
        char proximoSiguiente;
        for (int i = 0; i < tamano; i++) {
            if (i < tamano - 1) {
                siguiente = romano.charAt(i + 1);
            } else {
                siguiente = 'U';
            }
            if (i < tamano - 2) {
                proximoSiguiente = romano.charAt(i + 2);
            } else {
                proximoSiguiente = 'U';
            }
            if (romano.charAt(i) == 'I' || romano.charAt(i) == 'V' || romano.charAt(i) == 'X' || romano.charAt(i) == 'L' || romano.charAt(i) == 'C' || romano.charAt(i) == 'D' || romano.charAt(i) == 'M') {
                switch (romano.charAt(i)) {
                    case 'I':
                        cantidadI += 1;
                        if (siguiente == 'L' || siguiente == 'C' || siguiente == 'D' || siguiente == 'M') {
                            System.out.println("El número que ingresó no es valido, la 'I' solo puede ir a la izquierda de 'V' o 'X'");
                            return false;
                        }
                        if (siguiente == 'V' || siguiente == 'X') {
                            if (cantidadI > 1) {
                                System.out.println("El número que ingresó no es valido, la 'I' no puede estar restando y repetida a la izquierda");
                                return false;
                            }
                            if (proximoSiguiente != 'U') {
                                System.out.println("El número que ingresó no es valido, la 'I' no puede estar a la izquierda de dos de mayor o igual valor'");
                                return false;
                            }
                        }
                        if (siguiente != 'I' && siguiente != 'U') {
                            cantidadI = 0;
                        }
                        break;
                    case 'V':
                        if (siguiente == 'X' || siguiente == 'L' || siguiente == 'C' || siguiente == 'D' || siguiente == 'M') {
                            System.out.println("El número que ingresó no es valido, la 'V' solo puede ir a la izquierda de 'I' porque no puede usarse para restar");
                            return false;
                        }
                        cantidadV += 1;
                        break;
                    case 'X':
                        cantidadX += 1;
                        if (siguiente == 'D' || siguiente == 'M') {
                            System.out.println("El número que ingresó no es valido, la 'X' solo puede ir a la izquierda de 'L' o 'C'");
                            return false;
                        }
                        if (siguiente == 'L' || siguiente == 'C') {
                            if (cantidadX > 1){
                                System.out.println("El número que ingresó no es valido, la 'X' no puede estar restando y repetida a la izquierda");
                                return false;
                            }
                            if (proximoSiguiente != 'U' && proximoSiguiente != 'I' && proximoSiguiente != 'V') {
                                System.out.println("El número que ingresó no es valido, la 'X' no puede estar a la izquierda de dos de mayor o igual valor'");
                                return false;
                            }
                        }
                        if (siguiente != 'X' && siguiente != 'U') {
                            cantidadX = 0;
                        }
                        break;
                    case 'L':
                        if (siguiente == 'C' || siguiente == 'D' || siguiente == 'M') {
                            System.out.println("El número que ingresó no es valido, porque la 'L' no puede usarse para restar");
                            return false;
                        }
                        cantidadL += 1;
                        break;
                    case 'C':
                        cantidadC += 1;
                        if (siguiente == 'D' || siguiente == 'M') {
                            if (cantidadC > 1){
                                System.out.println("El número que ingresó no es valido, la 'C' no puede estar restando y repetida a la izquierda");
                                return false;
                            }
                            if (proximoSiguiente == 'C' || proximoSiguiente == 'D' || proximoSiguiente == 'M') {
                                System.out.println("El número que ingresó no es valido, la 'C' no puede estar a la izquierda de dos de mayor o igual valor'");
                                return false;
                            }
                        }
                        if (siguiente != 'C' && siguiente != 'U') {
                            cantidadC = 0;
                        }
                        break;
                    case 'D':
                        if (siguiente == 'M') {
                            System.out.println("El número que ingresó no es valido, la 'D' no puede estar a la izquierda de 'M'");
                            return false;
                        }
                        cantidadD += 1;
                        break;
                    default:
                        cantidadM += 1;
                        if (siguiente != 'M' && siguiente != 'U') {
                            cantidadM = 0;
                        }
                }
                if (cantidadI > 3 || cantidadV > 1 || cantidadX > 3 || cantidadL > 1 || cantidadC > 3 || cantidadD > 1 || cantidadM > 3) {
                    System.out.println("El número que ingresó no es valido, repite 'I', 'X', 'C' o 'M' mas de 3 veces o 'V', 'L' o 'D' mas de una vez");
                    return false;
                }
            } else {
                System.out.println("El número que ingresó no es valido, contiene uno o varios caracteres que no pertenecen a los números romanos");
                return false;
            }
        }

        return true;
    }

    private static int convertirRomano(String romano, int tamano) {
        int numEntero = 0;
        char siguiente;
        for (int i = 0; i < tamano; i++) {
            if (i < tamano - 1) {
                siguiente = romano.charAt(i + 1);
            } else {
                siguiente = 'I';
            }
            switch (romano.charAt(i)) {
                case 'I':
                    if (siguiente == 'V' || siguiente == 'X') {
                        numEntero -= 1;
                    } else {
                        numEntero += 1;
                    }
                    break;
                case 'V':
                    numEntero += 5;
                    break;
                case 'X':
                    if (siguiente == 'L' || siguiente == 'C') {
                        numEntero -= 10;
                    } else {
                        numEntero += 10;
                    }
                    break;
                case 'L':
                    numEntero += 50;
                    break;
                case 'C':
                    if (siguiente == 'D' || siguiente == 'M') {
                        numEntero -= 100;
                    } else {
                        numEntero += 100;
                    }
                    break;
                case 'D':
                    numEntero += 500;
                    break;
                default:
                    numEntero += 1000;
            }
        }
        return numEntero;
    }
}