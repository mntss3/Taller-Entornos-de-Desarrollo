package com.dam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestorPedidosTest {

    private GestorPedidos gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorPedidos();
    }

    @Test
    void testClienteNormalSinDescuentoVolumen() {
        // Escenario: 10 unidades a 10€, Cliente Normal (2)
        // Cálculo esperado: 10 * 10 = 100 base.
        // IVA (21%): 100 * 1.21 = 121.0
        
        double total = gestor.calcular(10, 10.0, 2);
        
        // Usamos un delta de 0.01 para comparar doubles por posibles decimales
        assertEquals(121.0, total, 0.01, "El cálculo para cliente normal falló");
    }

    @Test
    void testClienteVIPConDescuento() {
        // Escenario: 10 unidades a 10€, Cliente VIP (1)
        // Cálculo esperado: 10 * 10 = 100 base.
        // Descuento VIP (5%): 100 - 5 = 95.
        // IVA VIP (10%): 95 + 9.5 = 104.5
        double total = gestor.calcular(10, 10.0, 1);
        assertEquals(104.5, total, 0.01, "El cálculo para cliente VIP falló");

    }

    @Test
    void testClienteNuevoConRecargo() {
        // Escenario: 10 unidades a 10€, Cliente Nuevo (3)
        // Cálculo esperado: 10 * 10 = 100 base.
        // Recargo fijo: 100 + 2.5 = 102.5
        // IVA (21%): 102.5 * 1.21 = 124.025
        double total = gestor.calcular(10, 10.0, 3);
        assertEquals(124.025, total, 0.01, "El cálculo para cliente nuevo falló");
    }

    @Test
    void testDescuentoPorVolumen() {
        // Escenario: 51 unidades (Mayor a 50) a 10€, Cliente Normal (2)
        // Cálculo esperado: 51 * 10 = 510 base.
        // Descuento volumen (10%): 510 * 0.90 = 459.
        // IVA (21%): 459 * 1.21 = 555.39
        double total = gestor.calcular(51, 10.0, 2);
        assertEquals(555.39, total, 0.01, "El cálculo para descuento volumen falló");
    }

    @Test
    void testValidacionEntradaNegativa() {
        // Escenario: Cantidad negativa
        
        // El código devuelve -1 en caso de error
        double total = gestor.calcular(-10, 10.0, 3);
        assertEquals(-1, total, 0.01, "El cálculo para entrada negativa falló");
    }

    @Test
    void testTipoClienteDesconocido() {
        // Escenario: Tipo de cliente 99 (no existe)
        
        // El código devuelve 0 si no conoce el cliente
        double total = gestor.calcular(10, 10.0, 99);
        assertEquals(100, total, 0.01, "El cálculo para 99 clientes falló");
    }

    @Test
    void testPrecioNegativo() {
        // Escenario: Precio negativo
        double total = gestor.calcular(10, -10.0, 2);
        assertEquals(-1, total, 0.01, "El cálculo para precio negativo falló");
    }

    @Test
    void testPrecioCero() {
        // Escenario: Precio cero
        double total = gestor.calcular(10, 0.0, 2);
        assertEquals(-1, total, 0.01, "El cálculo para precio 0 falló");
    }

    @Test
    void testCantidadCero() {
        // Escenario: Cantidad cero
        double total = gestor.calcular(0, 10.0, 2);
        assertEquals(-1, total, 0.01, "El cálculo para cantidad 0 falló");
    }

    @Test
    void testCasoBordeExactamente50Unidades() {
        // Escenario: Exactamente 50 unidades (límite inferior para descuento)
        // 50 unidades NO deberían tener descuento por volumen
        // Cálculo: 50 * 10 = 500
        // IVA (21%): 500 * 1.21 = 605.0
        double total = gestor.calcular(50, 10.0, 2);
        assertEquals(605.0, total, 0.01, "El cálculo para descuento  falló");
    }

    @Test
    void testCasoBorde51Unidades() {
        // Escenario: 51 unidades (justo por encima del límite)
        // Ya está probado en testDescuentoPorVolumen, pero lo incluimos por completitud
        // Cálculo: 51 * 10 = 510 base
        // Descuento volumen (10%): 510 * 0.90 = 459
        // IVA (21%): 459 * 1.21 = 555.39
        double total = gestor.calcular(51, 10.0, 2);
        assertEquals(555.39, total, 0.01, "El cálculo para descuento 51 falló");
    }

    @Test
    void testClienteVIPConDescuentoPorVolumen() {
        // Escenario: Cliente VIP con más de 50 unidades
        // Cálculo: 60 * 10 = 600 base
        // Descuento volumen (10%): 600 * 0.90 = 540
        // Descuento VIP (5%): 540 * 0.95 = 513
        // IVA VIP (10%): 513 * 1.10 = 564.3
        double total = gestor.calcular(60, 10.0, 1);
        assertEquals(564.3, total, 0.01, "El cálculo para cliente VIP con descuento falló");
    }

    @Test
    void testClienteNuevoConDescuentoPorVolumen() {
        // Escenario: Cliente Nuevo con más de 50 unidades
        // Cálculo: 60 * 10 = 600 base
        // Descuento volumen (10%): 600 * 0.90 = 540
        // Recargo fijo: 540 + 2.5 = 542.5
        // IVA (21%): 542.5 * 1.21 = 656.425
        double total = gestor.calcular(60, 10.0, 3);
        assertEquals(656.425, total, 0.01, "El cálculo para cliente nuevo con descuento falló");
    }

    @Test
    void testValoresExtremosCantidadGrande() {
        // Escenario: Cantidad muy grande
        // Cálculo: 1000 * 5 = 5000 base
        // Descuento volumen (10%): 5000 * 0.90 = 4500
        // IVA (21%): 4500 * 1.21 = 5445.0
        double total = gestor.calcular(1000, 5.0, 2);
        assertEquals(5445.0, total, 0.01, "El cálculo para megacantidad falló");
    }

    @Test
    void testValoresExtremosPrecioPequeno() {
        // Escenario: Precio muy pequeño
        // Cálculo: 10 * 0.01 = 0.1 base
        // IVA (21%): 0.1 * 1.21 = 0.121
        double total = gestor.calcular(10, 0.01, 2);
        assertEquals(0.121, total, 0.01, "El cálculo para precio muy pequeño falló");
    }
}
 