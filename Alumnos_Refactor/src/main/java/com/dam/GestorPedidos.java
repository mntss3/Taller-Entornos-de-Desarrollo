package com.dam;

public class GestorPedidos {

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

        switch (type) {
        case 1:
            total = total - (total * 0.05);
            total = total + (total * 0.10);
            break;
        case 2:
            total = total + (total * 0.21);
            break;
        case 3:
            total = total + 2.5;
            total = total + (total * 0.21);
            break;
        default:
        	 System.out.println("Número no reconocido.");

            return total;
        }
    return total;
    }
}