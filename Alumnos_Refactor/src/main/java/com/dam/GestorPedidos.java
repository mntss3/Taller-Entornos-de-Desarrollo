package com.dam;

import java.util.logging.Logger;

public class GestorPedidos {

    private static final Logger LOGGER = Logger.getLogger(GestorPedidos.class.getName());

    /**
     * Calcula el precio final pero tiene muy malas prácticas.
     *
     * @param c Cantidad de productos
     * @param p Precio unitario
     * @param t Tipo de cliente (1 = VIP, 2 = Normal, 3 = Nuevo)
     * @return precio final
     */
    public double calcular(int c, double p, int t) {
        double res = 0;

        // Validacion basica
        if (c <= 0 || p <= 0) {
            return -1; // Codigo de error
        }

        // Logica compleja y sucia
        if (c > 50) {
            // Descuento por volumen grande
            res = c * p * 0.90; 
        } else {
            res = c * p;
        }

        if (t == 1) {
            // Cliente VIP: 5% extra descuento
            res = res - (res * 0.05);
            // IVA VIP (reducido por alguna razon ficticia)
            res = res + (res * 0.10);
            // Registro opcional para depuración en lugar de imprimir en consola
            LOGGER.fine("Calculando para cliente VIP...");
        } else if (t == 2) {
             // Cliente Normal: sin descuento extra
             // IVA Normal
             res = res + (res * 0.21);
        } else if (t == 3) {
            // Cliente Nuevo: recargo de gestion
            res = res + 2.5; // Recargo fijo
            // IVA Normal
            res = res + (res * 0.21);
        } else {
            // Tipo desconocido
            return 0;
        }

        return res;
    }
}