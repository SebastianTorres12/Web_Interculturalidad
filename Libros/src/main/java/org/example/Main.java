package org.example;

import model.Usuario;
import model.Vuelo;
import service.AuthService;
import service.CompraService;
import service.VueloService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        VueloService vueloService = new VueloService();
        CompraService compraService = new CompraService();
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Viajecitos SA ✈️ Cliente Consola =====");
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        Usuario usuario = authService.login(correo, password);
        if (usuario == null) {
            System.out.println("❌ Login fallido.");
            return;
        }

        System.out.println("✅ Bienvenido/a " + usuario.getNombre());

        while (true) {
            System.out.println("\n1. Buscar vuelos");
            System.out.println("2. Salir");
            System.out.print("Opción: ");
            String entrada = sc.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida.");
                continue;
            }

            if (opcion == 1) {
                System.out.print("Ciudad Origen: ");
                String origen = sc.nextLine();
                System.out.print("Ciudad Destino: ");
                String destino = sc.nextLine();
                System.out.print("Fecha (YYYY-MM-DD): ");
                String fecha = sc.nextLine();

                List<Vuelo> vuelos = vueloService.buscar(origen, destino, fecha);
                if (vuelos.isEmpty()) {
                    System.out.println("❌ No hay vuelos disponibles.");
                    continue;
                }

                vuelos.sort(Comparator.comparingDouble(Vuelo::getValor).reversed());

                // Mostrar solo el más caro primero
                Vuelo masCaro = vuelos.get(0);
                System.out.println("\n✈️ Vuelo más caro encontrado:");
                System.out.printf("%s -> %s | %s %s | $%.2f\n",
                        masCaro.getCiudadOrigen(), masCaro.getCiudadDestino(),
                        masCaro.getFecha(), masCaro.getHoraSalida(), masCaro.getValor());

                System.out.print("¿Deseas comprar este vuelo? (s/n): ");
                String respuesta = sc.nextLine().trim().toLowerCase();

                if (respuesta.equals("s")) {
                    compraService.realizarCompra(usuario.getNombre(), masCaro);
                    continue;
                }

                // Mostrar todos los vuelos si el usuario no quiso el más caro
                System.out.println("\n📋 Otros vuelos disponibles:");
                for (int i = 0; i < vuelos.size(); i++) {
                    Vuelo v = vuelos.get(i);
                    System.out.printf("%d. %s -> %s | %s %s | $%.2f\n",
                            i + 1,
                            v.getCiudadOrigen(), v.getCiudadDestino(),
                            v.getFecha(), v.getHoraSalida(), v.getValor());
                }

                System.out.print("Seleccione un vuelo: ");
                int indice;
                try {
                    indice = Integer.parseInt(sc.nextLine()) - 1;
                    if (indice < 0 || indice >= vuelos.size()) {
                        System.out.println("Selección inválida.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida.");
                    continue;
                }

                Vuelo seleccionado = vuelos.get(indice);
                compraService.realizarCompra(usuario.getNombre(), seleccionado);
                System.out.println("✅ A nombre de " + usuario.getNombre());
            } else if (opcion == 2) {
                System.out.println("👋 Gracias por usar Viajecitos SA");
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
}
