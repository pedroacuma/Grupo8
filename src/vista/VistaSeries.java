package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import controlador.CtrListSeries;
import modelo.Categoria;
import modelo.Conexion;
import modelo.Serie;
import modelo.Vi�eta;

public class VistaSeries extends JPanel implements IVistaSeries {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton crearCategoria;
	private JButton borrarCategoria;
	private JButton crearSerie;
	private JButton borrarSerie;
	private JButton crearVi�eta;
	private JButton borrarVi�eta;
	
	private JScrollPane scrollCategoria;
	private JScrollPane scrollSerie;
	private JScrollPane scrollVi�eta;
	
	private JList<Categoria> listCategoria;
	private JList<Serie> listSerie;
	private JList<Vi�eta> listVi�eta;
	private JTextArea infoSerie;
	
	private JLabel labelCategoria;
	private JLabel labelSerie;
	private JLabel labelVi�eta;
	private JLabel labelInformacion;
	
	private DefaultListModel <Categoria> modeloListaCats;
	private DefaultListModel <Serie> modeloListaSeries;
	private DefaultListModel <Vi�eta> modeloListaVi�etas;
	
	
	public VistaSeries() {
		
		labelCategoria = new JLabel ("Categor�as");
		labelSerie = new JLabel ("Series");
		labelVi�eta = new JLabel ("Vi�etas");
		labelInformacion = new JLabel ("Informaci�n");
		
		crearCategoria = new JButton ("Crear Categor�a");
		borrarCategoria = new JButton ("Borrar Categor�a");
		crearSerie = new JButton ("Crear Serie");
		borrarSerie = new JButton ("Borrar Serie");
		crearVi�eta = new JButton ("Crear Vi�eta");
		borrarVi�eta = new JButton ("Borrar Vi�eta");
		
		//Montamos la lista de categorias
		listCategoria = new JList<Categoria>();
		modeloListaCats = new DefaultListModel<Categoria>();
		listCategoria.setModel(modeloListaCats);
		mostrarCategorias();
		
		//Montamos la lista de Series
		listSerie = new JList<Serie>();
		modeloListaSeries = new DefaultListModel<Serie>();
		listSerie.setModel(modeloListaSeries);
		mostrarSeries();
		
		//Se le a�ade el controlador para la seleccion de una serie
		CtrListSeries cls = new CtrListSeries(this);
		setControladorListaSeries(cls);
		
		
		//Montamos la lista de Vi�etas
		listVi�eta = new JList<Vi�eta>();
		modeloListaVi�etas = new DefaultListModel<Vi�eta>();
		listVi�eta.setModel(modeloListaVi�etas);
		
		String[] vi�eta = {"Aqui se mostrar�an las vi�etas de la serie seleccionada"};
		listVi�eta = new JList(vi�eta);
		
		infoSerie = new JTextArea("Informaci�n de la serie seleccionada");
		
		scrollCategoria = new JScrollPane(listCategoria);
		scrollSerie = new JScrollPane(listSerie);
		scrollVi�eta = new JScrollPane(listVi�eta);
			
		
		// Botonera
		JPanel botonera = new JPanel();
		botonera.setLayout(new GridLayout(2, 3));
		botonera.add(crearCategoria);
		botonera.add(crearSerie);
		botonera.add(crearVi�eta);
		botonera.add(borrarCategoria);	
		botonera.add(borrarSerie);
		botonera.add(borrarVi�eta);
		
		//Scrolls y sus labels
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(labelSerie, BorderLayout.NORTH);
		panel1.add(scrollSerie, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(labelVi�eta, BorderLayout.NORTH);
		panel2.add(scrollVi�eta, BorderLayout.CENTER);
		
		//Panel Central
		JPanel panelC = new JPanel();
		panelC.setLayout(new GridLayout(2, 1));
		panelC.add(panel1);
		panelC.add(panel2);
		
		//Panel Derecha
		JPanel panelI = new JPanel();
		panelI.setLayout(new BorderLayout());
		panelI.add(labelCategoria, BorderLayout.NORTH);
		panelI.add(scrollCategoria, BorderLayout.CENTER);
		
				
		//Panel Izquierda
		JPanel panelD = new JPanel();
		panelD.setLayout(new BorderLayout());
		panelD.add(labelInformacion, BorderLayout.NORTH);
		panelD.add(infoSerie, BorderLayout.CENTER);
		
		
		// Confeccion del panel de contenidos
		JPanel panelPrinci = new JPanel();
		panelPrinci.setLayout(new GridLayout (1, 3));
		panelPrinci.add(panelI);
		panelPrinci.add(panelC);
		panelPrinci.add(panelD);
		
		this.setLayout(new BorderLayout());
		this.add(botonera, BorderLayout.NORTH);
		this.add(panelPrinci, BorderLayout.CENTER);
		
	}

	
	@Override
	public void controlador(ActionListener ctr) {
		// TODO Auto-generated method stub
		crearCategoria.addActionListener(ctr);
		crearCategoria.setActionCommand(CC);
		
		borrarCategoria.addActionListener(ctr);
		borrarCategoria.setActionCommand(BC);
		
		crearSerie.addActionListener(ctr);
		crearSerie.setActionCommand(CS);
		
		borrarSerie.addActionListener(ctr);
		borrarSerie.setActionCommand(BS);
		
		crearVi�eta.addActionListener(ctr);
		crearVi�eta.setActionCommand(CV);
		
		borrarVi�eta.addActionListener(ctr);
		borrarVi�eta.setActionCommand(BV);
		 
	}

	//Carga categorias en panel categorias
	public void mostrarCategorias() {
		for(Categoria c : Categoria.listCategorias()) {
			this.modeloListaCats.addElement(c);
		}
	}
	
	public void anadirCategoria(Categoria c) {
		this.modeloListaCats.addElement(c);
	}
	
	public void actualizar() {
		modeloListaCats.removeAllElements();
		mostrarCategorias();
		modeloListaSeries.removeAllElements();
		mostrarSeries();
	}
	
	public void borrarCategoria(Categoria c) {
		modeloListaCats.removeElement(c);
	}
	
	public void mostrarSeries() {
		for(Serie s : Serie.listSerie()) {
			modeloListaSeries.addElement(s);
		}
	}
	
	public void anadirSerie(Serie s) {
		modeloListaSeries.addElement(s);
	}
	
	public void borrarSerie(Serie s) {
		modeloListaSeries.removeElement(s);
	}
	
	public void anadirVineta(Vi�eta v){
		modeloListaVi�etas.addElement(v);
	}
	
	//M�todos para mostrar info. Se establece el ListSelectionEvent y un m�todo para mostrar la info en esta vista
	private void setControladorListaSeries(ListSelectionListener ctrLista)
	{
		listSerie.addListSelectionListener(ctrLista);
	}
	
	public void muestraInfoSerie(Serie s) {
		infoSerie.setText(s.getInfo());
	}
	

}
