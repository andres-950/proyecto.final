package proyectoFinal;

import java.util.Scanner;

public class TrainTicketSystem {

    static Scanner sc = new Scanner(System.in);

    // Tablas en memoria: cada fila es un registro, cada columna un dato
    static String[][] usuarios = new String[20][5];
    static String[][] cajeros = new String[20][5];
    static String[][] trenes = new String[3][3];
    static String[][] tickets = new String[20][7];
    static String[][] ventas = new String[20][5];

    // Contadores para saber cuántos registros hay realmente
    static int totalUsuarios = 0;
    static int totalCajeros = 0;
    static int totalTickets = 0;
    static int totalVentas = 0;
    static int cajeroActual = -1; 

    public static void main(String[] args) {
        cargarTrenes();
        menuPrincipal();
    }

    // Trenes fijos del sistema, no se crean desde el menú
    public static void cargarTrenes() {
        trenes[0][0] = "1";
        trenes[0][1] = "Tren Santa Fe";
        trenes[0][2] = "4";
        trenes[1][0] = "2";
        trenes[1][1] = "Tren Cisneros";
        trenes[1][2] = "4";
        trenes[2][0] = "3";
        trenes[2][1] = "Tren Puerto Berrio";
        trenes[2][2] = "4";
    }

    public static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("FERROCARRILES DE ANTIOQUIA");
            System.out.println("1. Registrar cajero");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    crearCajero();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 3);
    }

    // Valida id + contraseña y guarda quién está logueado
    public static void login() {
        System.out.println("Ingrese id del cajero");
        String id = sc.nextLine();
        System.out.println("Ingrese contrasena");
        String clave = sc.nextLine();
        for (int i = 0; i < totalCajeros; i++) {
            if (cajeros[i][0].equals(id) && cajeros[i][4].equals(clave)) {
                cajeroActual = i;
                System.out.println("Bienvenido " + cajeros[i][1]);
                dashboard();
                return;
            }
        }
        System.out.println("Id o contrasena incorrectos");
    }

    // Menú principal después de loguearse
    public static void dashboard() {
        int opcion;
        do {
            System.out.println("DASHBOARD CAJERO");
            System.out.println("1. Usuarios");
            System.out.println("2. Trenes");
            System.out.println("3. Tickets");
            System.out.println("4. Ventas");
            System.out.println("5. Cerrar sesion");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuTren();
                    break;
                case 3:
                    menuTickets();
                    break;
                case 4:
                    menuVenta();
                    break;
                case 5:
                    cajeroActual = -1;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 5);
    }

    public static void menuUsuario() {
        int opcion;
        do {
            System.out.println("MENU USUARIOS");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("6. Volver");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    actualizarDatos();
                    break;
                case 3:
                    consultarUsuarioPorId();
                    break;
                case 4:
                    consultarUsuarios();
                    break;
                case 5:
                    eliminarUsuario();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 6);
    }

    public static void crearUsuario() {
        if (totalUsuarios >= 20) {
            System.out.println("No hay espacio");
            return;
        }
        System.out.println("Ingrese id");
        usuarios[totalUsuarios][0] = sc.nextLine();
        System.out.println("Ingrese nombre");
        usuarios[totalUsuarios][1] = sc.nextLine();
        System.out.println("Ingrese apellido");
        usuarios[totalUsuarios][2] = sc.nextLine();
        System.out.println("Ingrese origen (Santa Fe, Cisneros, Puerto Berrio)");
        usuarios[totalUsuarios][3] = sc.nextLine();
        System.out.println("Ingrese destino (Santa Fe, Cisneros, Puerto Berrio)");
        usuarios[totalUsuarios][4] = sc.nextLine();
        totalUsuarios++;
        System.out.println("Usuario registrado");
    }

    public static void consultarUsuarios() {
        for (int i = 0; i < totalUsuarios; i++) {
            System.out.println("Id: " + usuarios[i][0] + " | Nombre: " + usuarios[i][1] + " | Apellido: " + usuarios[i][2] + " | Origen: " + usuarios[i][3] + " | Destino: " + usuarios[i][4]);
        }
        if (totalUsuarios == 0) {
            System.out.println("No hay usuarios");
        }
    }

    // Búsqueda lineal por id
    public static void consultarUsuarioPorId() {
        System.out.println("Indique el id del usuario a consultar");
        String id = sc.nextLine();
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i][0].equals(id)) {
                System.out.println("Id: " + usuarios[i][0] + " | Nombre: " + usuarios[i][1] + " | Apellido: " + usuarios[i][2] + " | Origen: " + usuarios[i][3] + " | Destino: " + usuarios[i][4]);
                return;
            }
        }
        System.out.println("Usuario no encontrado");
    }

    public static void actualizarDatos() {
        System.out.println("Ingrese el id del usuario a actualizar");
        String id = sc.nextLine();
        int i = -1;
        for (int j = 0; j < totalUsuarios; j++) {
            if (usuarios[j][0].equals(id)) {
                i = j;
                break;
            }
        }
        if (i == -1) {
            System.out.println("Usuario no existe");
            return;
        }
        System.out.println("Seleccione el dato a actualizar 1. Nombre 2. Apellido 3. Origen 4. Destino");
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("Actualizar nombre");
                usuarios[i][1] = sc.nextLine();
                break;
            case 2:
                System.out.println("Actualizar apellido");
                usuarios[i][2] = sc.nextLine();
                break;
            case 3:
                System.out.println("Actualizar origen");
                usuarios[i][3] = sc.nextLine();
                break;
            case 4:
                System.out.println("Actualizar destino");
                usuarios[i][4] = sc.nextLine();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
        System.out.println("Usuario actualizado");
    }

    // Elimina corriendo los registros hacia adelante para no dejar huecos
    public static void eliminarUsuario() {
        System.out.println("Ingrese el id del usuario a eliminar");
        String id = sc.nextLine();
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i][0].equals(id)) {
                for (int j = i; j < totalUsuarios - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                totalUsuarios--;
                System.out.println("Usuario eliminado");
                return;
            }
        }
        System.out.println("Usuario no encontrado");
    }

    public static void menuTren() {
        int opcion;
        do {
            System.out.println("MENU TRENES");
            System.out.println("1. Consultar por id");
            System.out.println("2. Consultar todos");
            System.out.println("3. Volver");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    consultarTrenPorId();
                    break;
                case 2:
                    consultarTrenes();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 3);
    }

    public static void consultarTrenes() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Maquina: " + trenes[i][0] + " | Modelo: " + trenes[i][1] + " | Capacidad: " + trenes[i][2]);
        }
    }

    public static void consultarTrenPorId() {
        System.out.println("Ingrese numero de maquina");
        String id = sc.nextLine();
        for (int i = 0; i < 3; i++) {
            if (trenes[i][0].equals(id)) {
                System.out.println("Maquina: " + trenes[i][0] + " | Modelo: " + trenes[i][1] + " | Capacidad: " + trenes[i][2]);
                return;
            }
        }
        System.out.println("Tren no encontrado");
    }

    public static void crearCajero() {
        if (totalCajeros >= 20) {
            System.out.println("No hay espacio");
            return;
        }
        System.out.println("Ingrese id");
        cajeros[totalCajeros][0] = sc.nextLine();
        System.out.println("Ingrese nombre");
        cajeros[totalCajeros][1] = sc.nextLine();
        System.out.println("Ingrese apellido");
        cajeros[totalCajeros][2] = sc.nextLine();
        System.out.println("Ingrese rol");
        cajeros[totalCajeros][3] = sc.nextLine();
        System.out.println("Ingrese contrasena");
        cajeros[totalCajeros][4] = sc.nextLine();
        totalCajeros++;
        System.out.println("Cajero registrado");
    }

    public static void menuTickets() {
        int opcion;
        do {
            System.out.println("MENU TICKETS");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("6. Volver");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    crearTicket();
                    break;
                case 2:
                    actualizarTicket();
                    break;
                case 3:
                    consultarTicketPorId();
                    break;
                case 4:
                    consultarTickets();
                    break;
                case 5:
                    eliminarTicket();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 6);
    }

    // El ticket relaciona usuario + tren + asiento
    public static void crearTicket() {
        if (totalTickets >= 20) {
            System.out.println("No hay espacio");
            return;
        }
        System.out.println("Ingrese id del ticket");
        tickets[totalTickets][0] = sc.nextLine();
        System.out.println("Ingrese id del usuario");
        tickets[totalTickets][1] = sc.nextLine();
        System.out.println("Ingrese numero de maquina del tren (1, 2 o 3)");
        tickets[totalTickets][2] = sc.nextLine();
        System.out.println("Ingrese asiento (1-4)");
        tickets[totalTickets][3] = sc.nextLine();
        System.out.println("Ingrese origen");
        tickets[totalTickets][4] = sc.nextLine();
        System.out.println("Ingrese destino");
        tickets[totalTickets][5] = sc.nextLine();
        System.out.println("Ingrese precio");
        tickets[totalTickets][6] = sc.nextLine();
        totalTickets++;
        System.out.println("Ticket creado");
    }

    public static void consultarTickets() {
        for (int i = 0; i < totalTickets; i++) {
            System.out.println("Id: " + tickets[i][0] + " | Usuario: " + tickets[i][1] + " | Tren: " + tickets[i][2] + " | Asiento: " + tickets[i][3] + " | Origen: " + tickets[i][4] + " | Destino: " + tickets[i][5] + " | Precio: " + tickets[i][6]);
        }
        if (totalTickets == 0) {
            System.out.println("No hay tickets");
        }
    }

    public static void consultarTicketPorId() {
        System.out.println("Ingrese id del ticket");
        String id = sc.nextLine();
        for (int i = 0; i < totalTickets; i++) {
            if (tickets[i][0].equals(id)) {
                System.out.println("Id: " + tickets[i][0] + " | Usuario: " + tickets[i][1] + " | Tren: " + tickets[i][2] + " | Asiento: " + tickets[i][3] + " | Origen: " + tickets[i][4] + " | Destino: " + tickets[i][5] + " | Precio: " + tickets[i][6]);
                return;
            }
        }
        System.out.println("Ticket no encontrado");
    }

    public static void actualizarTicket() {
        System.out.println("Ingrese id del ticket a actualizar");
        String id = sc.nextLine();
        int i = -1;
        for (int j = 0; j < totalTickets; j++) {
            if (tickets[j][0].equals(id)) {
                i = j;
                break;
            }
        }
        if (i == -1) {
            System.out.println("Ticket no existe");
            return;
        }
        System.out.println("Seleccione 1. Usuario 2. Tren 3. Asiento 4. Origen 5. Destino 6. Precio");
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("Actualizar usuario");
                tickets[i][1] = sc.nextLine();
                break;
            case 2:
                System.out.println("Actualizar tren");
                tickets[i][2] = sc.nextLine();
                break;
            case 3:
                System.out.println("Actualizar asiento");
                tickets[i][3] = sc.nextLine();
                break;
            case 4:
                System.out.println("Actualizar origen");
                tickets[i][4] = sc.nextLine();
                break;
            case 5:
                System.out.println("Actualizar destino");
                tickets[i][5] = sc.nextLine();
                break;
            case 6:
                System.out.println("Actualizar precio");
                tickets[i][6] = sc.nextLine();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
        System.out.println("Ticket actualizado");
    }

    public static void eliminarTicket() {
        System.out.println("Ingrese id del ticket a eliminar");
        String id = sc.nextLine();
        for (int i = 0; i < totalTickets; i++) {
            if (tickets[i][0].equals(id)) {
                for (int j = i; j < totalTickets - 1; j++) {
                    tickets[j] = tickets[j + 1];
                }
                totalTickets--;
                System.out.println("Ticket eliminado");
                return;
            }
        }
        System.out.println("Ticket no encontrado");
    }

    public static void menuVenta() {
        int opcion;
        do {
            System.out.println("MENU VENTAS");
            System.out.println("1. Registrar");
            System.out.println("2. Actualizar");
            System.out.println("3. Consultar por id");
            System.out.println("4. Consultar todos");
            System.out.println("5. Eliminar");
            System.out.println("6. Volver");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    crearVenta();
                    break;
                case 2:
                    actualizarVenta();
                    break;
                case 3:
                    consultarVentaPorId();
                    break;
                case 4:
                    consultarVentas();
                    break;
                case 5:
                    eliminarVenta();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 6);
    }

    // El total se calcula solo: precio * cantidad
    public static void crearVenta() {
        if (totalVentas >= 20) {
            System.out.println("No hay espacio");
            return;
        }
        System.out.println("Ingrese id de la venta");
        ventas[totalVentas][0] = sc.nextLine();
        System.out.println("Ingrese id del ticket");
        ventas[totalVentas][1] = sc.nextLine();
        System.out.println("Ingrese precio unitario");
        String precio = sc.nextLine();
        ventas[totalVentas][2] = precio;
        System.out.println("Ingrese cantidad");
        String cantidad = sc.nextLine();
        ventas[totalVentas][3] = cantidad;
        double total = Double.parseDouble(precio) * Integer.parseInt(cantidad);
        ventas[totalVentas][4] = String.valueOf(total);
        totalVentas++;
        System.out.println("Venta registrada. Total: " + total);
    }

    public static void consultarVentas() {
        for (int i = 0; i < totalVentas; i++) {
            System.out.println("Id venta: " + ventas[i][0] + " | Id ticket: " + ventas[i][1] + " | Precio: " + ventas[i][2] + " | Cantidad: " + ventas[i][3] + " | Total: " + ventas[i][4]);
        }
        if (totalVentas == 0) {
            System.out.println("No hay ventas");
        }
    }

    public static void consultarVentaPorId() {
        System.out.println("Ingrese id de la venta");
        String id = sc.nextLine();
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i][0].equals(id)) {
                System.out.println("Id venta: " + ventas[i][0] + " | Id ticket: " + ventas[i][1] + " | Precio: " + ventas[i][2] + " | Cantidad: " + ventas[i][3] + " | Total: " + ventas[i][4]);
                return;
            }
        }
        System.out.println("Venta no encontrada");
    }

    // Al cambiar precio o cantidad, recalcula el total
    public static void actualizarVenta() {
        System.out.println("Ingrese id de la venta a actualizar");
        String id = sc.nextLine();
        int i = -1;
        for (int j = 0; j < totalVentas; j++) {
            if (ventas[j][0].equals(id)) {
                i = j;
                break;
            }
        }
        if (i == -1) {
            System.out.println("Venta no existe");
            return;
        }
        System.out.println("Seleccione 1. Ticket 2. Precio 3. Cantidad");
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("Actualizar id ticket");
                ventas[i][1] = sc.nextLine();
                break;
            case 2:
                System.out.println("Actualizar precio");
                ventas[i][2] = sc.nextLine();
                break;
            case 3:
                System.out.println("Actualizar cantidad");
                ventas[i][3] = sc.nextLine();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
        double total = Double.parseDouble(ventas[i][2]) * Integer.parseInt(ventas[i][3]);
        ventas[i][4] = String.valueOf(total);
        System.out.println("Venta actualizada. Total: " + total);
    }

    public static void eliminarVenta() {
        System.out.println("Ingrese id de la venta a eliminar");
        String id = sc.nextLine();
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i][0].equals(id)) {
                for (int j = i; j < totalVentas - 1; j++) {
                    ventas[j] = ventas[j + 1];
                }
                totalVentas--;
                System.out.println("Venta eliminada");
                return;
            }
        }
        System.out.println("Venta no encontrada");
    }
}