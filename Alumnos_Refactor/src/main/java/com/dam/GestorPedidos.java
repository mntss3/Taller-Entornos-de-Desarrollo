package com.dam;

import java.util.logging.Logger;

public class GestorPedidos {

    private static final Logger LOGGER = Logger.getLogger(GestorPedidos.class.getName());

    /**
     * Calcula el precio final pero tiene muy malas prácticas.
     *
     * @param quantity Cantidad de productos
     * @param price Precio unitario
     * @param type Tipo de cliente (1 = VIP, 2 = Normal, 3 = Nuevo)
     * @return total precio final
     */
    public double calcular(int quantity, double price, int type) {
        double total = 0;

        // Validacion basica
        if (quantity <= 0 || price <= 0) {
            return -1; // Codigo de error
        }

        // Logica compleja y sucia
        if (quantity > 50) {
            // Descuento por volumen grande
            total = quantity * price * 0.90; 
        } else {
            total = quantity * price;
        }

        if (type == 1) {
            // Cliente VIP: 5% extra descuento
        	total = total - (total * 0.05);
            // IVA VIP (reducido por alguna razon ficticia)
        	total = total + (total * 0.10);
            // Registro opcional para depuración en lugar de imprimir en consola
            LOGGER.fine("Calculando para cliente VIP...");
        } else if (type == 2) {
             // Cliente Normal: sin descuento extra
             // IVA Normal
             total = total + (total * 0.21);
        } else if (type == 3) {
            // Cliente Nuevo: recargo de gestion
            total = total + 2.5; // Recargo fijo
            // IVA Normal
            total = total + (total * 0.21);
        } else {
            // Tipo desconocido
            return 0;
        }

        return total;
    }
}