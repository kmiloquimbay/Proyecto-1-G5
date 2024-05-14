package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.compraYsubasta.Oferta;
import galeria.compraYsubasta.Subasta;
import galeria.inventarioYpiezas.Fotografia;
import galeria.inventarioYpiezas.Inventario;
import galeria.inventarioYpiezas.Pintura;
import galeria.inventarioYpiezas.Video;
import usuarios.AdministradorGaleria;
import usuarios.Cajero;
import usuarios.Comprador;
import usuarios.ControladorUsuarios;
import usuarios.OperadorSubasta;
import usuarios.Propietario;

class Pruebas {
    //Setup para tests de los reqs
    //Galeria
    static Fotografia foto1= new Fotografia("La nina y el buitre","Kevin Carter", 1993, "Sudán","20-03-2024", true, 300,  false, "10", "200");
    static Fotografia foto2= new Fotografia("La torre de Piza", "Anónimo", 1355, "Italia","20-12-2024", false, 500, false, "26", "300");
    static Video video1= new Video("La Vie", "Edith Piaf", 1983, "Francia","14-11-2024", true, false, 0,  "40", "200");
    static Pintura pintura1=new Pintura("La Flor", "Juan Antonio", 1964, "Italia","20-12-2025", true, true, 0,  77, 53, "Oleo");
    static Inventario inventario1= new Inventario();
    static ControladorUsuarios controlador= new ControladorUsuarios();
    static Galeria galeriaTests = new Galeria(inventario1,controlador);
    static Pintura pinturaAgregar=new Pintura("Mona Lisa", "Leonardo da Vinci", 1506, "Italia","20-10-2024", true, false, 1000, 77, 53, "Oleo");
    
    //Usuarios
    static Comprador comprador= new Comprador("LuisP", "12345", "Luis Perez","3456289290", 1000000,galeriaTests.getInventario().getPiezasDisponibleVenta(), "547293");
    static Propietario propietario= new Propietario("LuisP", "12345", "Luis Perez", "3456289290","547293");
    static AdministradorGaleria admin= new AdministradorGaleria("fabio24", "1226745", "Administrador",galeriaTests, "562901");
    static Cajero cajero= new  Cajero("juanito2", "762598", "Cajero",galeriaTests, "4439035");
    static OperadorSubasta operador= new OperadorSubasta("andresP", "12235345", "Operador",galeriaTests, "653907");

    //Subastas y Compras
    static Oferta oferta1= new Oferta(20000, comprador);
    static Oferta oferta2= new Oferta(40000, comprador);
    static Oferta ofertaRecibir= new Oferta(25000, comprador);
    static Subasta subasta1= new Subasta("6748899",39034,15000,video1);
    static Compra compra1= new Compra("567890", 20000, "tarjeta", "La Vie","547293");
    static Compra compra2= new Compra("587650", 70000, "tarjeta", "La Flor","547293");

    
    void setUp(){
        galeriaTests.getInventario().guardarEnBodega(foto1);
        galeriaTests.getInventario().guardarEnBodega(video1);
        galeriaTests.getInventario().guardarEnBodega(pintura1);
        galeriaTests.getInventario().ponerEnDisponibles(foto1);
        galeriaTests.getInventario().ponerEnDisponibles(video1);
        galeriaTests.getInventario().ponerEnDisponibles(pintura1);
        galeriaTests.getControladorUsuarios().agregarComprador(comprador);
        galeriaTests.getControladorUsuarios().agregarPropietario(propietario);
        galeriaTests.getControladorUsuarios().agregarEmpleado(admin);
        galeriaTests.getControladorUsuarios().agregarEmpleado(cajero);
        galeriaTests.getControladorUsuarios().agregarEmpleado(operador);
        subasta1.agregarOferta(oferta1);
        subasta1.agregarOferta(oferta2);
        galeriaTests.agregarSubasta(subasta1);
        propietario.agregarPieza(foto1);
        propietario.agregarPieza(video1);
        propietario.agregarPieza(pintura1);
        propietario.pasarAPasadas(pintura1);
        galeriaTests.agregarCompra(compra1);
        galeriaTests.agregarCompra(compra2);
        comprador.agregarCompra(compra1);
        comprador.agregarCompra(compra2);
        galeriaTests.setAdministradorGaleria(admin);
    }
    @BeforeAll
    void setUpAll(){
        setUp();
    }
    
