package com.dam;

public class GestorPedidos {
	
	// Porcentajes y límites de negocio
    private static final double generalIVA = 1.21; // precio con IVA general (21%)
    private static final double vipIVA= 1.10;  // precio con IVA reducido para VIP (10%)
    private static final double vipDiscount = 0.95;  // precio tras aplicar 5% de descuento VIP
    private static final double volumeDiscount = 0.90; // precio tras aplicar 10% de descuento por volumen
    private static final int limitVolumen = 50; // a partir de esta cantidad hay descuento por volumen
    private static final double clienteNuevoRecargo = 2.5; // recargo fijo para clientes nuevos


    /**
     * Calcula el importe final de un pedido en función de la cantidad,
     * el precio unitario y el tipo de cliente.
     *
     * @param quantity Cantidad de productos
     * @param price Precio unitario
     * @param type Tipo de cliente (1 = VIP, 2 = Normal, 3 = Nuevo)
     * @return total precio final
     */
    // El método calcular delega el trabajo en los métodos subtotal y descuentoTipoCliente
    public double calcular(int quantity, double price, int type) {
        double subtotal = subtotal(quantity, price);
        return descuentoTpioCliente(type, subtotal);
    }

    // 1. Calcula el subtotal aplicando, si corresponde, el descuento por volumen
    public double subtotal(int quantity, double price) {
        //  Comprobación de que cantidad y precio tienen valores válidos
        if (quantity <= 0 || price <= 0) {
            return -1;
        }

        // Aplicar descuento por volumen
        if (quantity > limitVolumen) {
            return quantity * price * volumeDiscount;
        } else {
            return quantity * price;
        }
    }

    // 2: Calcula descuento según el tipo de cliente
    public double descuentoTpioCliente(int type, double subtotal) {
        if (subtotal == -1) return -1; 

        double total = subtotal;

        switch (type) {
            case 1:
            	 // Cliente VIP
                total = total * vipDiscount;
                total = total * vipIVA;
                break;
            case 2:
            	// Cliente normal
                total = total * generalIVA;
                break;
            case 3:
            	 // Cliente nuevo
                total = (total + clienteNuevoRecargo) * generalIVA;
                break;
            default:
                System.out.println("Número no reconocido.");
                break;
        }
        
        return total;
    }
}