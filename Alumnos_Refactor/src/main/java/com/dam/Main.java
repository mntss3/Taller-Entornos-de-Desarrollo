package com.dam;

public class Main {

    public static void main(String[] args) {
        GestorPedidos gestor = new GestorPedidos();
        
        System.out.println("=== Sistema de Gestión de Pedidos ===\n");
        
        // Ejemplo 1: Cliente VIP con pedido grande
        System.out.println("Ejemplo 1: Cliente VIP - 100 unidades a 10€");
        double precioVIP = gestor.calcular(100, 10.0, 1);
        System.out.println("Precio final: " + precioVIP + "€\n");
        
        // Ejemplo 2: Cliente Normal con pedido pequeño
        System.out.println("Ejemplo 2: Cliente Normal - 20 unidades a 15€");
        double precioNormal = gestor.calcular(20, 15.0, 2);
        System.out.println("Precio final: " + precioNormal + "€\n");
        
        // Ejemplo 3: Cliente Nuevo con pedido mediano
        System.out.println("Ejemplo 3: Cliente Nuevo - 30 unidades a 12€");
        double precioNuevo = gestor.calcular(30, 12.0, 3);
        System.out.println("Precio final: " + precioNuevo + "€\n");
        
        // Ejemplo 4: Caso de error (cantidad negativa)
        System.out.println("Ejemplo 4: Caso de error - cantidad negativa");
        double precioError = gestor.calcular(-5, 10.0, 2);
        if (precioError == -1) {
            System.out.println("Error: Datos de entrada inválidos\n");
        }
        
        System.out.println("=== Fin del programa ===");
    }
}