    //testUsuarios
    @Test
    void testAgregarComprador(){
        galeriaTests.getControladorUsuarios().agregarComprador(comprador);
        assertTrue(galeriaTests.getControladorUsuarios().obtenerComprador("547293").equals(comprador));
    }
    @Test
    void testAgregarPropietario(){
        galeriaTests.getControladorUsuarios().agregarPropietario(propietario);
        assertTrue(galeriaTests.getControladorUsuarios().obtenerPropietario("547293").equals(propietario));
    }
    @Test
    void testAgregarEmpleado(){
        galeriaTests.getControladorUsuarios().agregarEmpleado(admin);
        assertTrue(galeriaTests.getControladorUsuarios().obtenerEmpleado("562901").equals(admin));
    }
    @Test
    void testAgregarSubasta(){
        galeriaTests.agregarSubasta(subasta1);
        assertTrue(galeriaTests.getSubastas().containsKey("6748899"));
    }
    @Test
    void testAgregarCompraGaleria(){
        galeriaTests.agregarCompra(compra1);
        assertTrue(galeriaTests.getCompras().containsKey("567890"));
    }
    @Test
    void testObtenerEmpleado(){
        assertTrue(galeriaTests.getControladorUsuarios().obtenerEmpleado("562901").equals(admin));
    }
    @Test
    void testObtenerComprador(){
        assertTrue(galeriaTests.getControladorUsuarios().obtenerComprador("547293").equals(comprador));
    }
    @Test
    void testObtenerPropietario(){
        assertTrue(galeriaTests.getControladorUsuarios().obtenerPropietario("547293").equals(propietario));
    }
    @Test
    void testCrearComprador(){
        Comprador compradorNuevo= galeriaTests.getControladorUsuarios().crearComprador("Comprador1", "123", "comp","1234567890", 1000000);
        assertTrue(compradorNuevo.equals(comprador));
    }
    @Test
    void testCrearPropietario(){
        Propietario propietarioNuevo= galeriaTests.getControladorUsuarios().crearPropietario("Propietario1", "123", "prop", "1234567890");
        assertTrue(propietarioNuevo.equals(propietario));
    }

    //testGaleria
    @Test
    void testRegistrarIngresoPieza(){
        admin.registrarIngresoPieza(pintura1);
        assertTrue(galeriaTests.getInventario().getPiezasEnBodega().contains(pintura1));
    }    
    @Test
    void testVerHistorialArtista(){
        galeriaTests.verHistorialArtista("Juan Antonio");
        assertTrue(galeriaTests.getInventario().getPiezasPasadas().contains(pintura1));
    }
    @Test
    void testVerHistorialPieza(){
        galeriaTests.verHistorialPieza("La Flor");
        assertTrue(galeriaTests.getCompras().containsKey("587650"));
    }
    @Test
    void testVerHistorialPiezaNoExiste(){
        galeriaTests.verHistorialPieza("La Flor2");
        assertTrue(galeriaTests.getCompras().containsKey("587650"));
    }
    @Test
    void testVerHistorialArtistaNoExiste(){
        galeriaTests.verHistorialArtista("Juan Antonio2");
        assertTrue(galeriaTests.getInventario().getPiezasPasadas().contains(pintura1));
    }
    @Test
    void testEncontarCompra(){
        assertTrue(galeriaTests.encontrarCompra("587650").equals(compra2));
    }

    //testPieza
    @Test
    void testAgregarPieza(){
        propietario.agregarPieza(pinturaAgregar);
        assertTrue(propietario.getMisPiezasActuales().contains(pinturaAgregar));
    }
    @Test
    void testPasarAPasadas(){
        propietario.pasarAPasadas(pinturaAgregar);
        assertTrue(propietario.getMisPiezasActuales().contains(pinturaAgregar));
    }

    //testSubasta
    @Test
    void testAgregarOferta(){
        subasta1.agregarOferta(ofertaRecibir);
        assertTrue(subasta1.getOfertas().contains(ofertaRecibir));
    }

    //testCompra
    @Test
    void testVerificarVentaValorFijo(){
        assertTrue(compra1.verificarVentaValorFijo(foto1, 20000));
    }
    @Test
    void testVerificarVentaValorFijoNoCumple(){
        assertTrue(!compra1.verificarVentaValorFijo(foto1, 10000));
    }
    

    //testAdmin

    @Test
    void testRegistrarIngresoPiezaAdmin(){
        admin.registrarIngresoPieza(pintura1);
        assertTrue(galeriaTests.getInventario().getPiezasEnBodega().contains(pintura1));
    }
    @Test
    void testConfirmarVentaAdmin_Integracion(){
        admin.confirmarVenta(compra1, foto1, "547293");
        assertEquals("Venta confirmada", admin.confirmarVenta(compra1, foto1, "547293"));
        assertTrue(galeriaTests.getInventario().getPiezasPasadas().contains(foto1));
        assertTrue(galeriaTests.getControladorUsuarios().obtenerComprador("547293").getmisCompras().contains(compra1));
        assertFalse(galeriaTests.getInventario().getPiezasDisponibleVenta().contains(foto1));   
        
    }
    @Test
    void testVerificarComprador(){
        assertTrue(admin.verificarComprador("547293"));

    }

    @Test
    void testDevolucionPiezaAdmin_Integracion(){
        admin.devolucionPieza(pintura1, "547293");
        assertTrue(galeriaTests.getInventario().getPiezasEnBodega().contains(pintura1));
        assertTrue(!galeriaTests.getControladorUsuarios().obtenerPropietario("547293").getMisPiezasActuales().contains(pintura1));
    }
    @Test
    void testBloquearPieza(){
        admin.bloquearPieza("La Flor");
        assertTrue(pintura1.isBloqueada());
    }
    @Test
    void testDesbloquearPieza(){
        admin.desbloquearPieza("La Flor");
        assertTrue(!pintura1.isBloqueada());
    }
    @Test
    void testAumentarLimite(){
        admin.aumentarLimite("547293", 1000000);
        assertTrue(comprador.getLimiteCompras()==2000000);
    }
    
