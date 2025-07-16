/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;
import clases.Conexion;
import clases.Estudiante;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.Connection;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import clases.Solicitud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;



/**
 *
 * @author jobno
 */
public class MenuSolicitudes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuSolicitudes.class.getName());

    /**
     * Creates new form MenuEstudiantes
     */
    public MenuSolicitudes() {
        initComponents();
        DarEstilos();
        mostrarListaSolicitudes();/*Mandar a llamar la función para mostrar 
        la lista de solicitudes que haya guardadas*/
        
    }
    
    public void DarEstilos(){/*A toda está función de DarEstilos
        no le moví nadita*/
        
        campoBusqueda.putClientProperty("Component.arc",      20);
        campoBusqueda.putClientProperty("JComponent.roundRect", true);

    }
    
    public void mostrarListaSolicitudes(){/* Es la función 
        para mostra la lista*/
        DefaultTableModel modelo = new DefaultTableModel();/*
        Sepa para que sea, pero se queda*/
        modelo.addColumn("Fecha de Elaboracio");
        /*Le pones la primera columna que tengas en la tablá*/
        modelo.addColumn("Estudiante");
        modelo.addColumn("Estatus");
        modelo.addColumn("Motivo");
        /*Pones el resto de campos, misma sintaxis modelo.addColumn("")*/
        
        //Inicias el try, yo copie y pegué del de Tareas
        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.con;
            //Se queda igual
            
            //De aquí...
            String sql = "SELECT s.idSolicitud, s.fecha, a.idAlumno, a.nombreAlumno, s.estatus, s.motivo FROM alumno a INNER JOIN solicitud s WHERE a.idAlumno=s.idAlumno;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet datos = ps.executeQuery();
            ArrayList<Solicitud> datosSolicitud = new ArrayList<>();
            
            while (datos.next()){//a aquí se queda igual,
                //solo adaptas la consulta
                int idSolicitud = datos.getInt("idSolicitud");
                /*Mando a llamar el id de la solicitud, sería el id
                de lo que quieras mandar a llamar, depende el orden 
                del constructor en la clase creo*/
                String fecha = datos.getString("fecha");
                int idAlumno = datos.getInt("idAlumno");
                String nombreAlumno = datos.getString("nombreAlumno");
                //Pongo el resto de datos
                
                /*Este es para cuando tengo un CHAR, ejemplo, 1 y para
                decir que 1 sea equivalente a algo como "Socioeconomico"
                y es lo que me va a mostrar*/
                String estatus1 = datos.getString("estatus");
                String estatus;
                if(estatus1.equals("1")){
                    estatus = "Pendiente";
                }else{
                    estatus = "Completada";
                };
                //Mismo ejemplo  que el anterior pero con motivo
                String motivo = datos.getString("motivo");
                if(motivo.equals("1")){
                    motivo = "Socioeconomico";
                }else if (motivo.equals("2") ){
                    motivo = "Salud";
                }else{
                    motivo = "Familiar";
                };
                
                //Aqui se que tiene que ver con las clases que utilicez
                Estudiante est = new Estudiante(idAlumno, nombreAlumno);
                Solicitud soli = new Solicitud(idSolicitud, nombreAlumno, fecha, estatus, motivo);
                //
                modelo.addRow(new Object[]{
                soli.getFecha(),
                est.getNombre(),
                soli.getEstatus(),
                soli.getMotivo(),
                
            });
                datosSolicitud.add(soli);
            }
        tabla_solicitudes.setModel(modelo);
        tabla_solicitudes.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
            //La fila que seleccione
            int row =tabla_solicitudes.rowAtPoint(evt.getPoint());
            //La columna que seleccione
            int col =tabla_solicitudes.columnAtPoint(evt.getPoint());

            if((col==0)||(col==1)||(col==2)||(col==3)){
                Solicitud soli= datosSolicitud.get(row);
                int id = soli.getIdSolicitud();
                VerSolicitud o = new VerSolicitud("menu 1", id);
                o.setVisible(true);
                dispose();//Cierra la pantalla actual
            }
        }
        });
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar los datos"+e.getMessage());
        }
            
        };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        botonAtenciones = new javax.swing.JButton();
        botonSolicitudes = new javax.swing.JButton();
        botonEstudiantes = new javax.swing.JButton();
        campoBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_solicitudes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(43, 138, 127));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, -1, -1));

        jPanel4.setBackground(new java.awt.Color(43, 138, 127));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(168, 204, 193));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonAtenciones.setBackground(new java.awt.Color(204, 204, 204));
        botonAtenciones.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        botonAtenciones.setText("Atenciones");
        botonAtenciones.setMargin(new java.awt.Insets(2, 0, 3, 0));
        botonAtenciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtencionesActionPerformed(evt);
            }
        });
        jPanel2.add(botonAtenciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 100, 27));

        botonSolicitudes.setBackground(new java.awt.Color(80, 80, 80));
        botonSolicitudes.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        botonSolicitudes.setForeground(new java.awt.Color(255, 255, 255));
        botonSolicitudes.setText("Solicitudes");
        botonSolicitudes.setMargin(new java.awt.Insets(2, 0, 3, 0));
        botonSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSolicitudesActionPerformed(evt);
            }
        });
        jPanel2.add(botonSolicitudes, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 100, 27));

        botonEstudiantes.setBackground(new java.awt.Color(204, 204, 204));
        botonEstudiantes.setText("Estudiantes");
        botonEstudiantes.setMargin(new java.awt.Insets(2, 0, 3, 0));
        botonEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstudiantesActionPerformed(evt);
            }
        });
        jPanel2.add(botonEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        campoBusqueda.setFont(new java.awt.Font("Poppins Medium", 0, 10)); // NOI18N
        campoBusqueda.setForeground(new java.awt.Color(102, 102, 102));
        campoBusqueda.setText("Ingresa la Matricula del Alumno");
        jPanel2.add(campoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 208, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 720, 23));

        tabla_solicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha de Elaboracion", "Estudiante", "Motivo", "Estatus"
            }
        ));
        jScrollPane1.setViewportView(tabla_solicitudes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 62, 720, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAtencionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtencionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAtencionesActionPerformed

    private void botonSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSolicitudesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonSolicitudesActionPerformed

    private void botonEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEstudiantesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonEstudiantesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());    
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> new MenuSolicitudes().setVisible(true));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAtenciones;
    private javax.swing.JButton botonEstudiantes;
    private javax.swing.JButton botonSolicitudes;
    private javax.swing.JTextField campoBusqueda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_solicitudes;
    // End of variables declaration//GEN-END:variables
}
