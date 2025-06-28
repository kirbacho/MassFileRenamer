import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;


public class App {

static File selectedFile;
static String filePath;

public static String getExtension (String currName){
    int lastDot = currName.lastIndexOf('.');
    return currName.substring(lastDot, currName.length());
}


public static void appThing (String name, String folderPath) throws IOException{
    Scanner scanner = new Scanner(System.in);
    File folderFile = new File(folderPath);
    File [] folderArr = folderFile.listFiles();
    int i = 0;
    for (File currFile : folderArr) {
        String newName = name + " " + Integer.toString(i) + getExtension(currFile.getAbsolutePath());
        if (!currFile.getName().equals(newName) && !currFile.getName().equals(".DS_Store")) {
           
        File newFile = new File (folderPath, newName);

            currFile.renameTo(newFile);
            currFile.createNewFile();  
            currFile.delete();
        }
        
      i++;
    }
    scanner.close();
}


public static void openWindow (){


JFrame window = new JFrame("Mass File Renamer");
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
window.setSize(500,100);
window.setLayout(new FlowLayout());

JButton selectFileButton = new JButton("Select Folder");

JButton renameButton = new JButton("Rename folder");
renameButton.setEnabled(false);

JTextField inputField = new JTextField();
inputField.setPreferredSize(new Dimension(200, 30));

JLabel label = new JLabel("Enter your desired filename:");


JFileChooser browser = new JFileChooser();
browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

selectFileButton.addActionListener(e ->{
int result = browser.showOpenDialog(window);

if (result == JFileChooser.APPROVE_OPTION){
    renameButton.setEnabled(true);
    selectedFile = browser.getSelectedFile();
    filePath = selectedFile.getAbsolutePath();
}          
});

renameButton.addActionListener(e ->{
    try {
        appThing(inputField.getText(), filePath);
    } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
    renameButton.setEnabled(false);
    });

window.add (label);
window.add(inputField);
window.add(selectFileButton);
window.add (renameButton);
window.setVisible(true);
}




    public static void main(String[] args) throws Exception {
openWindow();
    }
}