    //testCajero
    @Test
    void testRegistrarCompra(){
        admin.confirmarVenta(compra1, foto1, "547293");
        assertEquals("Pago registrado", cajero.registrarPago(compra1, foto1, "547293"));
    }

    @Test
    void testEntregarPieza_Integracion(){
        admin.confirmarVenta(compra1, foto1, "547293");
        assertEquals("547293", cajero.entregarPieza(foto1, "547293"));
        assertTrue(galeriaTests.getInventario().getPiezasEnExhibicion().contains(foto1));
        assertTrue(!galeriaTests.getInventario().getPiezasEnBodega().contains(foto1));
        assertTrue(galeriaTests.getControladorUsuarios().obtenerPropietario("547293").getMisPiezasActuales().contains(foto1));
    }
    
    //testOperador
    @Test
    void testRecibirRegistrarOferta(){
        assertTrue(operador.recibirRegistrarOferta(ofertaRecibir, "6748899").equals("Oferta registrada"));

    }
    @Test
    void testEvaluarOferta(){
        assertTrue(operador.evaluarOferta(ofertaRecibir, "6748899"));
    }
    @Test
    void testTerminarSubasta(){
        assertTrue(operador.terminarSubasta("6748899").equals("Subasta terminada"));
    }
    @Test
    void testTerminarSubastaNoExiste(){
        assertTrue(operador.terminarSubasta("6748899").equals("Subasta terminada"));
    }

    //testComprador
    @Test
    void testRealizarCompraFija(){
        assertEquals("Compra realizada", comprador.realizarCompraFija(foto1));
    }
    @Test
    void testVerHistorialCompras(){
        comprador.verHistorialCompras();
        assertTrue(comprador.getmisCompras().contains(compra1));
    }
    @Test
    void testVerHistorialComprasVacio(){
        comprador.getmisCompras().clear();
        comprador.verHistorialCompras();
        assertTrue(comprador.getmisCompras().isEmpty());
    }
    @Test
    void testAgregarCompra(){
        comprador.agregarCompra(compra1);
        assertTrue(comprador.getmisCompras().contains(compra1));
    }
    @Test
    void testAgregarCompraVacia(){
        comprador.getmisCompras().clear();
        comprador.agregarCompra(compra1);
        assertTrue(comprador.getmisCompras().contains(compra1));
    }

    //testPropietario

    @Test
    void testPasarAPasadasPropietario(){
        propietario.pasarAPasadas(pintura1);
        assertTrue(propietario.getMisPiezasActuales().contains(pintura1));
    }
    @Test
    void testAgregarAPasadas(){
        propietario.agregarAPasadas(pintura1);
        assertTrue(propietario.getMisPiezasPasadas().contains(pintura1));
    }
    @Test
    void testAgregarPiezaPropietario(){
        propietario.agregarPieza(pinturaAgregar);
        assertTrue(propietario.getMisPiezasActuales().contains(pinturaAgregar));
    }

    //testInventario
    @Test
    void testGuardarEnBodega(){
        galeriaTests.getInventario().guardarEnBodega(foto2);
        assertTrue(galeriaTests.getInventario().getPiezasEnBodega().contains(foto2));
    }
    @Test
    void testPonerEnDisponibles(){
        galeriaTests.getInventario().ponerEnDisponibles(foto2);
        assertTrue(galeriaTests.getInventario().getPiezasDisponibleVenta().contains(foto2));
    }
    @Test
    void testPonerEnExhibicion(){
        galeriaTests.getInventario().pasarAExhibicion(foto2);
        assertTrue(galeriaTests.getInventario().getPiezasEnExhibicion().contains(foto2));
    }
    @Test
    void testBuscarPiezaEnDisponibles(){
        assertTrue(galeriaTests.getInventario().buscarPieza("La nina y el buitre").equals(foto1));
    }

    @Test
    void testBuscarPiezaEnBodega(){
        assertTrue(galeriaTests.getInventario().buscarPieza("La torre de Piza").equals(foto2));
    }
    @Test
    void testBuscarPiezaEnPasadas(){
        assertTrue(galeriaTests.getInventario().buscarPieza("La Flor").equals(pintura1));
    }

    @AfterAll   
    static void setDown(){
        galeriaTests.getInventario().getPiezasEnBodega().clear();
        galeriaTests.getInventario().getPiezasEnExhibicion().clear();
        galeriaTests.getInventario().getPiezasDisponibleVenta().clear();
        galeriaTests.getInventario().getPiezasPasadas().clear();
        galeriaTests.getControladorUsuarios().getMapaCompradores().clear();
        galeriaTests.getControladorUsuarios().getMapaPropietarios().clear();
        galeriaTests.getControladorUsuarios().getMapaEmpleados().clear();
        galeriaTests.getSubastas().clear();
        galeriaTests.getCompras().clear();
    }
    

}