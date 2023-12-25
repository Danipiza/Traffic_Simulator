package a.extra.examples;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.SwingUtilities;

public class EjemploJTable5b extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTable tbl;
	private JTextField txt;
	private JButton btnModificar;
	private JButton btnAniadirFila;
	private JButton btnMostrarContenido;
	private MiModeloDeTabla modelo;

	public EjemploJTable5b() {
		super("Ejemplo de JTable con un modelo de tabla");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel pnlControl = new JPanel();
		pnlControl.setLayout(new GridLayout(1, 4, 5, 5));
		txt = new JTextField("");
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnAniadirFila = new JButton("Aniadir fila");
		btnAniadirFila.addActionListener(this);
		btnMostrarContenido = new JButton("Mostrar contenido");
		btnMostrarContenido.addActionListener(this);
		pnlControl.add(txt);
		pnlControl.add(btnModificar);
		pnlControl.add(btnAniadirFila);
		pnlControl.add(btnMostrarContenido);
		this.getContentPane().add(pnlControl, BorderLayout.NORTH);

		modelo = new MiModeloDeTabla(3, 3);
		tbl = new JTable(modelo);

		JScrollPane scb = new JScrollPane(tbl);
		tbl.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tbl.setRowSelectionAllowed(true);
		tbl.setColumnSelectionAllowed(true);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < tbl.getColumnCount(); i++) {
			TableColumn col = tbl.getColumnModel().getColumn(i);
			col.setPreferredWidth(150);
		}
		this.getContentPane().add(scb, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int fila = tbl.getSelectedRow();
		int col = tbl.getSelectedColumn();

		// Se puede comparar con == porque es *exactamente* el mismo objeto en
		// memoria.
		if (e.getSource() == btnModificar) {
			if (fila >= 0 && col >= 0) {
				modelo.setValueAt(txt.getText(), fila, col);
			}
		} else if (e.getSource() == btnAniadirFila) {
			modelo.aniadirFilaVacia();
		} else if (e.getSource() == btnMostrarContenido) {
			System.out.println(modelo);
		}
	}

	private class MiModeloDeTabla extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int filas = 0;
		private int cols = 0;
		private Object[][] data;
		private final String[] columnNames = { "string", "entero", "booleano" };
		@SuppressWarnings({ "deprecation", "rawtypes" })
		private final Class[] columnClasses = { new String().getClass(),
				(new Integer(0)).getClass(), (new Boolean(true)).getClass() };

		public MiModeloDeTabla(int f, int c) {
			filas = f;
			cols = c;
			data = new Object[filas][cols];
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col].toString();
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return true;
		}

		@Override
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		} 

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int c) {
			return columnClasses[c];
		}

		public void aniadirFilaVacia() {
			filas++;
			Object[][] nuevoData = new Object[filas][cols];
			for (int i = 0; i < filas - 1; i++) {
				for (int j = 0; j < cols; j++) {
					nuevoData[i][j] = data[i][j];
				}
			}
			data = nuevoData;
			for (int j = 0; j < cols; j++)
				data[filas - 1][j] = null;

			fireTableRowsInserted(filas - 1, filas - 1);
		}

		@Override
		public String toString() {
			StringBuffer s = new StringBuffer();
			s.append("Datos: " + System.lineSeparator());
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < cols; j++) {
					s.append(data[i][j]);
					s.append("  ");
				}
				s.append(System.lineSeparator());			
			}
			s.append(System.lineSeparator());
			s.append("Tipos de los objetos: " + System.lineSeparator());
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < cols; j++) {
					if (data[i][j] != null)
						s.append(data[i][j].getClass().getName() + "  ");

					else
						s.append("null  ");
				}
				s.append(System.lineSeparator());
			}
			return s.toString();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				EjemploJTable5b v = new EjemploJTable5b();
				v.setVisible(true);
			}
		});
	}
}
