package controlador;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import vista.VistaCrearCategoria;
import vista.VistaCrearSerie;
import vista.VistaCrearVi�eta;
import vista.VistaSeries;
import modelo.Conexion;
import modelo.Serie;
import modelo.Vi�eta;


public class ControladorCrearVi�eta implements ActionListener {
	VistaCrearVi�eta vcv;
	VistaSeries vs;
	public ControladorCrearVi�eta(VistaCrearVi�eta vcv, VistaSeries vs) {
		this.vs = vs;
		this.vcv = vcv;
	}
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch(cmd) {
		
		
			case VistaCrearVi�eta.ACEPTAR : {
				String serie = vcv.getNombreSerie();
				String vi�eta = vcv.getVi�eta();
				String url = vcv.getUrl();
				crearVi�eta(serie,vi�eta,url);
			}
			break;
			
			case VistaCrearVi�eta.CANCELAR : {
				vcv.cerrar();
				System.out.println("Cerrado");
			}
			
			break;
		}
		
	}
	//este metodo hay que modificar, lo de arriba creo que est� bien
	private void crearVi�eta(String serie, String vi�eta, String url){
		if(serie.equals("") || vi�eta.equals("") || url.equals("")  ){
			vcv.alerta("Rellene todos los campos");
		} else {
			Conexion con = new Conexion();
			boolean idOk = con.Select("SELECT * FROM vineta WHERE id = " + vi�eta + "").isEmpty();
			boolean serieOk = !con.Select("SELECT * FROM serie WHERE nombre = '" + serie + "'").isEmpty();
			if(!idOk) {
				vcv.alerta("Ya exite una vi�eta con ese id");
			}else if(!serieOk){
				vcv.alerta("No existe esa serie, cr�ela");
			}else {
				//aqui se crea la serie: todo
				Vi�eta v = new Vi�eta(vi�eta , url, Integer.parseInt(vi�eta));
				vs.anadirVineta(v);
				vcv.mensaje("Serie creada con exito");
		}
	}
	
	}
	}
