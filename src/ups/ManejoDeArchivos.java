package ups;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ManejoDeArchivos extends Frame implements ActionListener {
    private TextArea txtArea;
    private Button btnAbrir, btnGuardar;
    private FileDialog archivoPlano;

    public ManejoDeArchivos() {
        setTitle("Manejador Archivos Planos");
        setSize(650, 500);
        setLayout(new BorderLayout());
        // crear los componentes
        txtArea = new TextArea();
        btnAbrir = new Button("Abrir");
        btnGuardar = new Button("Guardar");
        // configar el area de texto
        txtArea.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        // Crear el panel para los botones
        Panel panel = new Panel();
        panel.add(btnAbrir);
        panel.add(btnGuardar);
        // añadir componentes a la ventana
        add(txtArea, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        // añadir escuchador de eventos
        btnAbrir.addActionListener(this);
        btnGuardar.addActionListener(this);
        // Configuración el cierre de la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
        
        public void actionPerformed(ActionEvent e ){
            String commando = e.getActionCommand();
            if (commando.equals("Abrir")){
                abrirArchivos();
            }else if(commando.equals("Guardar")){
                guardarArchivo();
            }
            //centrando ventana
            
    }
    //Metodos abrir y guardar archivo
    private void abrirArchivos(){
        //Crear un dialogo de archivos para abrir
        archivoPlano = new FileDialog(this,"Abrir Archivo Personal", FileDialog.LOAD);
        //
        archivoPlano.setVisible(true);

        String carpeta = archivoPlano.getDirectory();
        String nombreArchivo = archivoPlano.getFile();
        if(nombreArchivo != null){
            File archivo = new File(carpeta,nombreArchivo);
            try(Scanner scanner = new Scanner(archivo)){
                txtArea.setText("");
                while (scanner.hasNextLine()) {
                    txtArea.append(scanner.nextLine() + "\n");
                    
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

    }
    //metodo Guardar Archivo
    private void guardarArchivo(){
        archivoPlano = new FileDialog(this,"Guardar Archivo", FileDialog.SAVE);
        archivoPlano.setVisible(true);
        String carpeta = archivoPlano.getDirectory();
        String nombreArchivo = archivoPlano.getFile();
        if(nombreArchivo != null){
            File archivo = new File(carpeta, nombreArchivo);
            try(PrintWriter writer = new PrintWriter(new FileWriter(archivo))){
                writer.println(txtArea.getText());

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
}
